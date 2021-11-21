package com.example.framenttest_2.Entitätsklassen.ExterneSchnittstellen;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.framenttest_2.R;

import java.util.List;

import Anwendungsklassen.AdminEditerenSachbeabreiterK;
import Anwendungsklassen.AdminErstellenSachbearbeiterK;
import Anwendungsklassen.HilfsfunktionenK;
import Anwendungsklassen.InitializiseDropDown;


public class AdminSachbearbeiterEditerenAAS extends Fragment {

    HilfsfunktionenK hilfsfunktionenK = new HilfsfunktionenK(getContext());
    AdminEditerenSachbeabreiterK adminEditerenSachbeabreiterK;
    InitializiseDropDown initializiseDropDown;
    String selectedUser;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        hilfsfunktionenK = new HilfsfunktionenK(getContext());
        hilfsfunktionenK.open();

        adminEditerenSachbeabreiterK = new AdminEditerenSachbeabreiterK(getContext());
        adminEditerenSachbeabreiterK.open();


        initializiseDropDown = new InitializiseDropDown(getContext());
        initializiseDropDown.open();

        view = inflater.inflate(R.layout.admin_sachbearbeiter_editieren_aas, container, false);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerUsername);
        List<String> users = initializiseDropDown.getAllNutzer();

        TextView username = (TextView) view.findViewById(R.id.usernameInput);
        TextView password = (TextView) view.findViewById(R.id.password_input);
        Button loginButton = (Button) view.findViewById(R.id.button);
        RadioButton adminRadio = (RadioButton) view.findViewById(R.id.adminRadio);
        RadioButton sacharbeiterRadio = (RadioButton) view.findViewById(R.id.sachbearbeiterRadio);


        ArrayAdapter<String> adapterUsername = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, users);
        adapterUsername.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterUsername);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                selectedUser = users.get(position);
                Toast.makeText(getContext(), "You selected: " + selectedUser, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String role = " ";
                if (adminRadio.isChecked()) {
                    role = adminRadio.getText().toString();
                }
                if (!adminRadio.isChecked()) {
                    role = "Sachbearbeiter";
                }
                hilfsfunktionenK.transferAnwendungsschicht(username.getText().toString() , password.getText().toString(),role);
                hilfsfunktionenK.transfere(username.getText().toString(),null,null,null,null,null,null,null,null);
                adminEditerenSachbeabreiterK.updateSachbearbeiterVerwalung(selectedUser);
                adminEditerenSachbeabreiterK.updateSachbearbeiterFortbildungUsername(selectedUser);
                Toast.makeText(getContext(), "Update was successful :", Toast.LENGTH_LONG).show();
            }
        });
        return view;

    }
}
