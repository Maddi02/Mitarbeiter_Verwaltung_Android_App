package com.example.framenttest_2.Entitätsklassen.ExterneSchnittstellen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.framenttest_2.R;

import java.util.List;

import Anwendungsklassen.HilfsfunktionenK;

public class AdminSacharbeiterFortbildungLöschen extends Fragment {

    HilfsfunktionenK hilfsfunktionenK = new HilfsfunktionenK(getContext());
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        hilfsfunktionenK = new HilfsfunktionenK(getContext());
        hilfsfunktionenK.open();



        view = inflater.inflate(R.layout.sachbearbeiter_fortbildung_loeschen, container, false);

        Button loginButton = (Button) view.findViewById(R.id.button);

        Spinner spinnerUsername = (Spinner) view.findViewById(R.id.spinnerUsername);
        List<String> users  = hilfsfunktionenK.getAllNutzer();

        ArrayAdapter<String> adapterUsername = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, users);
        adapterUsername.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsername.setAdapter(adapterUsername);

        Spinner spinnerFortbildung = (Spinner) view.findViewById(R.id.spinnerFortbildung);
        ArrayAdapter<CharSequence> adapterFortbildung = ArrayAdapter.createFromResource(getContext(),
                R.array.Fortbildungen, android.R.layout.simple_spinner_item);
        adapterFortbildung.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFortbildung.setAdapter(adapterFortbildung);

        return view;

    }
}