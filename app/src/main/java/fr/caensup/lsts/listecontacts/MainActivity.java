package fr.caensup.lsts.listecontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private ContactHelper contactHelper;
    private TextView tvContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContacts = findViewById( R.id.tvTxtContacts ) ;

        initDatabase();
        createContacts();
    }

    private String getContactsAsString() {
        String[] ALL = new String[]{
                contactHelper.NOM_COLONNE_ID,
                contactHelper.NOM_COLONNE_PRENOM,
                contactHelper.NOM_COLONNE_NOM,
                contactHelper.NOM_COLONNE_NUMERO_TEL};
        SQLiteDatabase db = contactHelper.getReadableDatabase();
        Cursor cursor = db.query( ContactHelper.TABLE_CONTACT, ALL , null,null,null,null,null);
        StringBuilder contacts = new StringBuilder();
        while( cursor.moveToNext()) {

            int id = cursor.getInt( 0);
            String prenom = cursor.getString(1);
            String nom = cursor.getString(2);
            String numTel = cursor.getString(3);

            contacts.append( id );
            contacts.append( " " );
            contacts.append(  prenom  );
            contacts.append( " " );
            contacts.append( nom );
            contacts.append( " " );
            contacts.append(  numTel );
            contacts.append("\n");
        }
        return contacts.toString();
    }

    private void initDatabase() {
        contactHelper = new ContactHelper( this );
    }

    private void createContacts() {
        SQLiteDatabase db = contactHelper.getWritableDatabase();

        db.beginTransaction();
        try {
            // Transaction insertion des 50 contacts
            List<String> contacts = getData();
            for( String contact : contacts ) {
                StringTokenizer st =
                        new StringTokenizer( contact , ",");
                ContentValues values=new ContentValues();
                values.put(ContactHelper.NOM_COLONNE_PRENOM, st.nextToken());
                values.put(ContactHelper.NOM_COLONNE_NOM, st.nextToken());
                values.put(ContactHelper.NOM_COLONNE_NUMERO_TEL, st.nextToken());
                db.insert(ContactHelper.TABLE_CONTACT,null,values);
            }
            db.setTransactionSuccessful();
        }
        catch (SQLiteException e) {

        }
        finally {
            db.endTransaction();
            db.close();
        }


    }

    private List<String> getData() {

        return Arrays.asList(new String[]{
        "Loretta,Maggill'Andreis,06 64 54 39 56 ",
        "Laverna,Haggarth,06 80 00 90 04",
        "Dannie,Bidder,06 08 62 27 57",
        "Nicol,Sawers,06 47 04 83 02",
        "Myrlene,Curm,06 84 64 17 13",
        "Alysia,Cluse,06 41 65 65 78",
        "Arley,Alpes,06 95 75 05 50",
        "Normie,Martinec,06 11 56 69 31",
        "Mortimer,Whitmore,06 10 74 82 77",
        "Dorothy,Jerrams,06 89 22 77 99",
        "Urbanus,Favela,06 23 82 25 98",
        "Marielle,Sheach,06 61 08 36 31",
        "Val,Pettigrew,06 16 27 57 39",
        "Merridie,Beale,06 11 07 63 66",
        "Yasmin,Gorringe,06 47 24 96 84",
        "Brnaba,Lehrahan,06 37 52 76 06",
        "Nari,Hugues,06 59 47 85 46",
        "Boris,Renehan,06 56 78 95 92",
        "Ethelin,Whitefoot,06 12 38 55 95",
        "Sibyl,Jennemann,06 93 58 31 62",
        "Joyous,Betts,06 75 72 98 57",
        "Lainey,Jerrold,06 73 85 69 67",
        "Timofei,Sangra,06 11 48 08 70",
        "Cordy,Wands,06 67 02 88 43"});

    }
}