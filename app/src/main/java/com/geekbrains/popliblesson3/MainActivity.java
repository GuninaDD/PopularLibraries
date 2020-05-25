package com.geekbrains.popliblesson3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 100;
    private static final String IMAGE_FOLDER = "/image_converter_results";
    Disposable disposable;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_select).setOnClickListener((view) -> {
            Intent intent = new Intent()
                    .setType("*/*")
                    .setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select a file"),
                    REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != REQUEST_CODE || resultCode != RESULT_OK) return;
        PermissionChecker checker = new PermissionChecker(this);
        checker.executeWithPermission(() -> {
            Single observable = Single.create((emitter) -> {
                Uri selectedFile = Objects.requireNonNull(data).getData();
                Bitmap bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedFile);
                String root = Environment.getExternalStorageDirectory().toString();
                File myDir = new File(root + IMAGE_FOLDER);
                if (!myDir.exists()) {
                    myDir.mkdirs();
                }
                try {
                    Thread.sleep(10000);
                }
                catch (InterruptedException e){
                    return;
                }
                FileOutputStream out = new FileOutputStream(new File(String.format("%s%s/result.png", root, IMAGE_FOLDER)));
                bm.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
                emitter.onSuccess(true);
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

            disposable = observable.subscribe(
                    (result) -> {
                        if (alertDialog.isShowing())
                    alertDialog.cancel();
                        Toast.makeText(MainActivity.this, "Complete", Toast.LENGTH_LONG).show();
                    },
                    (e) -> {
                        if (alertDialog.isShowing()) alertDialog.cancel();
                        Toast.makeText(MainActivity.this, "Something wet wrong" + ((Throwable)e).getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
            );
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Convert in go...")
                    .setNegativeButton("Cancel", (dialog, id) -> {
                        if (!disposable.isDisposed()) disposable.dispose();
                        dialog.cancel();
                    });
            alertDialog = builder.create();
            alertDialog.show();
        }
        );
    }
}
