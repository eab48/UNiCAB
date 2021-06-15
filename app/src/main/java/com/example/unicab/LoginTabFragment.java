package com.example.unicab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LoginTabFragment extends Fragment {


    EditText mEmail, mPassword;
    TextView mForgetPassword;
    Button mLogin;
    float v=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);


        mEmail = root.findViewById(R.id.email);
        mPassword = root.findViewById(R.id.password);
        mForgetPassword = root.findViewById(R.id.forget_password);
        mLogin = root.findViewById(R.id.login);

        mEmail.setTranslationX(800);
        mPassword.setTranslationX(800);
        mForgetPassword.setTranslationX(800);
        mLogin.setTranslationX(800);

        mEmail.setAlpha(v);
        mPassword.setAlpha(v);
        mForgetPassword.setAlpha(v);
        mLogin.setAlpha(v);

        mEmail.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        mPassword.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        mForgetPassword.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        mLogin.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return root;
    }
}
