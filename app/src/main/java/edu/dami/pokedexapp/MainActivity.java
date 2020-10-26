package edu.dami.pokedexapp;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup() {
        Button btnSignIn = findViewById(R.id.btn_signin);
        btnSignIn.setOnClickListener(this);

        Button btnSignUp = findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(this);

        loadDefaultForm();
    }

    @Override
    public void onClick(View button) {
        try {
            loadForm(button.getId());
        } catch(IllegalArgumentException ex) {
            showMessage(ex.getMessage());
        }
    }

    private void loadDefaultForm() {
        loadFragment(WelcomeFragment.newInstance());
    }

    private void loadForm(@IdRes int buttonId) throws IllegalArgumentException {
        switch(buttonId) {
            case  R.id.btn_signin:
                loadFragment(SignInFragment.newInstance());
                break;
            case R.id.btn_signup:
                loadFragment(SignUpFragment.newInstance());
                break;
            default:
                throw new IllegalArgumentException("No se encontró la acción requerida");
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.ph_form, fragment);
        transaction.commit();
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}