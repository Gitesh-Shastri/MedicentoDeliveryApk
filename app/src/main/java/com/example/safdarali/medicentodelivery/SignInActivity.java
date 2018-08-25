package com.example.safdarali.medicentodelivery;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.safdarali.medicentodelivery.data.Constants;

public class SignInActivity extends AppCompatActivity {

    ImageView mLogo;
    String mEmail;
    String mPassword;
    Animation mAnimation;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mLogo = findViewById(R.id.medicento_logo);
        final EditText emailEditTv = findViewById(R.id.email_edit_tv);
        final EditText passwordEditTv = findViewById(R.id.password_edit_tv);
        Button btn = findViewById(R.id.sign_in_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmail = emailEditTv.getText().toString();
                mPassword = passwordEditTv.getText().toString();
                if (mEmail.isEmpty() || mPassword.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "Please enter data for sign in!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!mEmail.matches("\\w+[@]\\w+[\\.]\\w+")) {
                    Toast.makeText(SignInActivity.this, "Email is not vaid", Toast.LENGTH_SHORT).show();
                    return;
                }
                //mProgressBar.setVisibility(View.VISIBLE);
                //getLoaderManager().initLoader(Constants.LOG_IN_LOADER_ID, null, SignInActivity.this);
                Intent intent = new Intent(SignInActivity.this, PasswordChangeActivity.class);
                startActivityForResult(intent, Constants.CHANGE_PASSWORD);

            }
        });
        mAnimation = new AlphaAnimation(1, 0);
        mAnimation.setDuration(2000);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);
        mLogo.startAnimation(mAnimation);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.CHANGE_PASSWORD) {
            if (resultCode == RESULT_OK) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(Constants.EMAIL, mEmail);
                Intent intent = new Intent();
                if (getParent() == null) {
                    setResult(Activity.RESULT_OK, intent);
                } else {
                    getParent().setResult(RESULT_OK);
                }
                editor.apply();
                Log.v("Saf", "here" + mEmail);
                finish();
            }
        } else {
            Toast.makeText(this, "Password change unsuccessful!!", Toast.LENGTH_SHORT).show();
        }
    }
}
