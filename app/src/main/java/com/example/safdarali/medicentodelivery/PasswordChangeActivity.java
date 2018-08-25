package com.example.safdarali.medicentodelivery;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordChangeActivity extends AppCompatActivity {

    ImageView mTick;
    String mOldPassword, mNewPassword, mConfirmPassword;
    TextView mErrorTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);
        EditText oldPassEditTv = findViewById(R.id.old_password);
        final EditText newPassEditTv = findViewById(R.id.new_password);
        final EditText confirmPassEditTv = findViewById(R.id.confirm_password);
        mTick = findViewById(R.id.tick);
        mErrorTv = findViewById(R.id.error_tv);
        final Button btn = findViewById(R.id.btn);

        newPassEditTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mNewPassword = charSequence.toString();
                if (mNewPassword.equals(mConfirmPassword)){
                    showPasswordMatch();
                } else {
                    showPasswordMatchError();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        confirmPassEditTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mConfirmPassword = charSequence.toString();
                if (mNewPassword.equals(mConfirmPassword)) {
                    showPasswordMatch();
                } else {
                    showPasswordMatchError();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mNewPassword.equals(mConfirmPassword)) {
                    Intent intent = new Intent();
                    if (getParent() == null) {
                        setResult(Activity.RESULT_OK, intent);
                    } else {
                        getParent().setResult(RESULT_OK);
                    }
                    finish();
                } else {
                    Toast.makeText(PasswordChangeActivity.this, "Please match both the passwords!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showPasswordMatchError() {
        mTick.setBackground(getDrawable(R.drawable.ic_cross));
        mTick.setVisibility(View.VISIBLE);
        mErrorTv.setVisibility(View.VISIBLE);
    }

    private void showPasswordMatch() {
        mTick.setBackground(getDrawable(R.drawable.ic_tick));
        mTick.setVisibility(View.VISIBLE);
        mErrorTv.setVisibility(View.INVISIBLE);
    }
}
