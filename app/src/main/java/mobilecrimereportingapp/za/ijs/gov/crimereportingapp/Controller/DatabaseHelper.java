package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MaribolleR on 2017/07/05.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DB_NAME = "DashboardDB.db";
    public static final String TABLE_NAME = "dashboard_data_table";
    public static final String COL_1 = "notificationCount";
    public static final String COL_2 = "inboxCount";
    public static final String COL_3 = "myCaseCount";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(NOTIFCOUNT INTEGER, INBOXCOUNT INTEGER, CASECOUNT INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(int notificationCount, int inboxCount, int caseCount){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,notificationCount);
        contentValues.put(COL_2,inboxCount);
        contentValues.put(COL_3,caseCount);

        return true;
    }
}
