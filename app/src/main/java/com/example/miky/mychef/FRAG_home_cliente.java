package com.example.miky.mychef;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FRAG_home_cliente extends Fragment {

    public FRAG_home_cliente() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_frag_home_cliente, container, false);

        Button avviaSchermataRicerca = (Button)rootView.findViewById(R.id.ricerca);
        avviaSchermataRicerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentActivity fragmentActivity = (home)getActivity();
                ((home) fragmentActivity).goToCercaChef();
            }
        });

        Button avviaSchermataLogout = (Button)rootView.findViewById(R.id.logout);
        avviaSchermataLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity fragmentActivity = (home)getActivity();
                ((home) fragmentActivity).goToLogout();
            }
        });

        return rootView;
    }
}
