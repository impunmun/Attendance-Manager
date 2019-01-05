package map.google.newattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import static android.R.attr.id;
import static android.widget.Toast.LENGTH_LONG;


public class db extends SQLiteOpenHelper {
//A helper class to manage database creation and version management by  implementing the
// onCreate(SQLiteDatabase arg0) , onUpgrade(SQLiteDatabase arg0, int oldversion, int newversion)

    private static final String DATABASE_NAME = "Subjects";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_CONTACTS="Students";
    private Context context;
    private static final String SUB = "Subject";//subject
    private static final String PRE = "present";//present
    private static final String AB = "absent";//absent
    private static final String PER = "PERCENT";

    public db(Context context) {
        // TODO Auto-generated constructor stub
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;

        //android.database.sqlite.SQLiteOpenHelper.SQLiteOpenHelper
        //(Context context, String name, CursorFactory factory, int version)


    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + SUB + " TEXT PRIMARY KEY," + PRE + " INTEGER,"
                + AB + " INTEGER)";
        arg0.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int oldversion, int newversion) {
        // TODO Auto-generated method stub
        arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(arg0);

    }
    void insert(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        //The database is not actually created or opened until one of getWritableDatabase()
        //or getReadableDatabase() is called.

        ContentValues values=new ContentValues();
        values.put(SUB, name);
        values.put(PRE, 0);
        values.put(AB,0);
        //values.put(PER,0);
        db.insert(TABLE_CONTACTS, null, values);
        db.close();

    }
    public Cursor getInsertedData()
    //Cursor This interface provides random read-write access
    //to the result set returned by a database query.
    {
        SQLiteDatabase db=this.getReadableDatabase();
        return db.query(TABLE_CONTACTS, new String[] {SUB,PRE,AB},
                null, null, null, null, null);

        // Cursor android.database.sqlite.SQLiteDatabase.query(String table,
        // String[] columns,String selection, String[]
        //selectionArgs, String groupBy, String having, String orderBy)


    }
    public void up1(String s1,int pre) {
        String s2;
        int a=pre;
        SQLiteDatabase db1 = this.getWritableDatabase();
                    a = a + 1;
                    s2 = "" + a;
                    ContentValues values = new ContentValues();
                    values.put(PRE, s2);

                    // updating row
                    db1.update(TABLE_CONTACTS, values, SUB + " = '" + s1 + "'", null);



    }
    public void del1(String s1)
    {
        SQLiteDatabase db1 = this.getWritableDatabase();
        db1.delete(TABLE_CONTACTS,SUB + " = '" + s1 + "'", null);
        //db1.execSQL("delete from "+TABLE_CONTACTS+" where SUB='"+s1+"'");
    }
    public void up2(String s1,int ab) {
        String s2;
        int a=ab;
        SQLiteDatabase db1 = this.getWritableDatabase();
        a = a + 1;
        s2 = "" + a;
        ContentValues values = new ContentValues();
        values.put(AB, s2);

        // updating row
        db1.update(TABLE_CONTACTS, values, SUB + " = '" + s1 + "'", null);



    }
    public void clearData() {
        // TODO Auto-generated method stub
        context.deleteDatabase(DATABASE_NAME);
    }


}





