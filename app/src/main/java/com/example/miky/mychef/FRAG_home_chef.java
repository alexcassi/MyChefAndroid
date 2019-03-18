package com.example.miky.mychef;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import preferenze.Sessione;
import utilities.Utilities;

public class FRAG_home_chef extends Fragment {

    public FRAG_home_chef() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_frag_home_chef, container, false);

        TextView welcome = rootView.findViewById(R.id.welcome);
        welcome.setText("Benvenuto " + Sessione.getSessionUserName(getContext()) + " !");

        Button avviaSchermataRicette = (Button)rootView.findViewById(R.id.button_ricette);
        avviaSchermataRicette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentActivity fragmentActivity = (Home)getActivity();
                ((Home) fragmentActivity).goToRicette();
            }
        });

        Button avviaSchermataLogout = (Button)rootView.findViewById(R.id.logout);
        avviaSchermataLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity fragmentActivity = (Home)getActivity();
                ((Home) fragmentActivity).goToLogout();
            }
        });

        Utilities.buttonEffect(avviaSchermataLogout);
        Utilities.buttonEffect(avviaSchermataRicette);


        return rootView;
    }

}
