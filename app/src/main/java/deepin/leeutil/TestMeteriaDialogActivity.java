package deepin.leeutil;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import deepin.dialog.materialdialogs.DialogAction;
import deepin.dialog.materialdialogs.MaterialDialog;

public class TestMeteriaDialogActivity extends Activity implements View.OnClickListener {

    String mStrInput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dialog);

        findViewById(R.id.id_single_choice_dialog).setOnClickListener(this);
        findViewById(R.id.id_multi_choice_dialog).setOnClickListener(this);
        findViewById(R.id.one_button_base_dialog).setOnClickListener(this);
        findViewById(R.id.two_button_base_dialog).setOnClickListener(this);
        findViewById(R.id.three_button_base_dialog).setOnClickListener(this);
        findViewById(R.id.id_no_tip_again).setOnClickListener(this);
        findViewById(R.id.id_dialog_input).setOnClickListener(this);
        findViewById(R.id.id_dialog_only_input).setOnClickListener(this);
        findViewById(R.id.id_dialog_input_single_choice).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_single_choice_dialog:
                onClickSingleChoiceDialog();
                break;
            case R.id.id_multi_choice_dialog:
                id_multi_choice_dialog();
                break;
            case R.id.one_button_base_dialog:
                one_button_base_dialog();
                break;
            case R.id.two_button_base_dialog:
                two_button_base_dialog();
                break;
            case R.id.three_button_base_dialog:
                three_button_base_dialog();
                break;
            case R.id.id_no_tip_again:
                id_no_tip_again();
                break;

            case R.id.id_dialog_input:
                id_dialog_input();
                break;
            case R.id.id_dialog_only_input:
                id_dialog_only_input();
                break;
            case R.id.id_dialog_input_single_choice:
                id_dialog_input_single_choice();
                break;

            default:
                break;
        }
    }

    private void id_dialog_only_input() {
        ///////////////////////
        List<String> mData;
        mData = new ArrayList<String>();

        mData.add("Basic Dialog");
        mData.add("List Dialogs");
        mData.add("Multi Choice List Dialogs");
        mData.add("Custom Views");
        mData.add("Input Dialogs ");
        mData.add("Progress Dialogs ");
        mData.add("ColorDialog");
        mData.add("File Selector Dialogs");
        mData.add("SingleChoice Dialogs");

        new MaterialDialog.Builder(TestMeteriaDialogActivity.this)
                .items(mData)
                .dividerColorRes(R.color.colorAccent)
                .itemsCallback(new MaterialDialog.ListCallback(){
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        Toast.makeText(TestMeteriaDialogActivity.this, "选择了 " + position + ", text = " + text, Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }
    private void id_dialog_input_single_choice() {

        buildSingleChoose();

    }


    private void one_button_base_dialog() {
        new MaterialDialog.Builder(TestMeteriaDialogActivity.this)
                .title("demo title")
                .content("沧海一声笑，滔滔两岸潮")
                .positiveText(R.string.dialog_ok)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Log.i("Test---->", "click ok button");
                        Toast.makeText(TestMeteriaDialogActivity.this, "点击确定", Toast.LENGTH_LONG).show();
                    }
                })
                .positiveColor(Color.BLUE)
                .show();


    }

    private void two_button_base_dialog() {
        new MaterialDialog.Builder(TestMeteriaDialogActivity.this)
                .title("demo title")
                .content("沧海一声笑，滔滔两岸潮")
                .positiveText(R.string.dialog_ok)
                .positiveColor(Color.BLACK)
                .negativeText("不同意")
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.NEUTRAL) {
                            Toast.makeText(TestMeteriaDialogActivity.this, "更多信息", Toast.LENGTH_LONG).show();
                        } else if (which == DialogAction.POSITIVE) {
                            Toast.makeText(TestMeteriaDialogActivity.this, "同意" , Toast.LENGTH_LONG).show();
                        } else if (which == DialogAction.NEGATIVE) {
                            Toast.makeText(TestMeteriaDialogActivity.this, "不同意", Toast.LENGTH_LONG).show();
                        }

                    }
                })
                .show();

    }

    private void three_button_base_dialog() {
        new MaterialDialog.Builder(TestMeteriaDialogActivity.this)
                .title("demo title")
                .content("沧海一声笑，滔滔两岸潮")
                .positiveText(R.string.dialog_ok)
                .negativeText("不同意")
                .neutralText("更多信息")
                //点击事件添加 方式1
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.NEUTRAL) {
                            Toast.makeText(TestMeteriaDialogActivity.this, "更多信息", Toast.LENGTH_LONG).show();
                        } else if (which == DialogAction.POSITIVE) {
                            Toast.makeText(TestMeteriaDialogActivity.this, "同意" , Toast.LENGTH_LONG).show();
                        } else if (which == DialogAction.NEGATIVE) {
                            Toast.makeText(TestMeteriaDialogActivity.this, "不同意", Toast.LENGTH_LONG).show();
                        }

                    }
                })
                .show();
    }

    private void id_no_tip_again() {
        new MaterialDialog.Builder(TestMeteriaDialogActivity.this)
                .title("demo title")
                .content("沧海一声笑，滔滔两岸潮")
                .positiveText(R.string.dialog_ok)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Log.i("Test---->", "click ok button");
                    }
                })
                .positiveColor(Color.BLUE)
                .negativeColor(Color.BLUE)
                .neutralColor(Color.BLUE)
                .negativeText("不同意")
                .neutralText("更多信息")

                .widgetColor(Color.BLUE)//不再提醒的checkbox 颜色
                //CheckBox
                .checkBoxPrompt("不再提醒", false, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            Toast.makeText(TestMeteriaDialogActivity.this, "不再提醒", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(TestMeteriaDialogActivity.this, "会再次提醒", Toast.LENGTH_LONG).show();
                        }
                    }
                })

                .show();
    }


    private void onClickSingleChoiceDialog() {

        List<String> mData;
        mData = new ArrayList<String>();

        mData.add("Basic Dialog");
        mData.add("List Dialogs");
        mData.add("Multi Choice List Dialogs");
        mData.add("Custom Views");
        mData.add("Input Dialogs ");
        mData.add("Progress Dialogs ");
        mData.add("ColorDialog");
        mData.add("File Selector Dialogs");
        mData.add("SingleChoice Dialogs");

        final String[] dataChoose = new String[1];//list选中数据
        new MaterialDialog.Builder(TestMeteriaDialogActivity.this)
                .title("Single Choice List Dialogs")
                .content("Multi Choice List Dialogs,显示数组信息，高度会随着内容扩大.可以多选")
                .items(mData)
                .itemsCallbackSingleChoice(/* 选择1 */1, new MaterialDialog.ListCallbackSingleChoice() {//0 表示第一个选中 -1 不选
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        dataChoose[0] = "选中的Item " + which;
                        Toast.makeText(TestMeteriaDialogActivity.this, dataChoose[0], Toast.LENGTH_LONG).show();
                        return true;
                    }
                })
                .show();
    }

    private void id_multi_choice_dialog() {
        List<String> mData = new ArrayList<String>();
        mData.add("Basic Dialog");
        mData.add("List Dialogs");

        new MaterialDialog.Builder(TestMeteriaDialogActivity.this)
                .title("Multi Choice List Dialogs")
                .content("Multi Choice List Dialogs,显示数组信息，高度会随着内容扩大.可以多选")
                .items(mData)
                .positiveText("确定")
                .widgetColor(Color.GRAY)//改变checkbox的颜色
                //多选框添加
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {

                        return true;//false 的时候没有选中样式
                    }
                })
                //点击确定后获取选中的下标数组
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        Toast.makeText(TestMeteriaDialogActivity.this, "选中" + dialog.getSelectedIndices().length + "个", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }


    private void id_dialog_input() {
        final String[] strInput = {""};
        new MaterialDialog.Builder(TestMeteriaDialogActivity.this)
                .title("输入窗")
                .content("包含输入框的diaolog")
                .widgetColor(Color.BLUE)//输入框光标的颜色
//                .inputType(InputType.TYPE_CLASS_PHONE)//可以输入的类型-电话号码
                .inputType(InputType.TYPE_CLASS_TEXT)//可以输入的类型-电话号码
                //前2个一个是hint一个是预输入的文字
                .input(R.string.input_hint, R.string.input_prefill, new MaterialDialog.InputCallback() {

                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        if (dialog.getInputEditText().length() < 3) {

                            dialog.getActionButton(DialogAction.POSITIVE).setEnabled(false);
                        } else {
                            dialog.getActionButton(DialogAction.POSITIVE).setEnabled(true);
                        }

                        mStrInput = input.toString();

                        Log.i("test---->", "输入的是：" + input);
                    }
                })
                .alwaysCallInputCallback()
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(TestMeteriaDialogActivity.this, "输入内容 ： " + mStrInput, Toast.LENGTH_LONG).show();
                    }
                })
                .show();

    }
    ///////////////////////////////////////
    // 简单实现
    protected AlertDialog buildSingleChoose() {
        List<String> mData = new ArrayList<String>();
        mData.add("Basic Dialog");
        mData.add("List Dialogs");
        mData.add("choice Dialogs");

        List<String> list = Arrays.asList("foo", "bar", "waa", "bar", "waa", "bar", "waa", "bar", "waa", "bar", "waa", "bar", "waa", "bar", "waa", "bar", "waa");
        CharSequence[] cs = list.toArray(new CharSequence[list.size()]);


        AlertDialog.Builder builder = new AlertDialog.Builder(TestMeteriaDialogActivity.this);
        builder.setTitle("lee title")
                .setSingleChoiceItems(mData.toArray(new CharSequence[mData.size()]), 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // DialogUtil.dismiss(dialogInterface);
                        Toast.makeText(TestMeteriaDialogActivity.this, "点击了 ： " + i, Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

        ;


        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }
}
