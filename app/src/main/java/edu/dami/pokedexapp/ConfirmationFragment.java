package edu.dami.pokedexapp;

import android.graphics.Point;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmationFragment extends DialogFragment {

    private static final String ARG_MESSAGE = "message";

    private String mMessage;

    public ConfirmationFragment() {
        // Required empty public constructor
    }

    public static ConfirmationFragment newInstance(String message) {
        ConfirmationFragment fragment = new ConfirmationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        //aplicar estilo que expande ancho y alto a minimo el 50% de la pantalla
        fragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMessage = getArguments().getString(ARG_MESSAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_confirmation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setup(view);
    }

    private void setup(View view) {
        TextView tvMessage = view.findViewById(R.id.tv_message);
        tvMessage.setText(mMessage);

        Button btnOk = view.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedView) {
                dismiss();
            }
        });
    }
}