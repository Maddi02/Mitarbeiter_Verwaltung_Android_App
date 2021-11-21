package Anwendungsklassen;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import Datenbank.DatabaseHelperFortbildungSachbearbeiter;
import Datenbank.DatabaseHelperSacharbeiterVerwaltung;
import Entitätsklassen.FortbildungSachbeabreiter;

public class SachbearbeiterSachbearbeiterFortbildungZuordnenK {
    private DatabaseHelperFortbildungSachbearbeiter dbHelper;
    private Context context;
    private SQLiteDatabase database;

    HilfsfunktionenK hilfsfunktionenK;


    public SachbearbeiterSachbearbeiterFortbildungZuordnenK(Context c) {
        context = c;
    }

    public SachbearbeiterSachbearbeiterFortbildungZuordnenK open() throws SQLException {
        dbHelper = new DatabaseHelperFortbildungSachbearbeiter(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void insert() {
        ContentValues contentValue = new ContentValues();
        System.out.println("USERNAME " + FortbildungSachbeabreiter.getUsername());
        contentValue.put(DatabaseHelperFortbildungSachbearbeiter.USERNAME, FortbildungSachbeabreiter.getUsername());
        contentValue.put(DatabaseHelperFortbildungSachbearbeiter.Fortbildung1, FortbildungSachbeabreiter.getFortbildung1());
        contentValue.put(DatabaseHelperFortbildungSachbearbeiter.Fortbildung2, FortbildungSachbeabreiter.getFortbildung2());
        contentValue.put(DatabaseHelperFortbildungSachbearbeiter.Fortbildung3, FortbildungSachbeabreiter.getFortbildung3());
        contentValue.put(DatabaseHelperFortbildungSachbearbeiter.Fortbildung4, FortbildungSachbeabreiter.getFortbildung4());
        contentValue.put(DatabaseHelperFortbildungSachbearbeiter.Status1, FortbildungSachbeabreiter.getStatus1());
        contentValue.put(DatabaseHelperFortbildungSachbearbeiter.Status2, FortbildungSachbeabreiter.getStatus2());
        contentValue.put(DatabaseHelperFortbildungSachbearbeiter.Status3, FortbildungSachbeabreiter.getStatus3());
        contentValue.put(DatabaseHelperFortbildungSachbearbeiter.Status4, FortbildungSachbeabreiter.getStatus4());
        database.insert(DatabaseHelperFortbildungSachbearbeiter.TABLE_NAME, null, contentValue);
    }


    @SuppressLint("Range")
    public String getAllFortbildungenForUser(String user) {
        String alleFortbildungen = "";
        String query = "select * from SACHBEARBEITERFORTBILDUNG where username = \"" + user + "\"";
        Cursor cursor = database.rawQuery(query, null);

        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndex("Fortbildung1")) != null)
                alleFortbildungen = alleFortbildungen + " " + (cursor.getString(cursor.getColumnIndex("Fortbildung1")));

            if (cursor.getString(cursor.getColumnIndex("Fortbildung2")) != null)
                alleFortbildungen = alleFortbildungen + " " + (cursor.getString(cursor.getColumnIndex("Fortbildung2")));

            if (cursor.getString(cursor.getColumnIndex("Fortbildung3")) != null)
                alleFortbildungen = alleFortbildungen + " " + (cursor.getString(cursor.getColumnIndex("Fortbildung3")));

            if (cursor.getString(cursor.getColumnIndex("Fortbildung4")) != null)
                alleFortbildungen = alleFortbildungen + " " + (cursor.getString(cursor.getColumnIndex("Fortbildung4")));

            if (cursor.getString(cursor.getColumnIndex("Status1")) != null)
                alleFortbildungen = alleFortbildungen + " " + (cursor.getString(cursor.getColumnIndex("Status1")));

            if (cursor.getString(cursor.getColumnIndex("Status2")) != null)
                alleFortbildungen = alleFortbildungen + " " + (cursor.getString(cursor.getColumnIndex("Status2")));

            if (cursor.getString(cursor.getColumnIndex("Status3")) != null)
                alleFortbildungen = alleFortbildungen + " " + (cursor.getString(cursor.getColumnIndex("Status3")));

            if (cursor.getString(cursor.getColumnIndex("Status4")) != null)
                alleFortbildungen = alleFortbildungen + " " + (cursor.getString(cursor.getColumnIndex("Status4")));
        }
        return alleFortbildungen;
    }


    public boolean setFortbildungen(String user, String ausgewählteFortbildung, String Status) {
        hilfsfunktionenK = new HilfsfunktionenK(context);
        hilfsfunktionenK.open();
        ContentValues values = new ContentValues();

        String Mathe1 = "Mathematik 1";
        String Mathe2 = "Mathematik 2";
        String Kostenrechnung = "Kostenrechnung";
        String AllgemeineBetriebswirtschaft = "Allgemeine Betriebswirtschaft";

        String alleFortbildungenWithVorraussetzung = hilfsfunktionenK.getAllVorrausetzungenForFortbildungAsString(ausgewählteFortbildung);
        System.out.println(alleFortbildungenWithVorraussetzung);
        String alleFortbildungenForUser = getAllFortbildungenForUser(user);
        System.out.println("USSSSSEEERRRR " + alleFortbildungenForUser);

        if (ausgewählteFortbildung.equals(Mathe1)) {
            values.put(DatabaseHelperFortbildungSachbearbeiter.Fortbildung1, ausgewählteFortbildung);
            values.put(DatabaseHelperFortbildungSachbearbeiter.Status1, Status);
            database.update(DatabaseHelperFortbildungSachbearbeiter.TABLE_NAME, values, "username=?", new String[]{user});
            return true;
        }

        if (ausgewählteFortbildung.equals(Mathe2) && hilfsfunktionenK.isContainExactWord(alleFortbildungenForUser, Mathe1)) {
            System.out.println("Drin ´a");
            values.put(DatabaseHelperFortbildungSachbearbeiter.Fortbildung2, ausgewählteFortbildung);
            values.put(DatabaseHelperFortbildungSachbearbeiter.Status2, Status);
            database.update(DatabaseHelperFortbildungSachbearbeiter.TABLE_NAME, values, "username=?", new String[]{user});
            return true;
        }

        if (ausgewählteFortbildung.equals(Kostenrechnung) && hilfsfunktionenK.isContainExactWord(alleFortbildungenForUser, Mathe2) && hilfsfunktionenK.isContainExactWord(alleFortbildungenForUser, AllgemeineBetriebswirtschaft)) {
            values.put(DatabaseHelperFortbildungSachbearbeiter.Fortbildung3, ausgewählteFortbildung);
            values.put(DatabaseHelperFortbildungSachbearbeiter.Status3, Status);
            database.update(DatabaseHelperFortbildungSachbearbeiter.TABLE_NAME, values, "username=?", new String[]{user});
            return true;
        }

        if (ausgewählteFortbildung.equals(AllgemeineBetriebswirtschaft)) {
            values.put(DatabaseHelperFortbildungSachbearbeiter.Fortbildung4, ausgewählteFortbildung);
            values.put(DatabaseHelperFortbildungSachbearbeiter.Status4, Status);
            database.update(DatabaseHelperFortbildungSachbearbeiter.TABLE_NAME, values, "username=?", new String[]{user});
            return true;
        }
        return false;
    }


}

