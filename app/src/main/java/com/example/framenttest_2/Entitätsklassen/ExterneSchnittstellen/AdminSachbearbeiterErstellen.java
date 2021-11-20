package com.example.framenttest_2.Entitätsklassen.ExterneSchnittstellen;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.framenttest_2.R;
import com.google.android.material.button.MaterialButton;

import Anwendungsklassen.HilfsfunktionenK;
import Anwendungsklassen.OpenAllFortbildungen;
import Anwendungsklassen.OpenFortbildungenUser;
import Entitätsklassen.SacharbeiterEK;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AdminSachbearbeiterErstellen extends Fragment {


    View view;

    SacharbeiterEK sacharbeiterEK = new SacharbeiterEK();
    HilfsfunktionenK hilfsfunktionenK = new HilfsfunktionenK(getContext());
    OpenFortbildungenUser openFortbildungenUser = new OpenFortbildungenUser(getContext());
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        hilfsfunktionenK = new HilfsfunktionenK(getContext());
        hilfsfunktionenK.open();

        openFortbildungenUser = new OpenFortbildungenUser(getContext());
        openFortbildungenUser.open();
        view = inflater.inflate(R.layout.admin_sachbearbeiter_erstellen_aas, container, false);
        TextView username = (TextView) view.findViewById(R.id.usernameInput);
        TextView password = (TextView) view.findViewById(R.id.password_input);
        Button loginButton = (Button) view.findViewById(R.id.button);
        RadioButton adminRadio = (RadioButton) view.findViewById(R.id.adminRadio);

        // Inflate the layout for this fragment



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String role = "";
                if(adminRadio.isChecked())
                {
                    role = adminRadio.getText().toString();
                }
                if(!adminRadio.isChecked())
                {
                    role = "Sachbearbeiter";
                }
                hilfsfunktionenK.transferAnwendungsschicht(username.getText().toString(),password.getText().toString(), role );
                hilfsfunktionenK.insert();
                openFortbildungenUser.transfere(username.getText().toString(),null,null,null,null,null,null,null,null);
                openFortbildungenUser.insert();


            }
        });
        return view;
    }
}
