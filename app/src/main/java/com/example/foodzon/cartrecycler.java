package com.example.foodzon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class cartrecycler extends AppCompatActivity {
    RecyclerView mcart;
    TextView textemptycart;
    private DatabaseReference mdatabase;
    ArrayList<cartitem> arrayList;
    cartViewHolder adapter;
//    FirebaseRecyclerOptions<cartitem> options;
//    private FirebaseRecyclerAdapter<cartitem, cartViewHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cartview);
        arrayList = new ArrayList<cartitem>();
        textemptycart=(TextView)findViewById(R.id.textemptycart);

        try {
        mdatabase = FirebaseDatabase.getInstance().getReference().child("global");
        // mdatabase.keepSynced(true);
        mcart = (RecyclerView) findViewById(R.id.recyclerViewcart);
        mcart.setHasFixedSize(true);
        mcart.setLayoutManager(new LinearLayoutManager(cartrecycler.this));

            mdatabase.addValueEventListener(new
                ValueEventListener() {
                    @Override
                    public void onDataChange (@NonNull DataSnapshot dataSnapshot){

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            cartitem cartitem = dataSnapshot1.getValue(com.example.foodzon.cartitem.class);
                            if (!arrayList.contains(cartitem))
                                arrayList.add(cartitem);
                        }
                        adapter = new cartViewHolder(cartrecycler.this, arrayList);
                        mcart.setAdapter(adapter);
                    }
                    @Override
                    public void onCancelled (@NonNull DatabaseError databaseError){
                        Toast.makeText(cartrecycler.this, "Something is wrong", Toast.LENGTH_SHORT).show();
                    }
                });

        }catch (Exception e){}
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putString("key",disp.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //disp.setText(savedInstanceState.getString("key"));
    }
}