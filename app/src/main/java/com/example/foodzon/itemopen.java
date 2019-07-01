package com.example.foodzon;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.client.Firebase;

public class itemopen extends AppCompatActivity {
    Button buttoncart;
    ImageView imageViewopen;
    TextView textitemopen,textpriceopen;
    Firebase url,url2,url3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemopener);
        Firebase.setAndroidContext(this);
        imageViewopen=(ImageView)findViewById(R.id.imageViewopen);
        textitemopen=(TextView)findViewById(R.id.textitemopen);
        textpriceopen=(TextView)findViewById(R.id.textpriceopen);
        buttoncart=(Button)findViewById(R.id.buttoncart);
        final String getimage=getIntent().getStringExtra("image");
        final String getname=getIntent().getStringExtra("name");
        final String getprice=getIntent().getStringExtra("price");
        Glide.with(imageViewopen.getContext()).load(getimage).into(imageViewopen);
        textitemopen.setText(getname);
        textpriceopen.setText(getprice);

        url=new Firebase("https://foodzon-cd299.firebaseio.com/");
        Firebase firebase=url.child("global");
        url2=new Firebase("https://foodzon-cd299.firebaseio.com/"+"global/");
        buttoncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase firebase2=url2.child(getname);
                url3=new Firebase("https://foodzon-cd299.firebaseio.com/global/"+getname);
                Toast.makeText(itemopen.this,"Added to cart",Toast.LENGTH_SHORT).show();
                Firebase firebaseadd=url3.child("name");
                firebaseadd.setValue(getname);
                Firebase firebasein=url3.child("price");
                firebasein.setValue(getprice);
                Firebase firebasein2=url3.child("image");
                firebasein2.setValue(getimage);
                Intent intent=new Intent(itemopen.this,cartrecycler.class);
                startActivity(intent);
            }
        });




    }


}
