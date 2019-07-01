package com.example.foodzon;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class adapterclass extends RecyclerView.Adapter<adapterclass.ViewHolder> {
    private Context context;
    private Example[] data;

    public adapterclass(Context context, Example[] data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitems,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
    final Example example=data[i];
    viewHolder.textView.setText(example.getName());
    viewHolder.textprice.setText(example.getPrice());
    Glide.with(viewHolder.imageView.getContext()).load(example.getImgurl()).into(viewHolder.imageView);
    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,itemopen.class);
            intent.putExtra("image",example.getImgurl());
            intent.putExtra("name",example.getName());
            intent.putExtra("price",example.getPrice());
            context.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView,textprice;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.textView);
            textprice=(TextView)itemView.findViewById(R.id.textprice);
            imageView=(ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}