package com.example.framenttest_2.Entitätsklassen.ExterneSchnittstellen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.framenttest_2.R;

import Anwendungsklassen.AdminErstellenSachbearbeiterK;
import Anwendungsklassen.HilfsfunktionenK;
import Anwendungsklassen.SachbearbeiterSachbearbeiterFortbildungZuordnenK;
import Entitätsklassen.SacharbeiterEK;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AdminSachbearbeiterErstellen extends Fragment {


    View view;

    SacharbeiterEK sacharbeiterEK = new SacharbeiterEK();
    HilfsfunktionenK hilfsfunktionenK = new HilfsfunktionenK(getContext());
    SachbearbeiterSachbearbeiterFortbildungZuordnenK sachbearbeiterSachbearbeiterFortbildungZuordnenK;
    AdminErstellenSachbearbeiterK adminErstellenSachbearbeiterK;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        hilfsfunktionenK = new HilfsfunktionenK(getContext());
        hilfsfunktionenK.open();

        adminErstellenSachbearbeiterK = new AdminErstellenSachbearbeiterK(getContext());
        adminErstellenSachbearbeiterK.open();

        sachbearbeiterSachbearbeiterFortbildungZuordnenK = new SachbearbeiterSachbearbeiterFortbildungZuordnenK(getContext());
        sachbearbeiterSachbearbeiterFortbildungZuordnenK.open();

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
                adminErstellenSachbearbeiterK.insert();
                hilfsfunktionenK.transfere(username.getText().toString(),null,null,null,null,null,null,null,null);
                sachbearbeiterSachbearbeiterFortbildungZuordnenK.insert();


            }
        });
        return view;
    }
}
