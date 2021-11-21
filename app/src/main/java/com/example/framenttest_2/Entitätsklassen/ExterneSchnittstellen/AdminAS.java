package com.example.framenttest_2.Entitätsklassen.ExterneSchnittstellen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.example.framenttest_2.R;

public class AdminAS extends AppCompatActivity {

    Button first ;
    Button second;
    Button erstellen;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.SachbeabeiterErstellenButtonMenu:
                replaceFragement(new AdminSachbearbeiterErstellen());
                break;

            case R.id.SachbeabeiterEditerenButtonMenu:
                System.out.println("Editerne");
                replaceFragement(new AdminSachbearbeiterEditerenAAS());
                break;

            case R.id.SachbeabeiterLöschenButtonMenu:
                 replaceFragement(new AdminSachbearbeiterLöschenAAS());
            break;

            case R.id.SachbeabeiterFortbbildungZuordnenButtonMenu:
                replaceFragement(new AdminSacharbeiterFortbildungZuordnungASS());
                break;

            case R.id.SachbeabeiterFortbbildungLöschenButtonMenu:
                replaceFragement(new AdminSacharbeiterFortbildungLöschen());
                break;

            case R.id.Ausloggen:
                this.finishAffinity();
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu,menu);
        return true;
    }


    private void replaceFragement(Fragment fragment) {
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayout,fragment);
        fragmentTransaction.commit();
    }
}