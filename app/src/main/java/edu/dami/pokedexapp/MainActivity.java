package edu.dami.pokedexapp;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, AuthFormListener {

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
        if(buttonId == R.id.btn_signin) {
            loadFragment(SignInFragment.newInstance());
            return;
        }
        if(buttonId == R.id.btn_signup) {
            loadFragment(SignUpFragment.newInstance());
            return;
        }

        throw new IllegalArgumentException("No se encontró la acción requerida");
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.ph_form, fragment);
        transaction.commit();
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignInSubmit(int pin) {
        showConfirmationFragment(ACTION_SIGNIN);
    }

    @Override
    public void onSignUpSubmit(String fullname, int pin) {
        showConfirmationFragment(ACTION_SIGNUP);
    }

    private void showConfirmationFragment(@AuthActionType int actionType) {
        String message;
        switch (actionType) {
            case AuthFormListener.ACTION_SIGNIN:
                message = "¡Hola nuevamente!";
                break;
            case AuthFormListener.ACTION_SIGNUP:
                message = "¡Hola por primera vez!";
                break;
            default:
                message = "";
        }

        FragmentManager frgManager = getSupportFragmentManager();
        ConfirmationFragment frg = ConfirmationFragment.newInstance(message);
        frg.show(frgManager, "frg_confirm");
    }
}