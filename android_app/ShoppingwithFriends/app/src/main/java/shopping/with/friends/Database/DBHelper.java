package shopping.with.friends.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ryan Brooks on 1/24/15.
 */
public class DBHelper extends SQLiteOpenHelper {

    /**
     * Instantiating table names, column names, etc.
     */

    public static final String DATABASE_NAME = "DB";
    public static final int DATABASE_VERSION = 1;

    public static final String SAVED_USER_DATA = "user_data";

    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    public static final String CREATE_USER_DATA_TABLE =
            "CREATE TABLE " + SAVED_USER_DATA + " ("
            + KEY_USER_ID + "INTEGER PRIMARY KEY, "
            + KEY_USERNAME + "TEXT NOT NULL, "
            + KEY_PASSWORD + "TEXT NOT NULL)";

    /**
     * Public constructor, takes in a context (activity instance)
     */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * More detailed constructor
     */
    public DBHelper(Context context,
                    String name,
                    SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }

    /**
     * More detailed constructor
     */
    public DBHelper(Context context,
                    String name,
                    SQLiteDatabase.CursorFactory factory,
                    int version,
                    DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    /**
     * Creates the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_DATA_TABLE);
    }

    /**
     * Updates the database if there is a new version
     * Don't worry about this at all unless we start to do complicated stuff
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SAVED_USER_DATA);
        onCreate(db);
    }

    /**
     * Adds a user's username and password into the db
     */
    public void addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_USERNAME, username);
        contentValues.put(KEY_PASSWORD, password);

        db.insert(SAVED_USER_DATA, null, contentValues);
        db.close();
    }

    /**
     * Updates the user's info
     */
    public void updateUserInfo(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_USERNAME, username);
        contentValues.put(KEY_PASSWORD, password);

        db.update(SAVED_USER_DATA, contentValues, KEY_USER_ID + " = " + 1, null);
        db.close();
    }

    /**
     * Deletes the user's info
     */
    public void deleteUserInfo() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(SAVED_USER_DATA, KEY_USER_ID + " = " + 1, null);
        db.close();
    }

    /**
     * Getter for username
     */
    public String getUsername() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(SAVED_USER_DATA,
                new String[] {KEY_USERNAME},
                KEY_USER_ID + "=?",
                new String[] {"1"},
                null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor.getString(0);
    }

    /**
     * Getter for password
     */
    public String getPassword() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(SAVED_USER_DATA,
                new String[] {KEY_PASSWORD},
                KEY_USER_ID + "=?",
                new String[] {"1"},
                null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor.getString(0);
    }
}
