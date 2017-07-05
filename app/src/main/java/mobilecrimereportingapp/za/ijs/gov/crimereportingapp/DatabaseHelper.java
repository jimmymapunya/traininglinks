package mobilecrimereportingapp.za.ijs.gov.crimereportingapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MaribolleR on 2017/07/05.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public Cursor getData(){
        SQLiteDatabase database = this.getWritableDatabase();
        return  null;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
