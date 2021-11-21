package com.example.framenttest_2.Entitätsklassen.ExterneSchnittstellen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.framenttest_2.R;

import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.List;

import Anwendungsklassen.HilfsfunktionenK;
import Anwendungsklassen.OpenAllFortbildungen;
import Anwendungsklassen.OpenFortbildungenUser;

public class AdminSacharbeiterFortbildungZuordnungASS extends Fragment {

    HilfsfunktionenK hilfsfunktionenK = new HilfsfunktionenK(getContext());
    OpenFortbildungenUser openFortbildungenUser;
    OpenAllFortbildungen openAllFortbildungen;
    View view;
    String selectedUser;
    String selectFortbildung;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.sacharbeiter_fortbildung_zuweisen_aas, container, false);

        hilfsfunktionenK = new HilfsfunktionenK(getContext());
        hilfsfunktionenK.open();

        openFortbildungenUser = new OpenFortbildungenUser(getContext());
        openFortbildungenUser.open();

        openAllFortbildungen = new OpenAllFortbildungen(getContext());
        openAllFortbildungen.open();

        Button set = (Button) view.findViewById(R.id.button);

        RadioButton bestanden = (RadioButton) view.findViewById(R.id.bestanden);
        RadioButton beleget = (RadioButton) view.findViewById(R.id.belegt);

        Spinner spinnerUsername = (Spinner) view.findViewById(R.id.spinnerUsername);
        List<String> user = hilfsfunktionenK.getAllNutzer();
        ArrayAdapter<String> adapterUsername = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, user);
        adapterUsername.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsername.setAdapter(adapterUsername);

        Spinner spinnerFortbildung = (Spinner) view.findViewById(R.id.spinnerFortbildung);
        List<String> Fortbildungen = openAllFortbildungen.getAllFortbildungen();
        ArrayAdapter<String> adapterFortbildung = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, Fortbildungen);
        adapterFortbildung.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFortbildung.setAdapter(adapterFortbildung);


        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = false;
                if (bestanden.isChecked())
                    success = openFortbildungenUser.setFortbildungen(selectedUser, selectFortbildung, bestanden.getText().toString());

                if (beleget.isChecked())
                    success = openFortbildungenUser.setFortbildungen(selectedUser, selectFortbildung, beleget.getText().toString());


                if (!success) {
                    Toast.makeText(getContext(), "Fortbildung " + selectFortbildung + " konnte nicht gesetzt werden, da nicht alle Vorraussetzung erfüllt wurden", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Fortbildung " + selectFortbildung + " wurde gesetzt", Toast.LENGTH_SHORT).show();
                }
            }

        });


        spinnerUsername.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @SuppressLint("ResourceType")
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                selectedUser = user.get(position);
                Toast.makeText(getContext(), "You selected: " + selectedUser, Toast.LENGTH_LONG).show();
                getString(R.id.allFortbildungen, openFortbildungenUser.getAllFortbildungenForUser(selectedUser), 0);
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


        return view;


    }
}
