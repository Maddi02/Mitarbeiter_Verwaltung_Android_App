package Datenbank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperAlleFortbildungen extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "AlleFortbildungen";

    // Table columns
    public static final String _ID = "_id";
    public static final String Fortbildung = "Fortbildung";
    public static final String Vorraussetzung1 = "Vorraussetzung1";
    public static final String Vorraussetzung2 = "Vorraussetzung2";


    // Database Information
    static final String DB_NAME = "AlleFortbildungen.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLEAlleFortbildungen = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Fortbildung + " TEXT NOT NULL, " + Vorraussetzung1 + " TEXT," + Vorraussetzung2 + " TEXT );";

    public DatabaseHelperAlleFortbildungen(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLEAlleFortbildungen);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
