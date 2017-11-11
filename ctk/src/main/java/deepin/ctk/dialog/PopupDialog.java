package deepin.ctk.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import deepin.ctk.R;


public class PopupDialog {

    private ImageView mTvNoTipAgain;

    //-------------------------------------------------
    public interface OnListItemClickListener {
        void onClick(int which, boolean bSelect);
    }
    public interface OnPopupDialogClickListener {
        void onClick(boolean bSelect);
    }
    //-------------------------------------------------

    private Context context;
    ViewGroup mRootView;
    private Dialog dialog;
    private RecyclerView mRecyclerView;
    private DialogListAdapter mAllSmallVideoAdapter;

    /**
     * @param context 不能用application的上下文
     */
    public PopupDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * 必须先调用这个函数，才能调用其他的函数
     */
    public PopupDialog builder() {
        //
        mRootView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.popup_dialog_layout_a, null);
        int width = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        mRootView.setMinimumWidth(width - 200);
        dialog = new Dialog(context, R.style.PopupDialogStyle);
        dialog.setContentView(mRootView);

        // 列表
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.id_all_items);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));
        mAllSmallVideoAdapter = new DialogListAdapter(context);
        mRecyclerView.setAdapter(mAllSmallVideoAdapter);
        mRecyclerView.addItemDecoration(new PopupDialogDivider(context, PopupDialogDivider.HORIZONTAL_LIST));

        // 先全部隐藏
        mRootView.findViewById(R.id.id_popup_title).setVisibility(View.GONE);
        mRootView.findViewById(R.id.id_popup_content).setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mRootView.findViewById(R.id.id_popup_no_tip_again_layout).setVisibility(View.GONE);
        mRootView.findViewById(R.id.id_button_popup_cancel).setVisibility(View.GONE);
        mRootView.findViewById(R.id.id_button_popup_ok).setVisibility(View.GONE);

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

    public PopupDialog setTitleCenter() {
        TextView txt_title = (TextView) mRootView.findViewById(R.id.id_popup_title);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        txt_title.setLayoutParams(lp);

        return this;
    }

    /**
     * 设置副标题（或者是内容）
     * @param content
     * @return
     */
    public PopupDialog setContent(String content) {
        if (TextUtils.isEmpty(content)) {
            return this;
        }

        TextView tvContent = (TextView) mRootView.findViewById(R.id.id_popup_content);
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(content);
        return this;
    }

    public PopupDialog setNoTipAgain(boolean defalutSelected, final OnPopupDialogClickListener callBack) {
        ViewGroup noTipAgainViewGroup = mRootView.findViewById(R.id.id_popup_no_tip_again_layout);
        noTipAgainViewGroup.setVisibility(View.VISIBLE);
        mTvNoTipAgain = mRootView.findViewById(R.id.id_popup_no_tip_again);
        mTvNoTipAgain.setSelected(defalutSelected);

        noTipAgainViewGroup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvNoTipAgain.setSelected(!mTvNoTipAgain.isSelected());
                if(callBack != null){
                    callBack.onClick(mTvNoTipAgain.isSelected());
                }
            }
        });
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

    public PopupDialog setOkBtnVisiable(final OnPopupDialogClickListener callBack) {
        TextView tvOk = (TextView) mRootView.findViewById(R.id.id_button_popup_ok);
        tvOk.setVisibility(View.VISIBLE);
        tvOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack != null){
                    callBack.onClick(true);
                }

                dialog.dismiss();
            }
        });

        return this;
    }

    /**
     * 如果设置为不可以取消，下面2个都会屏蔽
     * 1、点击外面的按钮
     * 2、点击返回按钮
     *
     * @param cancel 是否可取消
     */
    public PopupDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * 点击外面的按钮，是否可以取消
     * @param cancel 是否可取消
     */
    public PopupDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public PopupDialog setSingleChoice(String[] strList, int iDefalutSelect, OnListItemClickListener callback) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mAllSmallVideoAdapter.setType(PopupDialogData.LIST_TYPE.SINGLE_CHOICE);
        mAllSmallVideoAdapter.setItemDatas(strList);
        mAllSmallVideoAdapter.setDefalutSelect(iDefalutSelect);
        mAllSmallVideoAdapter.setCallback(callback);
        return this;
    }
    public PopupDialog setMultiChoice(String[] strList, int[] iDefalutSelect, OnListItemClickListener callback) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mAllSmallVideoAdapter.setType(PopupDialogData.LIST_TYPE.MULTI_CHOICE);
        mAllSmallVideoAdapter.setItemDatas(strList);

        for(int i = 0; i < iDefalutSelect.length; ++i) {
            mAllSmallVideoAdapter.setDefalutSelect(iDefalutSelect[i]);
        }

        mAllSmallVideoAdapter.setCallback(callback);
        return this;
    }

    /**
     * 最后一个调用的函数
     */
    public void show() {
        dialog.show();
    }


//  ---------------------------------------------------------------
    public class SheetItem {
        String name;
        OnListItemClickListener itemClickListener;
        Color color;

        public SheetItem(String name, Color color,
                         OnListItemClickListener itemClickListener) {
            this.name = name;
            this.color = color;
            this.itemClickListener = itemClickListener;
        }
    }

    public enum Color {
        Blue("#037BFF"),
        GRAY("#999999"),
        Red("#FD4A2E");

        private String name;

        private Color(String name) {
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
