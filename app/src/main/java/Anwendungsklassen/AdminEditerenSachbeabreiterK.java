package Anwendungsklassen;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import Datenbank.DatabaseHelperFortbildungSachbearbeiter;
import Datenbank.DatabaseHelperSacharbeiterVerwaltung;
import Entitätsklassen.FortbildungSachbeabreiter;
import Entitätsklassen.SacharbeiterEK;

public class AdminEditerenSachbeabreiterK {
    private DatabaseHelperSacharbeiterVerwaltung dbHelperVerwaltung;
    private DatabaseHelperFortbildungSachbearbeiter dbHelperFortbildungUser;
    private Context context;

    private SQLiteDatabase databaseVerwaltung;
    private SQLiteDatabase databaseFortbildungUser;

    public AdminEditerenSachbeabreiterK(Context c) {
        context = c;
    }

    public AdminEditerenSachbeabreiterK open() throws SQLException {
        dbHelperVerwaltung = new DatabaseHelperSacharbeiterVerwaltung(context);
        databaseVerwaltung = dbHelperVerwaltung.getWritableDatabase();
        dbHelperFortbildungUser = new DatabaseHelperFortbildungSachbearbeiter(context);
        databaseFortbildungUser = dbHelperFortbildungUser.getWritableDatabase();

        return this;
    }

    public void updateSachbearbeiterVerwalung(String user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelperSacharbeiterVerwaltung.USERNAME, SacharbeiterEK.getUsername());
        values.put(DatabaseHelperSacharbeiterVerwaltung.PASSWORT, SacharbeiterEK.getPasswort());
        values.put(DatabaseHelperSacharbeiterVerwaltung.ROLE, SacharbeiterEK.getRole());
        databaseVerwaltung.update(DatabaseHelperSacharbeiterVerwaltung.TABLE_NAME, values, "username=?" ,new String[]{user});
        }

    public void updateSachbearbeiterFortbildungUsername(String user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelperFortbildungSachbearbeiter.USERNAME, FortbildungSachbeabreiter.getUsername());
        databaseFortbildungUser.update(DatabaseHelperFortbildungSachbearbeiter.TABLE_NAME, values, "username=?" ,new String[]{user});
    }
}
