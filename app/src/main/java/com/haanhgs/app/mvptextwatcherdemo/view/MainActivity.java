package com.haanhgs.app.mvptextwatcherdemo.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.haanhgs.app.mvptextwatcherdemo.R;
import com.haanhgs.app.mvptextwatcherdemo.presenter.Presenter;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvOutput)
    TextView tvOutput;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.pbrProgressBarMain)
    ProgressBar pbrProgressBarMain;

    private Presenter presenter;

    private void initPresenter(){
        presenter = new Presenter(string -> tvOutput.setText(string));
    }

    private void handleName(){
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.onNameEditText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                pbrProgressBarMain.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void handleEmail(){
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.onEmailEditText(s.toString());
            }

            @Override public void afterTextChanged(Editable s) {}
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        pbrProgressBarMain.setVisibility(View.VISIBLE);
        initPresenter();
        handleName();
        handleEmail();
    }
}
