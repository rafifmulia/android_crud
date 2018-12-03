package com.mulia.rafif.mybetadroidz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsername,edtEmail,edtPassword,edtAlamat;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername = findViewById(R.id.edt_email);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        edtAlamat = findViewById(R.id.edt_alamat);

        initializeUI_Email();

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                String username = edtUsername.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String alamat = edtAlamat.getText().toString();

                boolean isEmptyFields = true;

                if (TextUtils.isEmpty(username)) {
                    isEmptyFields = false;
                    edtUsername.setError("username wajib diisi");
                }
                else if (TextUtils.isEmpty(email)) {
                    isEmptyFields = false;
                    edtPassword.setError("email wajib diisi");
                }
                else if (TextUtils.isEmpty(password)) {
                    isEmptyFields = false;
                    edtPassword.setError("password wajib diisi");
                }
                else if (TextUtils.isEmpty(alamat)) {
                    isEmptyFields = false;
                    edtAlamat.setError("alamat wajib diisi");
                }

                if (isEmptyFields) {
                    String type = "register";
                    BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                    backgroundWorker.execute(type, username, email, password, alamat);
                }
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
