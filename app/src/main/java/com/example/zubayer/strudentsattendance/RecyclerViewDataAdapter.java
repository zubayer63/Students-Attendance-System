package com.example.zubayer.strudentsattendance;

/**
 * Created by Zubayer on 01-Apr-19.
 */
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.ItemRowHolder> {

     ArrayList<SectionDataModel> dataList;
     Context mContext;



    public RecyclerViewDataAdapter(Context context, ArrayList<SectionDataModel> dataList) {
        this.dataList = dataList;
        this.mContext = context;
        Log.i("adapter", String.valueOf(dataList.size()));
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {

try {
    ArrayList singleSectionItems = dataList.get(i).singleItemModels;
    String sectionName = dataList.get(i).getHeaderTitle();

    itemRowHolder.itemTitle.setText(sectionName);

    SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(mContext, singleSectionItems);

    itemRowHolder.recycler_view_list.setHasFixedSize(true);
    itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);
}
catch (Exception e){e.printStackTrace();}


    }

    @Override
    public int getItemCount() {
        return dataList.size();

    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        TextView itemTitle;

         RecyclerView recycler_view_list;





        public ItemRowHolder(View view) {
            super(view);

            this.itemTitle = (TextView) view.findViewById(R.id.itemTitle);
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.recycler_view_list);



        }

    }

}