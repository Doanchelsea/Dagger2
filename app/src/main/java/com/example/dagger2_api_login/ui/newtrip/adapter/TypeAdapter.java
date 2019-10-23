package com.example.dagger2_api_login.ui.newtrip.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dagger2_api_login.R;
import com.example.dagger2_api_login.linsenner.LisennerNewTrip;
import com.example.dagger2_api_login.model.historyDetail.HistoryDetail;
import com.example.dagger2_api_login.model.historyDetail.Result;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    LisennerNewTrip lisennerNewTrip;
    List<Result> results;
    Context context;

    public TypeAdapter(LisennerNewTrip lisennerNewTrip, List<Result> results, Context context) {
        this.lisennerNewTrip = lisennerNewTrip;
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_type,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Result result = results.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv_name_type.setText(result.getCarTypeName());
        viewHolder.itemView.setOnClickListener(view -> {
            lisennerNewTrip.onClickItemType(results.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name_type;
        CircleImageView img_type;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name_type = itemView.findViewById(R.id.tv_xe_type);
            img_type = itemView.findViewById(R.id.img_type);
        }
    }
}
