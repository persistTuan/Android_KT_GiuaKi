package com.example.tlu.cse.ht63.coffeeshop.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tlu.cse.ht63.coffeeshop.Models.User;
import com.example.tlu.cse.ht63.coffeeshop.R;
import com.example.tlu.cse.ht63.coffeeshop.Reponsitory.UserReponsitory;
import com.example.tlu.cse.ht63.coffeeshop.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private UserReponsitory reponsitory;
    private  boolean isEmployee = false;
    private ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        reponsitory = new UserReponsitory(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSignUp.setOnClickListener(v -> {
           createUser();
        });

        binding.btnSignIn.setOnClickListener(v -> {
           this.finish();
        });
        binding.employee.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isEmployee = isChecked;
        });
        binding.customer.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isEmployee = !isChecked;
        });
    }

    private void createUser() {
        String fullName = binding.fullName.getEditText().getText().toString().trim();
        String userName = binding.userName.getEditText().getText().toString().trim();
        String password = binding.password.getEditText().getText().toString().trim();

        User user = new User(fullName, userName, password, isEmployee);
        reponsitory.insert(user);
        Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show();
        this.finish();



    }
}