package com.example.framenttest_2.Entitätsklassen.ExterneSchnittstellen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.framenttest_2.R;

import java.util.List;

import Anwendungsklassen.HilfsfunktionenK;
import Anwendungsklassen.InitializiseDropDown;
import Anwendungsklassen.SachbearbeiterSachbearbeiterFortbildungLöschen;

public class AdminSacharbeiterFortbildungLöschen extends Fragment {

    HilfsfunktionenK hilfsfunktionenK = new HilfsfunktionenK(getContext());

    SachbearbeiterSachbearbeiterFortbildungLöschen sachbearbeiterSachbearbeiterFortbildungLöschen;
    InitializiseDropDown initializiseDropDown;


    View view;
    String selectedUser;
    String selectFortbildung;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        hilfsfunktionenK = new HilfsfunktionenK(getContext());
        hilfsfunktionenK.open();

        initializiseDropDown = new InitializiseDropDown(getContext());
        initializiseDropDown.open();

        sachbearbeiterSachbearbeiterFortbildungLöschen = new SachbearbeiterSachbearbeiterFortbildungLöschen(getContext());
        sachbearbeiterSachbearbeiterFortbildungLöschen.open();





        view = inflater.inflate(R.layout.sachbearbeiter_fortbildung_loeschen, container, false);

        Button loginButton = (Button) view.findViewById(R.id.button);

        Spinner spinnerUsername = (Spinner) view.findViewById(R.id.spinnerUsername);
        List<String> users  = initializiseDropDown.getAllNutzer();

        ArrayAdapter<String> adapterUsername = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, users);
        adapterUsername.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsername.setAdapter(adapterUsername);

        Spinner spinnerFortbildung = (Spinner) view.findViewById(R.id.spinnerFortbildung);
        List<String> Fortbildungen = initializiseDropDown.getAllFortbildungen();
        ArrayAdapter<String> adapterFortbildung = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, Fortbildungen);
        adapterFortbildung.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFortbildung.setAdapter(adapterFortbildung);


        spinnerUsername.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @SuppressLint("ResourceType")
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                selectedUser = users.get(position);
                Toast.makeText(getContext(), "You selected: " + selectedUser, Toast.LENGTH_LONG).show();
                getString(R.id.allFortbildungen, hilfsfunktionenK.getAllFortbildungenForUser(selectedUser), 0);
                TextView textView= (TextView)view.findViewById(R.id.LabelFortbildungen);
                textView.setText("Bestandene / Belegte Fortbildungen  \n" + hilfsfunktionenK.getAllFortbildungenForUser(selectedUser));

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        spinnerFortbildung.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                selectFortbildung = adapterFortbildung.getItem(position);
                Toast.makeText(getContext(), "You selected: " + selectedUser, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = false;
                success = sachbearbeiterSachbearbeiterFortbildungLöschen.deleteFortbildungForUser(selectedUser,selectFortbildung);
                if(success)
                    Toast.makeText(getContext(), "You deleted " + selectFortbildung, Toast.LENGTH_LONG).show();
                else{
                    Toast.makeText(getContext(), "Sie können die Fortbildung  " + selectFortbildung + " nicht löschen, das sie Vorrausetzung für eine andere Fortbildung ist", Toast.LENGTH_LONG).show();
                }
            }
        });









        return view;

    }
}