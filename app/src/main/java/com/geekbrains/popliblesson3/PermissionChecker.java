package com.geekbrains.popliblesson3;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.Collections;
import java.util.List;

public class PermissionChecker implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final int PERMISSION_REQUEST_CODE = 5;
    private Activity activity;
    private Runnable callback;
    private List<String> permissions;

    PermissionChecker(Activity activity) {
        this.activity = activity;
    }

    void executeWithPermission(Runnable runnable) {
        this.permissions = Collections.singletonList(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        callback = runnable;
        innerExecute();
    }

    private boolean checkPermissions() {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) !=
                    PackageManager.PERMISSION_GRANTED) return false;
        }
        return true;
    }

    private void innerExecute() {
        if (checkPermissions()) {
            callback.run();
        } else {
            requestForCallPermission();
        }
    }

    private void requestForCallPermission() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    },
                    PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED ||
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                innerExecute();
            }
        }
    }
}
