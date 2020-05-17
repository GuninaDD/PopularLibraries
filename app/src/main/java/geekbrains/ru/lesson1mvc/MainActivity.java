package geekbrains.ru.lesson1mvc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener{
    private Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCounter1 = findViewById(R.id.btnCounter1);
        Button btnCounter2 = findViewById(R.id.btnCounter2);
        Button btnCounter3 = findViewById(R.id.btnCounter3);
        btnCounter1.setOnClickListener(this);
        btnCounter2.setOnClickListener(this);
        btnCounter3.setOnClickListener(this);
        mPresenter = new Presenter(this);
    }
    @Override
    public void onClick(View v) {
        mPresenter.buttonClick(v.getId());
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void setButtonText(int btnIndex, int value) {
        Button currentButton = findViewById(btnIndex);
        currentButton.setText("Количество = " + value);
    }
}


