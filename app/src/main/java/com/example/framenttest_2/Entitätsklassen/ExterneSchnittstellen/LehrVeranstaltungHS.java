package com.example.framenttest_2.Entitätsklassen.ExterneSchnittstellen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.framenttest_2.R;
import com.google.android.material.button.MaterialButton;

import Anwendungsklassen.HilfsfunktionenK;
import Datenbank.DatabaseHelperAlleFortbildungen;
import Datenbank.DatabaseHelperSacharbeiterVerwaltung;

public class LehrVeranstaltungHS extends AppCompatActivity {
    private HilfsfunktionenK hilfsfunktionenK;
    private String pwAusDerDatenbank;
    private String usernameAusDerDatenbank;
    private String roleAusDerDatenbank;
    private static RadioButton adminRadio;
    DatabaseHelperAlleFortbildungen databaseHelperAlleFortbildungen;
    DatabaseHelperSacharbeiterVerwaltung sacharbeiterVerwaltung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        hilfsfunktionenK = new HilfsfunktionenK(this);
        hilfsfunktionenK.open();

        super.onCreate(savedInstanceState);

         sacharbeiterVerwaltung = new DatabaseHelperSacharbeiterVerwaltung(this);
         databaseHelperAlleFortbildungen = new DatabaseHelperAlleFortbildungen(this);

        setContentView(R.layout.sachbearbeiter_verwaltung_hs);
        TextView username = (TextView) findViewById(R.id.username);
        TextView password  = (TextView) findViewById(R.id.password);
        MaterialButton loginButton =  (MaterialButton) findViewById(R.id.loginBtn);
         adminRadio = (RadioButton) findViewById(R.id.AdminBotton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                Cursor cursor = hilfsfunktionenK.gibSacharbeiter(username.getText().toString());
                if (cursor.moveToFirst()){

                pwAusDerDatenbank = cursor.getString(cursor.getColumnIndex("passwort"));
                usernameAusDerDatenbank = cursor.getString(cursor.getColumnIndex("username"));
                roleAusDerDatenbank = cursor.getString(cursor.getColumnIndex("role"));
                }
                cursor.close();
                if (username.getText().toString().equals(usernameAusDerDatenbank) && password.getText().toString().equals(pwAusDerDatenbank) && adminRadio.isChecked() && roleAusDerDatenbank.equals("Admin")) {
                    Toast.makeText(LehrVeranstaltungHS.this, "Login SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    switchActivitiesAdmin();
                }
                    if (username.getText().toString().equals(usernameAusDerDatenbank) && password.getText().toString().equals(pwAusDerDatenbank) && !adminRadio.isChecked() && roleAusDerDatenbank.equals("Admin")) {
                        Toast.makeText(LehrVeranstaltungHS.this, "Login SUCCESSFUL", Toast.LENGTH_SHORT).show();
                        switchActivitiesSachbearbeiter();
                    }

                    if(username.getText().toString().equals(usernameAusDerDatenbank) && password.getText().toString().equals(pwAusDerDatenbank) && roleAusDerDatenbank.equals("Sachbearbeiter") && adminRadio.isChecked())
                    {
                        Toast.makeText(LehrVeranstaltungHS.this, "Sie als Sacharbeiter können Sie nicht als Admin anmelden", Toast.LENGTH_SHORT).show();
                    }

                if(username.getText().toString().equals(usernameAusDerDatenbank) && password.getText().toString().equals(pwAusDerDatenbank) && roleAusDerDatenbank.equals("Sachbearbeiter") && !adminRadio.isChecked())
                {
                    Toast.makeText(LehrVeranstaltungHS.this, "Login SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    switchActivitiesSachbearbeiter();
                }

                 else {
                    Toast.makeText(LehrVeranstaltungHS.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public static String savedRoleLogin(){
    if(adminRadio.isChecked())
    {
        return adminRadio.getText().toString();
    }
    else {
        return "Sachbearbeiter";
    }
    }


    private void switchActivitiesAdmin() {
        Intent switchActivityIntent = new Intent(this, AdminAS.class);
        startActivity(switchActivityIntent);
    }
    private void switchActivitiesSachbearbeiter() {
        Intent switchActivityIntent = new Intent(this, SachbearbeiterAS.class);
        startActivity(switchActivityIntent);
    }
}
