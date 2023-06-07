package com.example.strivelifeapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;

import com.example.strivelifeapplication.ui.home.Task;
import com.example.strivelifeapplication.ui.home.TaskAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.strivelifeapplication.databinding.ActivityMainBinding;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements LoginDialog.ExampleDialogListener{

    private ActivityMainBinding binding;
    private EditText editTextUsername;
    private EditText editTextPassword;
    TextView textview; // 把視圖的元件宣告成全域變數
    String result; // 儲存資料用的字串
    String User_name, User_pass, login_User_name, login_User_pass;
    Button btn_login, btn_register;
    private final static String TAG = "HTTPURLCONNECTION test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐藏标题栏
        //login or register
        LoginDialog Dialog = new LoginDialog();
        Dialog.show(getSupportFragmentManager(), "Login Dialog");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public void applyTexts(String username, String password) {
        User_name = username;
        User_pass = password;
        Log.v("REGISTER_test", "ID:"+User_name+" PASS:"+User_pass);
    }

}