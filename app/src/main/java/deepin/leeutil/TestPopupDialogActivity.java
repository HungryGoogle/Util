package deepin.leeutil;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import deepin.ctk.dialog.PopupDialog;

public class TestPopupDialogActivity extends Activity {


    String mStrInput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_popup_dialog);

        final List<String> list=new ArrayList<String>();
        list.add("system_single_choice_dialog");
        list.add("system_multi_choice_dialog");
        list.add("single_choice_dialog");

        ListView listView;
        listView = findViewById(R.id.id_popup_dialog_listview);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (list.get(i)) {
                    case "system_single_choice_dialog":
                        buildSingleChoose();
                        break;
                    case "system_multi_choice_dialog":
                        buildMultiChoose();
                        break;
                    case "single_choice_dialog":
                        single_choice_dialog();
                        break;
                    default:break;

                }
            }
        });
    }

    int iDefaultSelect = 0;
    private void single_choice_dialog() {
        List items = new ArrayList();
        items.add("李白");
        items.add("杜甫");
        items.add("张九龄");

        ViewGroup viewCustomGroup = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.popup_dialog_custom_layout, null);
        PopupDialog popupDialog = new PopupDialog(this);
        popupDialog.builder()
                .setTitle("猜谜语")
//                .setTitleCenter()
                .setContent("海上生明月 天涯共此时")
//                .setContentCenter()
//                .setNoTipAgain(true, new PopupDialog.OnPopupDialogClickListener() {
//                    @Override
//                    public void onClick(boolean bSelect) {
//
//                    }
//                })
//                .addCustemView(viewCustomGroup)
//                .setCancelBtnVisiable("cancel")
                .setOkBtnVisiable("提交答案", new PopupDialog.OnPopupDialogClickListener() {
                    @Override
                    public void onClick(boolean bSelect) {

                    }
                })
//                .setSimpleList((String[]) items.toArray(new String[items.size()]), new PopupDialog.OnListItemClickListener() {
//                    @Override
//                    public void onClick(int which, boolean bSelect) {
//                        Log.i("leeTest----->", "single list click " + which + ", bSelect = " + bSelect);
//                        iDefaultSelect = which;
//                    }
//                })
                .setSingleChoice((String[]) items.toArray(new String[items.size()]), iDefaultSelect, new PopupDialog.OnListItemClickListener() {
                    @Override
                    public void onClick(int which, boolean bSelect) {
                        Log.i("leeTest----->", "single choose i = " + which + ", bSelect = " + bSelect);
                        iDefaultSelect = which;
                    }
                })
//                .setMultiChoice((String[]) items.toArray(new String[items.size()]), new int[]{0,1}, new PopupDialog.OnListItemClickListener() {
//                    @Override
//                    public void onClick(int which, boolean bSelect) {
//                        Log.i("leeTest----->", "multi choose i = " + which + ", bSelect = " + bSelect);
//                    }
//                })
//                .setCanceledOnTouchOutside(false)
//                .setCancelable(false)
                .show();
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    protected AlertDialog buildSingleChoose() {
        List<String> mData = new ArrayList<String>();
        mData.add("Basic Dialog");
        mData.add("List Dialogs");
        mData.add("choice Dialogs");

        final int[] iChoose = {0};

        List<String> list = Arrays.asList("foo", "bar", "waa", "bar", "waa", "bar", "waa", "bar", "waa", "bar", "waa", "bar", "waa", "bar", "waa", "bar", "waa");
        CharSequence[] cs = list.toArray(new CharSequence[list.size()]);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("lee title")
                .setSingleChoiceItems(mData.toArray(new CharSequence[mData.size()]), iChoose[0], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // DialogUtil.dismiss(dialogInterface);
                        iChoose[0] = i;
                        Toast.makeText(getApplication(), "点击了 ： " + i, Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplication(), "最后选择了 ： " + iChoose[0], Toast.LENGTH_SHORT).show();
                    }
                })

        ;


        AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        return dialog;
    }

    protected AlertDialog buildMultiChoose() {
        List<String> mData = new ArrayList<String>();
        mData.add("Basic Dialog");
        mData.add("List Dialogs");
        mData.add("choice Dialogs");

        final int[] iChoose = {0};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("lee title")
                .setMultiChoiceItems(mData.toArray(new CharSequence[mData.size()]),null,  new DialogInterface.OnMultiChoiceClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Toast.makeText(getApplication(), "点击了 ： " + which + ", select = " + isChecked, Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplication(), "最后选择了 ： " + iChoose[0], Toast.LENGTH_SHORT).show();
                    }
                })

        ;


        AlertDialog dialog = builder.create();
        dialog.show();


        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        return dialog;
    }
}
