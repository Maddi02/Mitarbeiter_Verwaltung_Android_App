package Anwendungsklassen;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import Datenbank.DatabaseHelperFortbildungSachbearbeiter;

public class SachbearbeiterSachbearbeiterFortbildungLöschen {
    private DatabaseHelperFortbildungSachbearbeiter dbHelper;
    private Context context;
    private SQLiteDatabase database;

    HilfsfunktionenK hilfsfunktionenK;



    public SachbearbeiterSachbearbeiterFortbildungLöschen(Context c) {
        context = c;
    }
    public SachbearbeiterSachbearbeiterFortbildungLöschen open() throws SQLException {
        dbHelper = new DatabaseHelperFortbildungSachbearbeiter(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public boolean deleteFortbildungForUser(String user, String Fortbildung)
    {
        ContentValues values = new ContentValues();
        if(Fortbildung.equals("Mathematik 1") || Fortbildung.equals("Mathematik 2")|| Fortbildung.equals("Allgemeine Betriebswirtschaft"))
            return false;

        else{
            values.put(DatabaseHelperFortbildungSachbearbeiter.Fortbildung3, (Boolean) null);
            values.put(DatabaseHelperFortbildungSachbearbeiter.Status3, (Boolean) null);
        }
        database.update(DatabaseHelperFortbildungSachbearbeiter.TABLE_NAME, values, "username=?" ,new String[]{user});
        return true;
    }
}
