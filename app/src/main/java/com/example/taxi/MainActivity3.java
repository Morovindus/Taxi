package com.example.taxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.taxi.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {
    private ActivityMain3Binding binding;
    private static final String TAG = "myLogs";
    Integer c = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Log.d(TAG, "MainActivity2: Create");
        binding.buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = 0;
                if (binding.Street1.getText().toString().isEmpty()){
                    binding.Street1.setHint("Обязательно заполните это поле"); c = 1;
                }
                if (binding.Street2.getText().toString().isEmpty()){
                    binding.Street2.setHint("Обязательно заполните это поле"); c = 1;
                }
                if (binding.Home1.getText().toString().isEmpty()){
                    binding.Home1.setHint("Обязательно заполните это поле"); c = 1;
                }
                if (binding.Home2.getText().toString().isEmpty()){
                    binding.Home2.setHint("Обязательно заполните это поле"); c = 1;
                }
                if (binding.Flat1.getText().toString().isEmpty()){
                    binding.Flat1.setHint("Обязательно заполните это поле"); c = 1;
                }
                if (binding.Flat2.getText().toString().isEmpty()){
                    binding.Flat2.setHint("Обязательно заполните это поле"); c = 1;
                }
                if (c != 0)
                    return;
                Intent intent = new Intent();
                intent.putExtra("Street1", binding.Street1.getText().toString());
                intent.putExtra("Street2", binding.Street2.getText().toString());
                intent.putExtra("Home1", binding.Home1.getText().toString());
                intent.putExtra("Home2", binding.Home2.getText().toString());
                intent.putExtra("Flat1", binding.Flat1.getText().toString());
                intent.putExtra("Flat2", binding.Flat2.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "MainActivity3: Destroy");
        super.onDestroy();
    }
}