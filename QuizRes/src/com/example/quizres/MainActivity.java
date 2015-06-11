package com.example.quizres;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteException;

public class MainActivity extends Activity {
    private int miProgreso = 100;
    private ProgressBar progreso;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		progreso=(ProgressBar)findViewById(R.id.progreso);
        progreso.setVisibility(View.INVISIBLE);
        new Thread(myThread).start();
	}
	
	private Runnable myThread = new Runnable() { 
        public void run() {
            while (miProgreso>0) {
                try {
                	if (miProgreso==50) {
                		cargarDB();
                    } else if (miProgreso==1) {
                    	cargarActivity();
                    }
                	miProgreso--; 
                    progreso.setProgress(miProgreso);
                    Thread.sleep(30); 
                } catch(Throwable t) {}
            }
        }
    };
    
    public void cargarActivity() {
		Intent intent=new Intent(this,HomeAct.class);
		this.startActivity(intent);
		this.finish();
    }
    
    public void cargarDB() {
    	BaseDatos datos = new BaseDatos (this,"db_quizres.sqlite3",null,1);
    	try {
			datos.openDB();
		} catch (SQLiteException e) {
			Toast.makeText(this, "Error al abrir la base de datos",Toast.LENGTH_LONG).show();
		}
    	datos.close();
    }
}
