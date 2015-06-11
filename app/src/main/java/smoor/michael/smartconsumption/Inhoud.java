package smoor.michael.smartconsumption;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

/**
 * Created by Michael on 11-6-2015.
 */
public class Inhoud extends SQLiteOpenHelper {
    private static final String DATABASE_PATH = "C/Users/Michael/AndroidStudioProjects/Smartconsumption/app/src/main/res/Database/";
    private static final String DATABASE_NAME = "SmartConsumptionDatabse.accdb";
    private static final int SCHEMA_VERSION = 1;

    private SQLiteDatabase dbSqlite;

    private final Context myContext;

    public Inhoud(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    
    public void createDatabase() {
        createDB();
    }

    private void createDB() {
        boolean dbExist = DBExists();

        if (!dbExist) {
            this.getReadableDatabase();

            copyDBFromResource();
        }
    }

    private boolean DBExists() {
        SQLiteDatabase db = null;

        try {
            String databasePath = DATABASE_PATH + DATABASE_NAME;
            db = SQLiteDatabase.openDatabase(databasePath, null,
                    SQLiteDatabase.OPEN_READWRITE);
            db.setLocale((Locale.getDefault()));
            db.setLockingEnabled(true);
            db.setVersion(1);

        } catch (SQLException e) {
            Log.e("SqlHelper", "database not found");
        }

        if (db != null) {
            db.close();
        }

        return db != null ? true : false;
    }

    private void copyDBFromResource() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String dbFilePath = DATABASE_PATH + DATABASE_NAME;

        try {
            inputStream = myContext.getAssets().open(DATABASE_NAME);

            outputStream = new FileOutputStream(dbFilePath);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            throw new Error("Problem copying database from resource file.");
        }
    }

    public synchronized void close() {

    }
}
