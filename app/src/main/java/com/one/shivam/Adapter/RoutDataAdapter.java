package com.one.shivam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.shivam.MainActivity;
import com.one.shivam.Model.CommonModel;
import com.one.shivam.R;

import java.util.List;

public class RoutDataAdapter extends RecyclerView.Adapter<RoutDataAdapter.ViewHolder> {
    MainActivity context;
    List<CommonModel> getlist;

    public RoutDataAdapter(MainActivity mainActivity, List<CommonModel> getlist) {
        this.context = mainActivity;
        this.getlist = getlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_list,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_number.setText(getlist.get(position).number);
        holder.txt_time.setText(getlist.get(position).time);
        holder.txt_action.setText(getlist.get(position).action);
        
        holder.lin_rout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.getShowData(getlist.get(position).number,getlist.get(position).time,getlist.get(position).action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_number,txt_time,txt_action;
        LinearLayout lin_rout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_number = itemView.findViewById(R.id.txt_number);
            txt_time = itemView.findViewById(R.id.txt_time);
            txt_action = itemView.findViewById(R.id.txt_action);
            lin_rout = itemView.findViewById(R.id.lin_rout);
        }
    }
}
