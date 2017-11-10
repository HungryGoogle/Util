package deepin.ctk.dialog;

/**
 * Created by li on 2017/11/9.
 */

public abstract class DialogItemListener {

    /**
     * item点击事件
     */
    public abstract void onItemClick(CharSequence text, int position);

    /**
     * 底部点击事件
     */
    public void onBottomBtnClick() {
    }
}
