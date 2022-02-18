package com.example.adminpanel.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.adminpanel.Adapter.RVImageAdapter;
import com.example.adminpanel.Adapter.RVStringAdapter;
import com.example.adminpanel.Model.ModelData;
import com.example.adminpanel.R;
import com.example.adminpanel.Utils.Cons;
import com.example.adminpanel.databinding.ActivityViewReportBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewReportActivity extends AppCompatActivity {
ActivityViewReportBinding binding;
    RVStringAdapter adapter;
    RVImageAdapter imageAdapter;

    ArrayList<String> memberList = new ArrayList<>();
    ArrayList<String> image_list = new ArrayList<>();

    String functionName = "";
    String summery = "";
    private DatabaseReference myRef;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewReportBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());


        FirebaseDatabase database = FirebaseDatabase.getInstance();
         myRef = database.getReference();



        functionName = getIntent().getStringExtra("fun");
        summery = getIntent().getStringExtra("summery");
        memberList = getIntent().getStringArrayListExtra("memberlist");
        image_list = getIntent().getStringArrayListExtra("images");

        binding.funID.setText("* Name: " + functionName);
        binding.ddID.setText(" -> " + summery);

        for (String s : image_list)
            Log.d("FFM","Image: " + s);

        for (String s : memberList)
            Log.d("FFM","Member: " + s);


        binding.rvstudentlistID.setHasFixedSize(true);
        binding.rvstudentlistID.setLayoutManager(new LinearLayoutManager(this));
        binding.rvstudentlistID.setItemAnimator(new DefaultItemAnimator());


        binding.rvstudentlistID.setHasFixedSize(true);
        binding.rvstudentlistID.setLayoutManager(new LinearLayoutManager(this));
        binding.rvstudentlistID.setItemAnimator(new DefaultItemAnimator());

        adapter = new RVStringAdapter(ViewReportActivity.this,memberList,false);
        binding.rvstudentlistID.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        binding.imageID.setHasFixedSize(true);
        binding.imageID.setLayoutManager(new LinearLayoutManager(this));
        binding.imageID.setItemAnimator(new DefaultItemAnimator());

        imageAdapter = new RVImageAdapter(this,image_list,false);
        binding.imageID.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();

        binding.backID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        binding.submitedatabaseID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: Submit..
                if (Cons.isOnline(ViewReportActivity.this))
                    submiteData();
                else
                    Toast.makeText(ViewReportActivity.this, "Connection Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void submiteData() {
        // Read from the database

        new AlertDialog.Builder(this).setTitle("Are You Sure (Confirmation Dialog)?")
        .setMessage("Post Data: Below \n\n" +  "Fun Name: " + functionName + "\n"+
                "Summery: " + summery + " \n"+
                "Size of Member: " + memberList.size() + "\n" +
                "Size of Images: " + image_list.size())
        .setCancelable(true)
        .setNegativeButton("NO",null)
        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                ModelData modelData = new ModelData(functionName,memberList,image_list,summery);

                myRef.child("Function").child(functionName).setValue(modelData);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ModelData value = dataSnapshot.getValue(ModelData.class);

                        Log.d("FFM","Value: " + value.getFunctionName());
                        Toast.makeText(ViewReportActivity.this, "Data Post SuccessFully", Toast.LENGTH_SHORT).show();
                        onBackPressed();

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.d("FFM","Fail: " + error.getMessage());
                        Toast.makeText(ViewReportActivity.this, "Error Try Again!", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }).create().show();

    }
}