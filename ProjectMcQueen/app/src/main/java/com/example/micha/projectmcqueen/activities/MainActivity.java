package com.example.micha.projectmcqueen.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.micha.projectmcqueen.adapters.MainAdapter;
import com.example.micha.projectmcqueen.R;

public class MainActivity extends AppCompatActivity  implements MainAdapter.MainAdapterOnClickHandler{

private RecyclerView _mainRecyclerView;
    private MainAdapter _mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

_mainRecyclerView = (RecyclerView)findViewById(R.id.rv_main);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        _mainRecyclerView.setLayoutManager(layoutManager);
        _mainRecyclerView.setHasFixedSize(true);
        _mainAdapter = new MainAdapter(this, this);
        _mainRecyclerView.setAdapter(_mainAdapter);
    }

    @Override
    public void onClick(int index) {
        Intent intent;
        switch (index){
            case 0:
                intent = new Intent(this, AlphabethActivity.class);
                break;
            default:
                intent = null;
                break;
        }
        if(intent != null)
        startActivity(intent);
    }
}
