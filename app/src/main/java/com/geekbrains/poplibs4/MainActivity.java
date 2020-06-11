package com.geekbrains.poplibs4;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.poplibs4.adapters.UsersAdapter;
import com.geekbrains.poplibs4.data.entities.UserEntity;

import com.geekbrains.poplibs4.di.DaggerDataComponent;
import com.geekbrains.poplibs4.di.DataComponent;
import com.geekbrains.poplibs4.di.modules.ActivityModule;
import com.geekbrains.poplibs4.di.modules.ApplicationContextModule;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainPresenter presenter;
    private UsersAdapter adapter;

    private ProgressBar progressBar;
    private EditText etUserName;

    private double timeResult = 0d;
    private DataComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Realm.init(getApplicationContext());

        initPresenter();
        initViews();
        initRecycler();
    }

    @Override
    protected void onResume() {
        presenter.bindView(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView();
    }

    private void initPresenter() {
        component = DaggerDataComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationContextModule(new ApplicationContextModule(this))
                .build();

        presenter = new MainPresenter();
        component.injectToPresenter(presenter);
    }

    private void initViews() {
        etUserName = findViewById(R.id.editText);
        progressBar = findViewById(R.id.progressBar);

        Button btnLoad = findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(v -> onClick());

        Button bntSaveRoom = findViewById(R.id.btn_save_room);
        bntSaveRoom.setOnClickListener(v -> presenter.onRoomSaveClicked(adapter.getData()));

        Button btnSaveRealm = findViewById(R.id.btn_save_realm);
        btnSaveRealm.setOnClickListener(v -> presenter.onRealmSaveClicked(adapter.getData()));

        Button btnLoadRoom = findViewById(R.id.btn_load_room);
        btnLoadRoom.setOnClickListener(v -> presenter.onRoomLoadClicked());

        Button btnLoadRealm = findViewById(R.id.btn_load_realm);
        btnLoadRealm.setOnClickListener(v -> presenter.onRealmLoadClicked());
    }

    private void initRecycler() {
        adapter = new UsersAdapter();
        RecyclerView recyclerView = findViewById(R.id.rv_repos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private boolean isNetworkConnected() {
        if (!component.isConnected()) {
            Toast.makeText(this, R.string.check_internet, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void onClick() {
        if (!isNetworkConnected()) return;
        String request = etUserName.getText().toString();
        etUserName.setText("");
        presenter.loadNetData(request);
    }

    public void updateResult() {
        TextView tvResult = findViewById(R.id.tv_test_time);
        TextView tvCount = findViewById(R.id.tv_test_items_count);
        tvResult.setText(String.valueOf(timeResult));
        tvCount.setText(String.valueOf(adapter.getData().size()));
    }

    public void startProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void stopProgress() {
        progressBar.setVisibility(View.GONE);
    }

    public void setResult(Double result) {
        this.timeResult = result;
    }

    public void updateRecyclerView() {
        adapter.notifyDataSetChanged();
    }

    public void updateRecyclerData(List<UserEntity> data) {
        adapter.setData(data);
    }

    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}