package com.example.adminpanel.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.adminpanel.Adapter.RVStringAdapter;
import com.example.adminpanel.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    RVStringAdapter adapter;
    ArrayList<String> memberList = new ArrayList<>();
    ArrayList<String> image_list = new ArrayList<>();

    String functionName = "";
    String summery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());


        // Write a message to the database

        memberList.clear();
        image_list.clear();

        //Initialize..
        binding.rvstudentlistID.setHasFixedSize(true);
        binding.rvstudentlistID.setLayoutManager(new LinearLayoutManager(this));
        binding.rvstudentlistID.setItemAnimator(new DefaultItemAnimator());

        binding.rvlistimageID.setHasFixedSize(true);
        binding.rvlistimageID.setLayoutManager(new LinearLayoutManager(this));
        binding.rvstudentlistID.setItemAnimator(new DefaultItemAnimator());


        adapter = new RVStringAdapter(this, memberList, true);
        binding.rvstudentlistID.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        binding.btnfunID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                functionName = binding.spatharaedID.getText().toString();
                if (!TextUtils.isEmpty(functionName)) {
                    binding.spatharaedID.setText(functionName);
                    Toast.makeText(MainActivity.this, "Function Name Add Succsesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Enter the Function Name", Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.btnaddstudentID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentName = binding.edstudentID.getText().toString();

                if (!TextUtils.isEmpty(studentName)) {
                    memberList.add(studentName);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Enter the Member Name", Toast.LENGTH_SHORT).show();
                }
                binding.edstudentID.setText("");
            }
        });


        binding.btnAddImageID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String image_uris = binding.edimageurlid.getText().toString();

                if (!TextUtils.isEmpty(image_uris)) {
                    image_list.add(image_uris);

                    adapter = new RVStringAdapter(MainActivity.this, image_list, true);
                    binding.rvlistimageID.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(MainActivity.this, "Enter the Image Uri", Toast.LENGTH_SHORT).show();
                }
                binding.edimageurlid.setText("");
            }
        });


        binding.btndiscpriotnID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                summery = binding.edsummeryID.getText().toString();

                if (!TextUtils.isEmpty(summery)) {
                    Toast.makeText(MainActivity.this, "Add Succsesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Enter the Summery", Toast.LENGTH_SHORT).show();
                }
            }
        });


        binding.btnsubmiteID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("FFM","Click" + summery + " | " + functionName + " | " + image_list.size() + " | " + memberList.size());
                if (!TextUtils.isEmpty(summery) && !TextUtils.isEmpty(functionName) && image_list.size() > 0
                && memberList.size() > 0){
                    startActivity(new Intent(MainActivity.this,ViewReportActivity.class)
                    .putExtra("fun",functionName)
                    .putExtra("summery",summery)
                    .putExtra("memberlist",memberList)
                    .putExtra("images",image_list));

                }
                else {
                    Toast.makeText(MainActivity.this, "Some thing went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}