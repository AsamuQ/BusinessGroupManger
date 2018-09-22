package com.example.businessgroupmanger;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.businessgroupmanger.db.AccountDAO;
import com.example.businessgroupmanger.tables.Account;

public class LoginActivity extends Activity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnGoRegister;
    private CheckBox rem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        init();

        //默认不记住，上一次选择记住后，本次登陆自动填写表单
        boolean isRemenber=pref.getBoolean("remember_password",false);
        if (isRemenber){
            String username=pref.getString("username","");
            String password=pref.getString("password","");
            etUsername.setText(username);
            etPassword.setText(password);
            rem.setChecked(true);       //置为已选中状态
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btnGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goRegister();
            }
        });
    }

    public void init(){
        etUsername=(EditText)findViewById(R.id.et_username);
        etPassword=(EditText)findViewById(R.id.et_password);
        btnLogin=(Button)findViewById(R.id.btnlogin);
        btnGoRegister=(Button)findViewById(R.id.btngoregister);
        rem=(CheckBox)findViewById(R.id.remember);
    }

    public void login(){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        AccountDAO accountDAO=new AccountDAO(this);
        Account account=accountDAO.find(username);

        if (account==null){
            Toast.makeText(this,"不存在该用户！",Toast.LENGTH_SHORT).show();
        }else if (!account.getPassword().equals(password)){
            Toast.makeText(this,"密码错误!",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
        }

        editor=pref.edit();
        if(rem.isChecked()){
            editor.putBoolean("remember_password",true);
            editor.putString("username",username);
            editor.putString("password",password);
        }else {
            editor.clear();
        }
        editor.apply();
    }

    private void goRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
