package com.mulia.rafif.mybetadroidz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView clickRegister;
    private EditText edtEmail, edtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);

        initializeUI_Email();

        clickRegister = findViewById(R.id.click_register);
        clickRegister.setOnClickListener(this);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                boolean isEmptyFields = true;

                if (TextUtils.isEmpty(email)) {
                    isEmptyFields = false;
                    edtPassword.setError("email wajib diisi");
                }
                else if (TextUtils.isEmpty(password)) {
                    isEmptyFields = false;
                    edtPassword.setError("password wajib diisi");
                }

                if (isEmptyFields) {
                    String type = "login";
                    BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                    backgroundWorker.execute(type, email, password);
                }
                break;
            case R.id.click_register:
                Intent keRegister = new Intent(this, RegisterActivity.class);
                startActivity(keRegister);
                break;
        }
    }

    public void initializeUI_Email() {
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // do ...
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do ...
            }

            @Override
            public void afterTextChanged(Editable edTable) {
                is_valid_email(edtEmail);
            }

            public void is_valid_email(EditText edText) {
                if (edText.getText().toString() == null) {
                    edText.setError("Invalid Email Address");
                }
                else if (isEmailValid(edText.getText().toString()) == false) {
                    edText.setError("Invalid Email Address");
                }
            }

            boolean isEmailValid(CharSequence email) {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
            }
        });
    }
}
