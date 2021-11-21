package Anwendungsklassen;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Datenbank.DatabaseHelperAlleFortbildungen;
import Datenbank.DatabaseHelperFortbildungSachbearbeiter;
import Datenbank.DatabaseHelperSacharbeiterVerwaltung;
import Entitätsklassen.FortbildungSachbeabreiter;
import Entitätsklassen.SacharbeiterEK;

public class HilfsfunktionenK {

    private DatabaseHelperSacharbeiterVerwaltung dbHelper;
    private DatabaseHelperFortbildungSachbearbeiter dbHelperFortbildungen;
    private DatabaseHelperAlleFortbildungen dbHelperAlleFortbildungen;
    private FortbildungSachbeabreiter fortbildungSachbeabreiter = new FortbildungSachbeabreiter();
    private Context context;

    private SQLiteDatabase database;
    private SQLiteDatabase databaseFortbildung;
    private SQLiteDatabase databaseAlleFortbildung;

    SacharbeiterEK sacharbeiterEK = new SacharbeiterEK();

    public HilfsfunktionenK(Context c) {
        context = c;
    }

    public HilfsfunktionenK open() throws SQLException {
        dbHelper = new DatabaseHelperSacharbeiterVerwaltung(context);
        database = dbHelper.getWritableDatabase();
        dbHelperFortbildungen = new DatabaseHelperFortbildungSachbearbeiter(context);
        databaseFortbildung = dbHelperFortbildungen.getWritableDatabase();

        dbHelperAlleFortbildungen = new DatabaseHelperAlleFortbildungen(context);
        databaseAlleFortbildung = dbHelperAlleFortbildungen.getWritableDatabase();

        return this;
    }


    public Cursor gibSacharbeiter(String username)
    {

        String query = "select * from SACHARBEITERVERWALTUNG where username=\""+ username + "\"";
        return database.rawQuery(query, null);
    }


    public void transferAnwendungsschicht(String username, String passwort, String role)
    {
        sacharbeiterEK.setRole(role);
        sacharbeiterEK.setUsername(username);
        sacharbeiterEK.setPasswort(passwort);
    }


    public void transfere(String username,String Fortbildung1 , String Status1, String Fortbildung2 , String Status2, String Fortbildung3 , String Status3,String Fortbildung4 , String Status4)
    {

        fortbildungSachbeabreiter.setUsername(username);
        fortbildungSachbeabreiter.setFortbildung1(Fortbildung1);
        fortbildungSachbeabreiter.setFortbildung2(Fortbildung2);
        fortbildungSachbeabreiter.setFortbildung3(Fortbildung3);
        fortbildungSachbeabreiter.setFortbildung4(Fortbildung4);

        fortbildungSachbeabreiter.setStatus1(Status1);
        fortbildungSachbeabreiter.setStatus1(Status2);
        fortbildungSachbeabreiter.setStatus1(Status3);
        fortbildungSachbeabreiter.setStatus1(Status4);
    }

    public boolean isContainExactWord(String fullString, String partWord)
    {
        String pattern = "\\b"+partWord+"\\b";
        Pattern p=Pattern.compile(pattern);
        Matcher m=p.matcher(fullString);
        return m.find();
    }

    @SuppressLint("Range")
    public String getAllFortbildungenForUser(String user) {
        String alleFortbildungen = "";
        String query = "select * from SACHBEARBEITERFORTBILDUNG where username = \"" + user + "\"";
        Cursor cursor = databaseFortbildung.rawQuery(query, null);

        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndex("Fortbildung1")) != null)
                alleFortbildungen = alleFortbildungen + " " + (cursor.getString(cursor.getColumnIndex("Fortbildung1")));

            if (cursor.getString(cursor.getColumnIndex("Status1")) != null)
                alleFortbildungen = alleFortbildungen + " " + (cursor.getString(cursor.getColumnIndex("Status1")));

            if (cursor.getString(cursor.getColumnIndex("Fortbildung2")) != null)
                alleFortbildungen = alleFortbildungen + " " + (cursor.getString(cursor.getColumnIndex("Fortbildung2")));

            if (cursor.getString(cursor.getColumnIndex("Status2")) != null)
                alleFortbildungen = alleFortbildungen + " " + (cursor.getString(cursor.getColumnIndex("Status2")));

            if (cursor.getString(cursor.getColumnIndex("Fortbildung3")) != null)
                alleFortbildungen = alleFortbildungen + " " + (cursor.getString(cursor.getColumnIndex("Fortbildung3")));

            if (cursor.getString(cursor.getColumnIndex("Status3")) != null)
                alleFortbildungen = alleFortbildungen + " " + (cursor.getString(cursor.getColumnIndex("Status3")));

            if (cursor.getString(cursor.getColumnIndex("Fortbildung4")) != null)
                alleFortbildungen = alleFortbildungen + " " + (cursor.getString(cursor.getColumnIndex("Fortbildung4")));

            if (cursor.getString(cursor.getColumnIndex("Status4")) != null)
                alleFortbildungen = alleFortbildungen + " " + (cursor.getString(cursor.getColumnIndex("Status4")));
        }
        return alleFortbildungen;
    }



    @SuppressLint("Range")
    public String getAllVorrausetzungenForFortbildungAsString(String Fortbildung)
    {
        String VorraussetzungeForFortbildung = "";
        String query= "SELECT  * from AlleFortbildungen WHERE FORTBILDUNG = \""+ Fortbildung + "\"" ;
        Cursor cursor = databaseAlleFortbildung.rawQuery(query,null);
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

    public boolean checkIfNutzerExist(String username)
    {
        String query = "select * from SACHARBEITERVERWALTUNG where username=\""+ username + "\"";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkIfUsernameIsValid(String username)
    {
        if(username.length() > 0 && username.matches("[a-zA-Z]+"))
        {
            return true;
        }
        return false;
    }
}