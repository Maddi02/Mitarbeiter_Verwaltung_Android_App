package Datenbank;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperSacharbeiterVerwaltung extends SQLiteOpenHelper {

        // Table Name
        public static final String TABLE_NAME = "SACHARBEITERVERWALTUNG";

        // Table columns
        public static final String _ID = "_id";
        public static final String USERNAME = "username";
        public static final String PASSWORT = "passwort";
        public static final String ROLE = "role";

        // Database Information
        static final String DB_NAME = "Sachbearbeiter.DB";

        // database version
        static final int DB_VERSION = 1;

        // Creating table query
        private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT NOT NULL, " + PASSWORT + " TEXT," + ROLE + " TEXT );";

        public DatabaseHelperSacharbeiterVerwaltung(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
