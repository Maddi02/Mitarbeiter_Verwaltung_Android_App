package com.example.framenttest_2.Entitätsklassen.ExterneSchnittstellen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.framenttest_2.R;

import java.util.List;

import Anwendungsklassen.AdminSachbearbeiterLöschenK;
import Anwendungsklassen.HilfsfunktionenK;
import Anwendungsklassen.InitializiseDropDown;

public class AdminSachbearbeiterLöschenAAS extends Fragment {

    HilfsfunktionenK hilfsfunktionenK = new HilfsfunktionenK(getContext());
    AdminSachbearbeiterLöschenK adminSachbearbeiterLöschenK;
    InitializiseDropDown initializiseDropDown;
    View view;
    String selectedUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        hilfsfunktionenK = new HilfsfunktionenK(getContext());
        hilfsfunktionenK.open();

        adminSachbearbeiterLöschenK = new AdminSachbearbeiterLöschenK(getContext());
        adminSachbearbeiterLöschenK.open();

        initializiseDropDown = new InitializiseDropDown(getContext());
        initializiseDropDown.open();

        List<String> users = initializiseDropDown.getAllNutzer();
        view = inflater.inflate(R.layout.admin_sachbearbeiter_loeschen_ass, container, false);

        Button loginButton = (Button) view.findViewById(R.id.button);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerUsername);
        ArrayAdapter<String> adapterUsername = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, users);
        adapterUsername.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterUsername);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                selectedUser = users.get(position);
                Toast.makeText(getContext(), "You selected: " + selectedUser,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminSachbearbeiterLöschenK.delete(selectedUser);
                Toast.makeText(getContext(), "You deleted: " + selectedUser,Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
