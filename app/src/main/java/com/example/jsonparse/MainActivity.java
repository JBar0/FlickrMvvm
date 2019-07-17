package com.example.jsonparse;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.jsonparse.models.Flickr;
import com.example.jsonparse.models.Item;
import com.example.jsonparse.services.ResReceiver;
import com.example.jsonparse.services.DownloadService;

import java.util.List;

import static com.example.jsonparse.services.DownloadService.BAD_RESULT;
import static com.example.jsonparse.services.DownloadService.GOOD_RESULT;
import static com.example.jsonparse.services.DownloadService.SEND_TEXT;

public class MainActivity extends AppCompatActivity implements ResReceiver.Receiver {
    private static final String TAG = "MainActivity";
    private ViewModel viewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ResReceiver resReceiver;

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        if (resultData != null) {
            switch (resultCode) {
                case GOOD_RESULT:
                    Log.d(TAG, "onReceiveResult: " + resultData.getString(SEND_TEXT));
                    break;
                case BAD_RESULT:
                    Log.d(TAG, "onReceiveResult: " + resultData.getString(SEND_TEXT));
                    break;
            }
        } else {
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resReceiver = new ResReceiver(new Handler());
        resReceiver.setReceiver(this);

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
                viewModel.refresh(MainActivity.this, resReceiver);
            }
        });

        viewModel.getDownloadResult().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                swipeRefreshLayout.setRefreshing(aBoolean);
            }
        });


    }
}
