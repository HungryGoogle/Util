package deepin.ctk.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

import deepin.ctk.R;


public class PopupDialog {

    //-------------------------------------------------
    public interface OnSheetItemClickListener {
        void onClick(int which);
    }
    //-------------------------------------------------

    private Context context;
    ViewGroup mRootView;
    private Dialog dialog;
    private List<SheetItem> sheetItemList;
    private Display display;


    private int iMenuItemHeight = 55; // 单位dp
    private RecyclerView mRecyclerView;
    private DialogListAdapter mAllSmallVideoAdapter;

    /**
     * @param context 不能用application的上下文
     */
    public PopupDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public PopupDialog builder() {
        mRootView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.popup_dialog_layout_a, null);

        // 设置为和屏幕一样宽
        int width = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        mRootView.setMinimumWidth(width - 200);

        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.id_all_items);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));
        mAllSmallVideoAdapter = new DialogListAdapter(context);
        mRecyclerView.setAdapter(mAllSmallVideoAdapter);
        mRecyclerView.addItemDecoration(new PopupDialogDivider(context, PopupDialogDivider.HORIZONTAL_LIST));

        dialog = new Dialog(context, R.style.PopupDialogStyle);



        dialog.setContentView(mRootView);
        return this;
    }

    public PopupDialog setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            return this;
        }

        TextView txt_title = (TextView) mRootView.findViewById(R.id.id_popup_title);
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setText(title);
        return this;
    }

    public PopupDialog setCancelBtnVisiable(boolean btnVisiable) {
        TextView tvCancel = (TextView) mRootView.findViewById(R.id.id_button_popup_cancel);

        if (btnVisiable) {
            tvCancel.setVisibility(View.VISIBLE);
        } else {
            tvCancel.setVisibility(View.GONE);
        }

        tvCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return this;
    }

    public PopupDialog setOkBtnVisiable(boolean btnVisiable) {
        TextView tvOk = (TextView) mRootView.findViewById(R.id.id_button_popup_ok);

        if (btnVisiable) {
            tvOk.setVisibility(View.VISIBLE);
        } else {
            tvOk.setVisibility(View.GONE);
        }

        tvOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return this;
    }

    public PopupDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public PopupDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }


    public void show() {
        dialog.show();
    }



    public class SheetItem {
        String name;
        OnSheetItemClickListener itemClickListener;
        PopupDialogColor color;

        public SheetItem(String name, PopupDialogColor color,
                         OnSheetItemClickListener itemClickListener) {
            this.name = name;
            this.color = color;
            this.itemClickListener = itemClickListener;
        }
    }

    public enum PopupDialogColor {
        Blue("#037BFF"),
        GRAY("#999999"),
        Red("#FD4A2E");

        private String name;

        private PopupDialogColor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
