package com.example.crmfood.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crmfood.BaseActivity;
import com.example.crmfood.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {

    Button loginButton;
    TextInputLayout phoneInputLayout;
    TextInputLayout passwordInputLayout;
    TextInputEditText phoneEditText;
    TextInputEditText passwordEditText;
//    EditText login_ed;
//    EditText password_ed;
    LoginPresenter presenterL = new LoginPresenter(this);
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        initViews();

        sharedPreferences = getApplicationContext().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        Selection.setSelection(phoneEditText.getText(), passwordEditText.getText().length());

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (passwordEditText.getText().length() > 0) {
                    passwordInputLayout.setError(null);
//                    loginButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        loginButton.setOnClickListener(e ->{
            presenterL.login(phoneEditText.getText().toString(), passwordEditText.getText().toString());
            loginButton.setEnabled(false);

            Timer buttonTimer = new Timer();
            buttonTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    runOnUiThread(() -> loginButton.setEnabled(true));
                }
            }, 5000);

            Log.e("phone", phoneEditText.getText().toString());
            Log.e("passw", passwordEditText.getText().toString());
            //                loginButton.setEnabled(false);

        });


    }

    @SuppressLint("CutPasteId")
    private void initViews() {
        loginButton = findViewById(R.id.loginButton);
//        login_ed = findViewById(R.id.login_ed);
//        password_ed = findViewById(R.id.password_ed);
        passwordEditText = findViewById(R.id.password_ed);
        phoneEditText = findViewById(R.id.login_ed);
        phoneInputLayout = findViewById(R.id.phone_text_field);
        passwordInputLayout = findViewById(R.id.password_text_field);
    }

    @Override
    public void showErrorToast() {
        Toast.makeText(this,getString(R.string.no_internet) , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showLoginError() {
        passwordInputLayout.setError(getString(R.string.shr_error_password));
        passwordEditText.setText("");
    }

    @Override
    public void addAuthToken(String authToken) {
        editor.putString("authToken", authToken).commit();
        startActivity(new Intent(LoginActivity.this, BaseActivity.class));
        finish();
    }

    @Override
    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
