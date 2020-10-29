package edu.dami.pokedexapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpFragment extends Fragment {

    private AuthFormListener authFormListener;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AuthFormListener){
            authFormListener = (AuthFormListener) context;
        } else {
            throw new ClassCastException("La actividad tiene que implementar la interfaz AuthFormListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setup(view);
    }

    private void setup(View view) {
        Button btnNext = view.findViewById(R.id.btn_next);
        TextInputLayout tilFullname = view.findViewById(R.id.til_fullname);
        TextInputLayout tilPin = view.findViewById(R.id.til_pin);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {
                if(tilFullname.getEditText() != null && tilPin.getEditText() != null) {
                    String strPin = tilPin.getEditText().getText().toString();
                    String fullname = tilFullname.getEditText().getText().toString();

                    if(!TextUtils.isEmpty(fullname) && !TextUtils.isEmpty(strPin)) {
                        int pin = Integer.parseInt(strPin);
                        authFormListener.onSignUpSubmit(fullname, pin);
                    }

                }
            }
        });
    }
}