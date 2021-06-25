package com.Ahmed.Amine.IF4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "goods.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "stock";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "good_name";
    private static final String COLUMN_QUANTITY = "good_quantity";
    private static final String COLUMN_DATE = "good_date";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, "
                + COLUMN_QUANTITY + " INTEGER, " +
                COLUMN_DATE + " DATE);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addGood(String name, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_QUANTITY, quantity);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        cv.put(COLUMN_DATE, date);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "échec", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "succèes", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    String dateRecente(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        String query = "SELECT * FROM " +  TABLE_NAME + " ORDER BY " + COLUMN_DATE + " DESC LIMIT 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);

            if(cursor.getCount() == 0){
                return(date);
            }
            else {
                cursor.moveToFirst();
                date = cursor.getString(3);
            }
            cursor.close();
        }
        return (date);
    }

    void updateData(String row_id, String name, String quantity, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_QUANTITY, quantity);
        cv.put(COLUMN_DATE, date);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "échec", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "succèes", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "échec", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "succèes", Toast.LENGTH_SHORT).show();
        }
    }

}
