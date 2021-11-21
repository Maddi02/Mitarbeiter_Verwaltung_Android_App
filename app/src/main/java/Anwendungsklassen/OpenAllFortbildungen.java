package Anwendungsklassen;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Datenbank.DatabaseHelperAlleFortbildungen;
import Datenbank.DatabaseHelperSacharbeiterVerwaltung;
import Entitätsklassen.AllFortbildungen;
import Entitätsklassen.SacharbeiterEK;

public class OpenAllFortbildungen {
    private DatabaseHelperAlleFortbildungen dbHelper;
    private Context context;

    private SQLiteDatabase database;


    public OpenAllFortbildungen(Context c) {
        context = c;
    }
    public OpenAllFortbildungen open() throws SQLException {
        dbHelper = new DatabaseHelperAlleFortbildungen(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void insert() {
    }

    @SuppressLint("Range")
    public List<String> getAllFortbildungen()
    {
        List<String> allFortbildungen = new ArrayList<>();

        String query= "SELECT  * from AlleFortbildungen" ;
        Cursor cursor = database.rawQuery(query,null);
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
    public String getAllVorrausetzungenForFortbildungAsString(String Fortbildung)
    {
        String VorraussetzungeForFortbildung = "";
        String query= "SELECT  * from AlleFortbildungen WHERE FORTBILDUNG = \""+ Fortbildung + "\"" ;
        Cursor cursor = database.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                VorraussetzungeForFortbildung = VorraussetzungeForFortbildung + (cursor.getString(cursor.getColumnIndex("Fortbildung")));
                if(cursor.getString(cursor.getColumnIndex("Vorraussetzung1")) != null)
                VorraussetzungeForFortbildung =  VorraussetzungeForFortbildung +" " + (cursor.getString(cursor.getColumnIndex("Vorraussetzung1")));
                if(cursor.getString(cursor.getColumnIndex("Vorraussetzung2")) != null)
                VorraussetzungeForFortbildung = VorraussetzungeForFortbildung +" " +  (cursor.getString(cursor.getColumnIndex("Vorraussetzung2")));
            }while (cursor.moveToNext());
        }

        return VorraussetzungeForFortbildung;
    }


}
