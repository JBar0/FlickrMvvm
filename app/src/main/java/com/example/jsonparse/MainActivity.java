package com.example.jsonparse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.jsonparse.models.Flickr;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final RecyclerAdapter adapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getLiveFlickr().observe(this, new Observer<Flickr>() {
            @Override
            public void onChanged(Flickr flickr) {
                adapter.setItemList(flickr);
            }
        });

    }
}
