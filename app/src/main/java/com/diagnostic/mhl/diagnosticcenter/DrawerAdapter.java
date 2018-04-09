package com.diagnostic.mhl.diagnosticcenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * This java class is created by SAJJAD AHMED NILOY on 19-Jan-18 which belongs to Banks_of_USA project.
 * © SAJJAD AHMED NILOY 2018
 */

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.RecyclerViewHolder> {

    private List<Test> arrayList = new ArrayList();

    public DrawerAdapter(List<Test> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_state_layout, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.textView.setText(arrayList.get(position).getTestName());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.id_txt_state_item);
        }
    }
}
