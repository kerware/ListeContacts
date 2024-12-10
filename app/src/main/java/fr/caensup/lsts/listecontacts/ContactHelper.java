package fr.caensup.lsts.listecontacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME =
            "contacts.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_CONTACT =
            "Contacts";
    public static final String NOM_COLONNE_ID = "Id";
    public static final String NOM_COLONNE_NOM = "Nom";
    public static final String NOM_COLONNE_PRENOM = "Prenom";
    public static final String NOM_COLONNE_NUMERO_TEL = "Numero";

    public ContactHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATION_DB = "CREATE TABLE " + TABLE_CONTACT + " (" +
            NOM_COLONNE_ID + " integer primary key autoincrement, " +
            NOM_COLONNE_NOM + " text not null, " +
            NOM_COLONNE_PRENOM + " text not null, " +
            NOM_COLONNE_NUMERO_TEL + " text not null);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATION_DB);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == newVersion)
            return;
        // Simplest implementation is to drop all old tables and recreate them
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT + ";");
        onCreate(db);
    }


}