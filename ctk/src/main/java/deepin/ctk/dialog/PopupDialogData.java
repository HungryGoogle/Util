package deepin.ctk.dialog;

public class PopupDialogData {

    public interface LIST_TYPE{
        int TEXT = 0;
        int SINGLE_CHOICE = 1;
        int MULTI_CHOICE = 1;
    }

    interface BUTTON_NUMBER{
        int TEXT = 0;
        int SINGLE_CHOICE = 1;
        int MULTI_CHOICE = 1;
    }

    public int iMenuItemHeight = 55; // 单位dp
    public String mStrTitle;
    public String mStrContent;
    public int list_type = LIST_TYPE.TEXT;




}
