package com.example.framenttest_2.Entitätsklassen.ExterneSchnittstellen;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
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
import Entitätsklassen.SacharbeiterEK;


public class AdminSachbearbeiterEditerenAAS extends Fragment {

    HilfsfunktionenK hilfsfunktionenK = new HilfsfunktionenK(getContext());
    AdminEditerenSachbeabreiterK adminEditerenSachbeabreiterK;
    InitializiseDropDown initializiseDropDown;
    String role = "";
    String selectedUser;
    TextView textView;

    View view;

    @SuppressLint("Range")
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
        TextView password = (TextView) view.findViewById(R.id.passwordInput);
        Button loginButton = (Button) view.findViewById(R.id.button);
         textView = (TextView) view.findViewById(R.id.LabelFortbildungenEditeren);

        RadioButton adminRadio = (RadioButton) view.findViewById(R.id.adminRadio);
        RadioButton sacharbeiterRadio = (RadioButton) view.findViewById(R.id.sachbearbeiterRadio);

        if(!LehrVeranstaltungHS.savedRoleLogin().equals("Admin")) {
            adminRadio.setEnabled(false);
            sacharbeiterRadio.setEnabled(false);
        }

        ArrayAdapter<String> adapterUsername = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, users);
        adapterUsername.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterUsername);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                HilfsfunktionenK hilfsfunktionenK = new HilfsfunktionenK(getContext());
                hilfsfunktionenK.open();
                selectedUser = users.get(position);
                if(selectedUser == null)
                {
                    selectedUser = "a";
                }
                Cursor cursor = hilfsfunktionenK.gibSacharbeiter(selectedUser);
                String user_personel = " ";
                String password_personel = " ";
                String role_personel = " ";
                if(cursor.moveToFirst())
                {
                    user_personel = cursor.getString(cursor.getColumnIndex("username"));
                    password_personel = cursor.getString(cursor.getColumnIndex("passwort"));
                    role_personel= cursor.getString(cursor.getColumnIndex("role"));
                }

                username.setText(user_personel);
                password.setText(password_personel);

                if(role_personel.equals("Admin"))
                {
                    adminRadio.setChecked(true);
                }
                else{
                    sacharbeiterRadio.setChecked(true);
                }
                if(hilfsfunktionenK != null) {
                    textView.setText("Bestandene / Belegte Fortbildungen  \n" + hilfsfunktionenK.getAllFortbildungenForUser(selectedUser));
                }
                Toast.makeText(getContext(), "You selected: " + selectedUser, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    if (adminRadio.isChecked()) {
                        role = adminRadio.getText().toString();
                    }
                    if (!adminRadio.isChecked()) {
                        role = "Sachbearbeiter";
                    }
                    if(!LehrVeranstaltungHS.savedRoleLogin().equals("Admin"))
                    {
                        Cursor cursor = hilfsfunktionenK.gibSacharbeiter(selectedUser);
                        if (cursor.moveToFirst()){
                            role = cursor.getString(cursor.getColumnIndex("role"));
                        }
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
