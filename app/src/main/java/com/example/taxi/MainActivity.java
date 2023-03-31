package com.example.taxi;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.taxi.databinding.ActivityMainBinding;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    SharedPreferences sPref;
    private static final String TAG = "myLogs";
    String name, surname, telephone, savedTelephone, savedName, savedSurname;
    Toast toast;
    Intent intent;
    Integer k = 0, c = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Log.d(TAG, "MainActivity: Create");
        intent = new Intent(this, MainActivity2.class);
        toast = Toast.makeText(this, "Неверно введены данные !", Toast.LENGTH_SHORT);

        sPref = getPreferences(MODE_PRIVATE);

        if (!sPref.getString("name", "").isEmpty()) {
            savedTelephone = sPref.getString("telephone", "");
            savedName = sPref.getString("name", "");
            savedSurname = sPref.getString("surname", "");
            binding.buttonRegistration.setText("Войти");
            k = 1;
        }
    }

    public void OnClickRegistration(View view) {
        c = 0;
        if (binding.Telephone.getText().toString().isEmpty()){
            binding.Telephone.setHint("Обязательно заполните это поле"); c = 1;
        }
        if (binding.Name.getText().toString().isEmpty()){
            binding.Name.setHint("Обязательно заполните это поле"); c = 1;
        }
        if (binding.Surname.getText().toString().isEmpty()){
            binding.Surname.setHint("Обязательно заполните это поле"); c = 1;
        }
        if (c != 0)
            return;
        if (k == 1) {
            if (!(new String(savedTelephone).equals(binding.Telephone.getText().toString()) &&
                    new String(savedName).equals(binding.Name.getText().toString()) &&
                    new String(savedSurname).equals(binding.Surname.getText().toString()))) {
                toast.show();
            }
            else{
                name = binding.Name.getText().toString();
                surname = binding.Surname.getText().toString();
                telephone = binding.Telephone.getText().toString();
                intent.putExtra("Telephone", binding.Telephone.getText().toString());
                intent.putExtra("Name", binding.Name.getText().toString());
                intent.putExtra("Surname", binding.Surname.getText().toString());
                startActivity(intent);
            }
        } else{
            name = binding.Name.getText().toString();
            surname = binding.Surname.getText().toString();
            telephone = binding.Telephone.getText().toString();
            intent.putExtra("Telephone", binding.Telephone.getText().toString());
            intent.putExtra("Name", binding.Name.getText().toString());
            intent.putExtra("Surname", binding.Surname.getText().toString());
            startActivity(intent);
        }
    }

    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("telephone", telephone);
        editor.putString("name", name);
        editor.putString("surname", surname);
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        saveText();
        Log.d(TAG, "MainActivity: Destroy");
        super.onDestroy();
    }
}