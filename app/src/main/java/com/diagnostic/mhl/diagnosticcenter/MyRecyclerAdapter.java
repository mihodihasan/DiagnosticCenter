package com.diagnostic.mhl.diagnosticcenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyVH> {

    List<Test> testList;
    Context context;
//
//    public MyRecyclerAdapter() {
//    }

    public MyRecyclerAdapter(List<Test> testList, Context context) {
        this.testList = testList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyVH(LayoutInflater.from(context).inflate(R.layout.list_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {
        holder.testNameTV.setText(testList.get(position).getTestName());
        holder.testPriceTV.setText(String.valueOf(testList.get(position).getTestPrice()));
    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    class MyVH extends RecyclerView.ViewHolder{
        TextView testNameTV,testPriceTV;
        public MyVH(View itemView) {
            super(itemView);

            testNameTV=itemView.findViewById(R.id.test_name);
            testPriceTV=itemView.findViewById(R.id.test_price);
        }
    }
}
