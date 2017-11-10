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

    int type = PopupDialogData.LIST_TYPE.SINGLE_CHOICE;

    DialogListAdapter(Context context) {
        mContext = context;

        mDatas = new ArrayList();
        mDatas.add(new ItemData("单独风"));
        mDatas.add(new ItemData("试试岳"));
        mDatas.add(new ItemData("十点多"));

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.single_choice_list_item_layout, parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(mDatas.get(position).mStrContent);
        holder.singleChoice.setSelected(mDatas.get(position).mIsSelected);
        holder.mItemRootView.setTag(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ViewGroup mItemRootView;
        TextView textView;
        ImageView singleChoice;

        public MyViewHolder(View view) {
            super(view);
            mItemRootView = (ViewGroup) view;
            textView = view.findViewById(R.id.id_num);
            singleChoice = view.findViewById(R.id.id_popup_single_choice);

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

    private void onClickItem(String strContent){
        for(int i = 0; i < mDatas.size(); ++i){
            ItemData itemData = mDatas.get(i);
            if(!TextUtils.isEmpty(strContent) && strContent.equals(itemData.mStrContent)){
                itemData.setSelected(true);
            }else {
                itemData.setSelected(false);
            }
        }

        notifyDataSetChanged();
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