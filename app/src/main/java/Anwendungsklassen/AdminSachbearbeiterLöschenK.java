package Anwendungsklassen;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import Datenbank.DatabaseHelperFortbildungSachbearbeiter;
import Datenbank.DatabaseHelperSacharbeiterVerwaltung;

public class AdminSachbearbeiterLöschenK {
    private DatabaseHelperSacharbeiterVerwaltung dbHelperVerwaltung;
    private DatabaseHelperFortbildungSachbearbeiter dbHelperSachbearbeiterFortbildung;
    private Context context;

    private SQLiteDatabase databaseVerwaltung;
    private SQLiteDatabase databaseUserFortbildung;

    public AdminSachbearbeiterLöschenK(Context c) {
        context = c;
    }

    public AdminSachbearbeiterLöschenK open() throws SQLException {
        dbHelperVerwaltung = new DatabaseHelperSacharbeiterVerwaltung(context);
        databaseVerwaltung = dbHelperVerwaltung.getWritableDatabase();
        dbHelperSachbearbeiterFortbildung = new DatabaseHelperFortbildungSachbearbeiter(context);
        databaseUserFortbildung = dbHelperSachbearbeiterFortbildung.getWritableDatabase();
        return this;
    }

    public void delete(String username) {
        String query = "delete from SACHARBEITERVERWALTUNG where username= \""+ username + "\"";
        databaseVerwaltung.execSQL(query);

        String query1 = "delete from SACHBEARBEITERFORTBILDUNG where username= \""+ username + "\"";
        databaseUserFortbildung.execSQL(query1);
    }
}
