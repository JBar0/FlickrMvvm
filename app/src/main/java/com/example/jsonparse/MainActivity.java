package com.example.jsonparse;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.jsonparse.models.Flickr;
import com.example.jsonparse.models.Item;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewModel viewModel;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.pull_to_refresh);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final RecyclerAdapter adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getLiveFlickr().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                if (items != null) {
                    adapter.setItemList(items);
                }
            }
        });

        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(MainActivity.this, Uri.parse(item.getLink()));
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.refresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }
}
