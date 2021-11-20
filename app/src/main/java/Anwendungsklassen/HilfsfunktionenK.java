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
import Entitätsklassen.SacharbeiterEK;

public class HilfsfunktionenK {

    private DatabaseHelperSacharbeiterVerwaltung dbHelper;
    private Context context;

    private SQLiteDatabase database;

    SacharbeiterEK sacharbeiterEK = new SacharbeiterEK();

    public HilfsfunktionenK(Context c) {
        context = c;
    }

    public HilfsfunktionenK open() throws SQLException {
        dbHelper = new DatabaseHelperSacharbeiterVerwaltung(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert() {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelperSacharbeiterVerwaltung.USERNAME, SacharbeiterEK.getUsername());
        contentValue.put(DatabaseHelperSacharbeiterVerwaltung.PASSWORT, SacharbeiterEK.getPasswort());
        contentValue.put(DatabaseHelperSacharbeiterVerwaltung.ROLE, SacharbeiterEK.getRole());
        database.insert(DatabaseHelperSacharbeiterVerwaltung.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelperSacharbeiterVerwaltung._ID, DatabaseHelperSacharbeiterVerwaltung.USERNAME, DatabaseHelperSacharbeiterVerwaltung.PASSWORT, DatabaseHelperSacharbeiterVerwaltung.ROLE };
        Cursor cursor = database.query(DatabaseHelperSacharbeiterVerwaltung.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor gibSacharbeiter(String username)
    {

        String query = "select * from SACHARBEITERVERWALTUNG where username=\""+ username + "\"";
        return database.rawQuery(query, null);
    }

    @SuppressLint("Range")
    public String getRoleFromUser(String username)
    {
        String role;
        String query = "select * from SACHARBEITERVERWALTUNG where username=\""+ username + "\"";
        Cursor cursor =  database.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            role = cursor.getString(cursor.getColumnIndex("username"));
        }
        else{
            role = null;
        }
        return role;
    }

    @SuppressLint("Range")
    public List<String> getAllNutzer()
    {
        List<String> allUsers = new ArrayList<>();

        String query= "SELECT  * from SACHARBEITERVERWALTUNG" ;
        Cursor cursor = database.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                allUsers.add(cursor.getString(cursor.getColumnIndex("username")));
            }while (cursor.moveToNext());
        }

        cursor.close();
      //  database.close();
        return allUsers;
    }


    public void update(String user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelperSacharbeiterVerwaltung.USERNAME, SacharbeiterEK.getUsername());
        values.put(DatabaseHelperSacharbeiterVerwaltung.PASSWORT, SacharbeiterEK.getPasswort());
        values.put(DatabaseHelperSacharbeiterVerwaltung.ROLE, SacharbeiterEK.getRole());
        database.update(DatabaseHelperSacharbeiterVerwaltung.TABLE_NAME, values, "username=?" ,new String[]{user});
    }

    public void delete(String username) {
        String query = "delete from SACHARBEITERVERWALTUNG where username= \""+ username + "\"";
        database.execSQL(query);
    }

    public void transferAnwendungsschicht(String username, String passwort, String role)
    {
        sacharbeiterEK.setRole(role);
        sacharbeiterEK.setUsername(username);
        sacharbeiterEK.setPasswort(passwort);
    }

}