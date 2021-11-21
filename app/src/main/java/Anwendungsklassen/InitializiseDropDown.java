package Anwendungsklassen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Datenbank.DatabaseHelperAlleFortbildungen;
import Datenbank.DatabaseHelperFortbildungSachbearbeiter;
import Datenbank.DatabaseHelperSacharbeiterVerwaltung;

public class InitializiseDropDown {

    private DatabaseHelperSacharbeiterVerwaltung dbHelperalleUser;
    private DatabaseHelperAlleFortbildungen dbHelperAlleFortbildungen;
    private Context context;

    private SQLiteDatabase databaseUser;
    private SQLiteDatabase databaseFortbildungen;

    public InitializiseDropDown(Context c) {
        context = c;
    }

    public InitializiseDropDown open() throws SQLException {
        dbHelperalleUser = new DatabaseHelperSacharbeiterVerwaltung(context);
        databaseUser = dbHelperalleUser.getWritableDatabase();

        dbHelperAlleFortbildungen = new DatabaseHelperAlleFortbildungen(context);
        databaseFortbildungen = dbHelperAlleFortbildungen.getWritableDatabase();
        return this;
    }

    @SuppressLint("Range")
    public List<String> getAllFortbildungen()
    {
        List<String> allFortbildungen = new ArrayList<>();

        String query= "SELECT  * from AlleFortbildungen" ;
        Cursor cursor = databaseFortbildungen.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                allFortbildungen.add(cursor.getString(cursor.getColumnIndex("Fortbildung")));
            }while (cursor.moveToNext());
        }

        cursor.close();
        //  database.close();
        return allFortbildungen;
    }

    @SuppressLint("Range")
    public List<String> getAllNutzer()
    {
        List<String> allUsers = new ArrayList<>();

        String query= "SELECT  * from SACHARBEITERVERWALTUNG" ;
        Cursor cursor = databaseUser.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                allUsers.add(cursor.getString(cursor.getColumnIndex("username")));
            }while (cursor.moveToNext());
        }

        cursor.close();
        //  database.close();
        return allUsers;
    }
}
