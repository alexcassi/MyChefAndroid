package com.example.miky.mychef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import preferenze.Sessione;
import utilities.Utilities;

public class FRAG_MENU_BAR extends Fragment {

    public FRAG_MENU_BAR() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.barra_menu, container, false);

        ImageButton profiloBT = (ImageButton) rootView.findViewById(R.id.profile_menuBT);
        profiloBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*FragmentActivity fragmentActivity = (Home)getActivity();
                ((Home) fragmentActivity).goToHome(view);*/
            }
        });

        ImageButton logoBT = (ImageButton) rootView.findViewById(R.id.logo_menuBT);
        logoBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*FragmentActivity fragmentActivity = (Home)getActivity();
                ((Home) fragmentActivity).goToHome(view);*/
            }
        });

        ImageButton homeBT = (ImageButton) rootView.findViewById(R.id.home_menuBT);
        homeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHome(view);
            }
        });

        return rootView;
    }

    public void goToHome(View view){
        Intent intent = new Intent(view.getContext(),Home.class);
        view.getContext().startActivity(intent);
    }

}
