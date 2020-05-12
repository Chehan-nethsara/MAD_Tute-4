package DATABASE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    public static final String DATABSE_NAME = "Users_info";

    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABSE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UsersMaster.Users.Table_Name + "(" +
                        UsersMaster.Users._ID + "INTEGER PRIMARY KEY," +
                        UsersMaster.Users.Column_Name_UserName + " TEXT," +
                        UsersMaster.Users.Column_Name_Password + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addInfo(String username, String password){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.Column_Name_UserName, username);
        values.put(UsersMaster.Users.Column_Name_Password, password);

        long newRowID = db.insert(UsersMaster.Users.Table_Name, null, values);
    }

    public List readAllInfo(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                UsersMaster.Users._ID,
                UsersMaster.Users.Column_Name_UserName,
                UsersMaster.Users.Column_Name_Password
        };

        String sortOrder = UsersMaster.Users.Column_Name_UserName + "DESC";

        Cursor cursor = db.query(
                UsersMaster.Users.Table_Name,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List userNames = new ArrayList<>();
        List passwords = new ArrayList<>();

        while (cursor.moveToNext()){
            String username = cursor.getString( cursor.getColumnIndexOrThrow(UsersMaster.Users.Column_Name_UserName));
            String password = cursor.getString( cursor.getColumnIndexOrThrow(UsersMaster.Users.Column_Name_Password));
            userNames.add(username);
            passwords.add(password);
        }

        cursor.close();
        return userNames;
    }

    public List<String> readInfo(String usernames, String userpasswords){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                UsersMaster.Users._ID,
                UsersMaster.Users.Column_Name_UserName,
                UsersMaster.Users.Column_Name_Password
        };

        String sortOrder = UsersMaster.Users.Column_Name_UserName + " DESC";

        Cursor cursor = db.query(UsersMaster.Users.Table_Name,
                projection,
                null, null, null , null,
                sortOrder
        );

        List<String> userNames = new ArrayList<>();
        List<String> passwords = new ArrayList<>();

        while (cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.Column_Name_UserName));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.Column_Name_Password));

            if(username.equals(username) && pass.equals(userpasswords)){
                userNames.add(username);
                userNames.add(pass);
            }

        }

        cursor.close();
        return userNames;
    }

    public void deleteInfo(String userName){
        SQLiteDatabase db = getReadableDatabase();

        String selection = UsersMaster.Users.Column_Name_UserName + " LIKE ?";
        String[] selectionArgs = { userName };

        db.delete(UsersMaster.Users.Table_Name, selection, selectionArgs );

    }

    public void updateInfo(String userName, String password){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.Column_Name_Password, password);

        String selection = UsersMaster.Users.Column_Name_UserName + " LIKE ?";
        String[] selectionArgs = {userName};

        int count = db.update(
                UsersMaster.Users.Table_Name,
                values,
                selection,
                selectionArgs
        );
    }
}
