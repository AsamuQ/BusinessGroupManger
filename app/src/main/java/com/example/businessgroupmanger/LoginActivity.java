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

import com.example.businessgroupmanger.admin.FunctionActivity;
import com.example.businessgroupmanger.db.AccountDAO;
import com.example.businessgroupmanger.tables.Account;
import com.example.businessgroupmanger.user.AllGoodsActivity;

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
            Toast.makeText(this,"登陆成功，欢迎"+username+"!",Toast.LENGTH_SHORT).show();
            if (username.equals("admin")){
                //进入管理员界面(职员变动、修改店铺信息、修改商品信息)
                startActivity(new Intent(getApplicationContext(), FunctionActivity.class));
            }else {
                //进入用户界面(仅查看功能；显示所有商品(SALE表))
                startActivity(new Intent(getApplicationContext(), AllGoodsActivity.class));
            }
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
