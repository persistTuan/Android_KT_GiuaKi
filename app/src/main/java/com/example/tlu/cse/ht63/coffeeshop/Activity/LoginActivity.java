package com.example.tlu.cse.ht63.coffeeshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tlu.cse.ht63.coffeeshop.Database.DbUserHelper;
import com.example.tlu.cse.ht63.coffeeshop.Models.User;
import com.example.tlu.cse.ht63.coffeeshop.R;
import com.example.tlu.cse.ht63.coffeeshop.Reponsitory.UserReponsitory;
import com.example.tlu.cse.ht63.coffeeshop.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private UserReponsitory reponsitory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        reponsitory = new UserReponsitory(this);
        // Inside your activity (if you did not enable transitions in your theme)
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        // Set an exit transition
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //binding
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //login
        binding.btnLogin.setOnClickListener(v -> {
            String userName = binding.userName.getEditText().getText().toString();
            String password = binding.password.getEditText().getText().toString();
            User userLogin = reponsitory.getUser(userName, password);
                Log.i("Infor_Login", userName + " " + password);
            if(userLogin != null){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnCreateAcount.setOnClickListener( v -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });
    }
}