package com.example.miky.mychef;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import utilities.Utilities;

public class FRAG_home_not_logged extends Fragment {

    public FRAG_home_not_logged() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_frag_home_not_logged, container, false);

        Button avviaSchermataLogin = (Button)rootView.findViewById(R.id.login);
        avviaSchermataLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentActivity fragmentActivity = (Home)getActivity();
                ((Home) fragmentActivity).goToLogin();
            }
        });

        Button avviaSchermataSignup = (Button)rootView.findViewById(R.id.signup);
        avviaSchermataSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity fragmentActivity = (Home)getActivity();
                ((Home) fragmentActivity).goToSignup();
            }
        });

        Utilities.buttonEffect(avviaSchermataLogin);
        Utilities.buttonEffect(avviaSchermataSignup);

        return rootView;
    }

}
