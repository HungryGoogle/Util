package deepin.ctk.dialog;

/**
 * Created by li on 2017/11/10.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import deepin.ctk.R;


/**
 * Created by li on 2017/6/29.
 */

public class DialogListAdapter extends RecyclerView.Adapter<DialogListAdapter.MyViewHolder> {
    private final Context mContext;
    private final ArrayList<ItemData> mDatas;
    private PopupDialog.OnListItemClickListener mCallback;
    private int mItemType = PopupDialogData.LIST_TYPE.TEXT;

    public void setType(int type) {
        switch (type){
            case PopupDialogData.LIST_TYPE.TEXT:
                mItemType = PopupDialogData.LIST_TYPE.TEXT;
                break;
            case PopupDialogData.LIST_TYPE.SINGLE_CHOICE:
                mItemType = PopupDialogData.LIST_TYPE.SINGLE_CHOICE;
                break;
            case PopupDialogData.LIST_TYPE.MULTI_CHOICE:
                mItemType = PopupDialogData.LIST_TYPE.MULTI_CHOICE;
                break;
            default:
                mItemType = PopupDialogData.LIST_TYPE.TEXT;
                break;
        }
    }

    DialogListAdapter(Context context) {
        mContext = context;
        mDatas = new ArrayList();
    }

    public void setItemDatas(String [] strDatas){
        mDatas.clear();
        for(int i = 0; i < strDatas.length; ++i){
            ItemData itemData = new ItemData(strDatas[i]);
            mDatas.add(itemData);
        }
    }

    public void setDefalutSelect(int iSelect){
        if(iSelect < 0 || mDatas.size() <= iSelect){
            return;
        }

        ItemData itemData = mDatas.get(iSelect);
        itemData.setSelected(true);
        notifyDataSetChanged();
    }

    public void setCallback(PopupDialog.OnListItemClickListener callback){
        mCallback = callback;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.single_choice_list_item_layout, parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(mDatas.get(position).mStrContent);
        holder.mItemRootView.setTag(mDatas.get(position));

        if(mItemType == PopupDialogData.LIST_TYPE.SINGLE_CHOICE){
            holder.singleChoice.setSelected(mDatas.get(position).mIsSelected);
        }else if(mItemType == PopupDialogData.LIST_TYPE.MULTI_CHOICE){
            holder.multiChoice.setSelected(mDatas.get(position).mIsSelected);
        }else {
            // expand other
        }
    }

    int maxLine = 5;
    @Override
    public int getItemCount() {
        if(mDatas.size() > maxLine)
            return maxLine;
        return mDatas.size();
    }

    private void onClickItem(String strContent){
        for(int i = 0; i < mDatas.size(); ++i){
            ItemData itemData = mDatas.get(i);
            // 找到当前的
            if(mItemType == PopupDialogData.LIST_TYPE.SINGLE_CHOICE){
                if(!TextUtils.isEmpty(strContent) && strContent.equals(itemData.mStrContent)){
                    itemData.setSelected(true);
                    if(mCallback != null){
                        mCallback.onClick(i, true);
                    }
                }else {
                    itemData.setSelected(false);
                }
            }else if(mItemType == PopupDialogData.LIST_TYPE.MULTI_CHOICE){
                if(!TextUtils.isEmpty(strContent) && strContent.equals(itemData.mStrContent)) {
                    itemData.setSelected(!itemData.mIsSelected);
                    mCallback.onClick(i, itemData.mIsSelected);
                }
            }else {
                // expand other
            }
        }
        notifyDataSetChanged();
    }

    // -----------------------------------------------------------------
    class MyViewHolder extends RecyclerView.ViewHolder {
        ViewGroup mItemRootView;
        TextView textView;
        ImageView singleChoice;
        ImageView multiChoice;

        public MyViewHolder(View view) {
            super(view);
            mItemRootView = (ViewGroup) view;
            textView = view.findViewById(R.id.id_num);

            singleChoice = view.findViewById(R.id.id_popup_single_choice);
            multiChoice = view.findViewById(R.id.id_popup_multi_choice);
            singleChoice.setVisibility(View.GONE);
            multiChoice.setVisibility(View.GONE);

            if(mItemType == PopupDialogData.LIST_TYPE.SINGLE_CHOICE){
                singleChoice.setVisibility(View.VISIBLE);
            }else if(mItemType == PopupDialogData.LIST_TYPE.MULTI_CHOICE){
                multiChoice.setVisibility(View.VISIBLE);
            }else {
                // expand other
            }

            mItemRootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ItemData itemData = (ItemData)v.getTag();
                    if(itemData == null){
                        return;
                    }

                    onClickItem(itemData.mStrContent);
                }
            });
        }
    }

    class ItemData {
        String mStrContent;
        boolean mIsSelected = false;

        public void setSelected(boolean selected) {
            this.mIsSelected = selected;
        }

        public ItemData() {
        }

        public ItemData(String strContent) {
            mStrContent = strContent;
        }
    }
}