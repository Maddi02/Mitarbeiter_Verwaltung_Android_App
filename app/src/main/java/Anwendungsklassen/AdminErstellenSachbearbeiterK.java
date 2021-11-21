package Anwendungsklassen;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import Datenbank.DatabaseHelperSacharbeiterVerwaltung;
import Entitätsklassen.SacharbeiterEK;

public class AdminErstellenSachbearbeiterK {
    private DatabaseHelperSacharbeiterVerwaltung dbHelper;
    private Context context;

    private SQLiteDatabase database;

    public AdminErstellenSachbearbeiterK(Context c) {
        context = c;
    }

    public AdminErstellenSachbearbeiterK open() throws SQLException {
        dbHelper = new DatabaseHelperSacharbeiterVerwaltung(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }


    public void insert() {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelperSacharbeiterVerwaltung.USERNAME, SacharbeiterEK.getUsername());
        contentValue.put(DatabaseHelperSacharbeiterVerwaltung.PASSWORT, SacharbeiterEK.getPasswort());
        contentValue.put(DatabaseHelperSacharbeiterVerwaltung.ROLE, SacharbeiterEK.getRole());
        database.insert(DatabaseHelperSacharbeiterVerwaltung.TABLE_NAME, null, contentValue);
    }
}
