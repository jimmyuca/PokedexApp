package edu.dami.pokedexapp;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface AuthFormListener {

    //https://developer.android.com/studio/write/annotations.html#enum-annotations
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ACTION_SIGNIN, ACTION_SIGNUP})
    public @interface AuthActionType {}

    public static int ACTION_SIGNIN = 1;
    public static int ACTION_SIGNUP = 2 ;

    public void onSignInSubmit(int pin);
    public void onSignUpSubmit(String fullname, int pin);
}
