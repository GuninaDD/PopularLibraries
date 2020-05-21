package com.geekbrains.popularlibrarieslesson2.watcher;

import android.text.Editable;
import android.text.TextWatcher;

public interface SimpleWatcher extends TextWatcher {
    @Override
    default void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    default void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    void afterTextChanged(Editable s);
}
