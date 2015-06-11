package com.example.quizres;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class BaseDatos extends SQLiteOpenHelper {
	@SuppressLint("SdCardPath")
	private static String DB_PATH = "/data/data/com.example.quizres/databases/";
    private static String DB_NAME = "db_quizres.sqlite3";
    private final Context mContext;
    SQLiteDatabase mDataBase;
     
	public BaseDatos (Context contexto,String nombre,CursorFactory factory,int version) {	
		super(contexto,nombre,factory,version);
		this.mContext = contexto;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
	
	public void openDB() throws SQLiteException {
		createDataBase();
		String path = mContext.getDatabasePath(DB_NAME).getAbsolutePath();
		mDataBase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
	}
	
	public void createDataBase() throws SQLiteException {
		if (checkDataBase() == false) {
			SQLiteDatabase sq = this.getWritableDatabase();
			try {
				copyDataBase();
			} catch (IOException e) {
				
			} finally {
				sq.close();
			}
		}
	}
	
	public boolean checkDataBase() {
		File fileDB = null;
		fileDB = mContext.getApplicationContext().getDatabasePath(DB_NAME);
		boolean checkDB = fileDB.exists();
		return checkDB;
	}
	
	private void copyDataBase() throws IOException { 
        OutputStream databaseOutputStream = new FileOutputStream("" + DB_PATH + DB_NAME);
        InputStream databaseInputStream;
        byte[] buffer = new byte[1024];
        databaseInputStream = mContext.getAssets().open("db_quizres.sqlite3");
        while (databaseInputStream.read(buffer) > 0) {
            databaseOutputStream.write(buffer);
        }
        databaseInputStream.close();
        databaseOutputStream.flush();
        databaseOutputStream.close();
    }
}