//package com.example.crmfood.login;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.Selection;
//import android.text.TextWatcher;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.example.crmfood.R;
//
//public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {
//
//    Button loginButton;
////    TextInputLayout phoneInputLayout;
////    TextInputLayout passwordInputLayout;
////    TextInputEditText phoneEditText;
////    TextInputEditText passwordEditText;
//    EditText login_ed;
//    EditText password_ed;
//    LoginPresenter presenterL = new LoginPresenter(this);
//    SharedPreferences sharedPreferences;
//    SharedPreferences.Editor editor;
//
//    @SuppressLint("CommitPrefEdits")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login_screen);
//        initViews();
//
//        sharedPreferences = getApplicationContext().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();
//
//
//        Selection.setSelection(login_ed.getText(), login_ed.getText().length());
//
//        login_ed.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if (!editable.toString().startsWith("+996")) {
//                    Selection.setSelection(login_ed.getText(), login_ed.getText().length());
//
//                }
//
//            }
//        });
//
//        password_ed.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (password_ed.getText().length() > 0) {
////                    passwordInputLayout.setError(null);
//                    loginButton.setEnabled(true);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                presenterL.login(login_ed.getText().toString(), password_ed.getText().toString());
//                loginButton.setEnabled(false);
//
//            }
//        });
//
//
//    }
//
//    @SuppressLint("CutPasteId")
//    private void initViews() {
//        loginButton = findViewById(R.id.loginButton);
//        login_ed = findViewById(R.id.login_ed);
//        password_ed = findViewById(R.id.login_ed);
////        passwordEditText = findViewById(R.id.passwordEditText);
////        phoneEditText = findViewById(R.id.login_ed);
////        phoneInputLayout = findViewById(R.id.phone_text_field);
////        passwordInputLayout = findViewById(R.id.password_ed);
//    }
//
//    @Override
//    public void showErrorToast() {
//        Toast.makeText(this,getString(R.string.no_internet) , Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void showLoginError() {
////        passwordInputLayout.setError(getString(R.string.shr_error_password));
////        passwordEditText.setText("");
//    }
//
//    @Override
//    public void addAuthToken(String authToken) {
//        editor.putString("authToken", authToken).commit();
//        startActivity(new Intent(LoginActivity.this, BaseActivity.class));
//        finish();
//    }
//
//    @Override
//    public boolean isConnected() {
//        ConnectivityManager cm =
//                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        return netInfo != null && netInfo.isConnectedOrConnecting();
//    }
//}
