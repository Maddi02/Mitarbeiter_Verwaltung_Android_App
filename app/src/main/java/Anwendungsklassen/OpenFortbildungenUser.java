package Anwendungsklassen;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import Datenbank.DatabaseHelperFortbildungSachbearbeiter;
import Datenbank.DatabaseHelperSacharbeiterVerwaltung;
import Entitätsklassen.FortbildungSachbeabreiter;
import Entitätsklassen.SacharbeiterEK;

public class OpenFortbildungenUser {
    private DatabaseHelperFortbildungSachbearbeiter dbHelper;
    private Context context;
    FortbildungSachbeabreiter fortbildungSachbeabreiter = new FortbildungSachbeabreiter();;
    private SQLiteDatabase database;


    public OpenFortbildungenUser(Context c) {
        context = c;
    }
    public OpenFortbildungenUser open() throws SQLException {
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

    public String getAllFortbildungenForUser(String user)
    {

        return "";
    }

    public void update(String user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelperFortbildungSachbearbeiter.USERNAME, FortbildungSachbeabreiter.getUsername());
        database.update(DatabaseHelperFortbildungSachbearbeiter.TABLE_NAME, values, "username=?" ,new String[]{user});
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
}
