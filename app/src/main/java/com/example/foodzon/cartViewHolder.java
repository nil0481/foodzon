package com.example.foodzon;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class cartViewHolder extends RecyclerView.Adapter<cartViewHolder.ViewHolder> {
    private Context context;
    private ArrayList<cartitem> data;

    public cartViewHolder(Context context, ArrayList<cartitem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cartlist,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        try {

        viewHolder.textView.setText(data.get(i).getName());
        viewHolder.textprice.setText(data.get(i).getPrice());
        Glide.with(viewHolder.imageView.getContext()).load(data.get(i).getImage()).into(viewHolder.imageView);
        viewHolder.buttondel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("global")
                        .child(data.get(i).getName());
                databaseReference.removeValue();
                int pos=data.indexOf(data.get(i));
                data.remove(pos);
                notifyDataSetChanged();
                if(data.isEmpty())viewHolder.textemptycart.setText("EMPTY");
            }
        });
        
        }catch (Exception e){}
    }
    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView,textprice,textemptycart;
        ImageView imageView;
        Button buttondel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.textView);
            textprice=(TextView)itemView.findViewById(R.id.textprice);
            textemptycart=(TextView)itemView.findViewById(R.id.textemptycart);
            imageView=(ImageView) itemView.findViewById(R.id.imageView);
            buttondel=(Button)itemView.findViewById(R.id.buttondel);
        }
    }
}






























//
//
//package com.example.foodzon;
//
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class cartViewHolder extends RecyclerView.ViewHolder {
//    public TextView textView,textprice;
//    public ImageView imageView;
//
//    public cartViewHolder(@NonNull View itemView) {
//        super(itemView);
//        textView=(TextView)itemView.findViewById(R.id.textView);
//        textprice=(TextView)itemView.findViewById(R.id.textprice);
//        imageView=(ImageView)itemView.findViewById(R.id.imageView);
//    }
//}
