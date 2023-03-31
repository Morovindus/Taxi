package com.example.taxi;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.taxi.databinding.ActivityMain2Binding;
import com.example.taxi.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {

    ActivityResultLauncher<Intent> startSecondActivityForResult = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent intent = result.getData();
                                if (intent != null){
                                    String Street1 = intent.getStringExtra("Street1");
                                    String Street2 = intent.getStringExtra("Street2");
                                    String Home1 = intent.getStringExtra("Home1");
                                    String Home2 = intent.getStringExtra("Home2");
                                    String Flat1 = intent.getStringExtra("Flat1");
                                    String Flat2 = intent.getStringExtra("Flat2");
                                    binding.Path.setText("Такси прибудет к " + Street1 + "," + Home1 + ","
                                    + Flat1 + "через 5 минут и отвезет вас на " + Street2 + "," + Home2 + ","
                                    + Flat2 + ", если вы согласны, нажмите Вызов такси");
                                    binding.buttonCall.setEnabled(true);
                                }
                            }
                            else {
                                String textError = "Error!";
                                binding.Path.setText(textError);
                            }
                        }
                    }
            );
    private ActivityMain2Binding binding;
    String NameOriginal, SurnameOriginal;
    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Log.d(TAG, "MainActivity2: Create");
        Toast toast = Toast.makeText(this, "Ждите такси. Удачи!", Toast.LENGTH_SHORT);

        Intent intent = getIntent();
        binding.buttonCall.setEnabled(false);

        NameOriginal = intent.getStringExtra("Name");
        SurnameOriginal = intent.getStringExtra("Surname");
        binding.Name.setText(NameOriginal + " " + SurnameOriginal);
        binding.Telephone.setText(intent.getStringExtra("Telephone"));

        intent = new Intent("android.intent.action.ThirdActivity");
        Intent finalIntent = intent;
        binding.buttonPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSecondActivityForResult.launch(finalIntent);
            }
        });

        binding.buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        Log.d(TAG, "MainActivity2: Destroy");
        super.onDestroy();
    }
}