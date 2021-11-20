package Datenbank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperFortbildungSachbearbeiter extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "SACHBEARBEITERFORTBILDUNG";

    // Table columns
    public static final String _ID = "_id";
    public static final String USERNAME = "username";
    public static final String Fortbildung1 = "Fortbildung1";
    public static final String Status1 = "Status1";
    public static final String Fortbildung2 = "Fortbildung2";
    public static final String Status2 = "Status2";
    public static final String Fortbildung3 = "Fortbildung3";
    public static final String Status3 = "Status3";
    public static final String Fortbildung4 = "Fortbildung4";
    public static final String Status4 = "Status4";

    // Database Information
    static final String DB_NAME = "SachbearbeiterFortbildung.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLEFortbildung = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ USERNAME +" TEXT," + Fortbildung1 + " TEXT, " + Status1 + " TEXT," + Fortbildung2 + " TEXT,"+ Status2 + " Text," +
               Fortbildung3 + " TEXT, " + Status3 + " TEXT," + Fortbildung4 + " TEXT,"+ Status4 + " TEXT );";

    public DatabaseHelperFortbildungSachbearbeiter(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLEFortbildung);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
