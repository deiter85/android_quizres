package com.example.quizres;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FinalAct extends Activity {
	private int aciertos;
	private int tiempo;
	private int puntos;
	private TextView tvPuntos;
	private EditText etNombre;
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.pantallafinal);
		tvPuntos=(TextView)findViewById(R.id.textoPuntos);
		etNombre=(EditText)findViewById(R.id.campoTexto);
		aciertos=getIntent().getIntExtra("Correctas",0);
		tiempo=getIntent().getIntExtra("Nivel", 100);
		puntos=aciertos*10;
		if(tiempo==50) {
			puntos*=2;
		}
		tvPuntos.setText("Tu puntacion es= "+String.valueOf(puntos));
		
	}

	public void pulsarBVolver(View v) {
		intent=new Intent(this, JuegoAct.class);
		this.startActivity(intent);
		this.finish();
	}
	
	public void pulsarBGuardar(View v) {
		String auxNombre="";
		int auxPuntos=0;
		boolean cambio=false;
		BaseDatos datos = new BaseDatos (this,"db_quizres.sqlite3",null,1);
		SQLiteDatabase db=datos.getReadableDatabase();
		Cursor cursor = null;
		ContentValues registro=new ContentValues();
		if (etNombre.getText().toString().isEmpty()==false) {		
			if(db != null){
				try {
					cursor=db.rawQuery("SELECT * From puntuacion", null);
					while(cursor.moveToNext()) {
						if (cambio==true) {
							registro.put("nombre", auxNombre);
							registro.put("puntos", auxPuntos);
							db.update("puntuacion",registro,"_id="+cursor.getString(0),null);
							Toast.makeText(this, "Configuración guardada correctamente", Toast.LENGTH_SHORT).show();
						} 
						if (puntos>Integer.parseInt(cursor.getString(2)) && cambio==false) {
							registro.put("nombre", etNombre.getText().toString());
							registro.put("puntos",puntos);
							db.update("puntuacion",registro,"_id="+cursor.getString(0),null);
							cambio=true;
						} 
						auxNombre=cursor.getString(1);
						auxPuntos=Integer.parseInt(cursor.getString(2));
					}
				} catch(Exception e) {
					Toast.makeText(this,"Fallo al añadir el registro",Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(this, "Error al entrar en la base de datos",Toast.LENGTH_SHORT).show();	
			}
		} else {
			Toast.makeText(this,"Introduce un nombre válido",Toast.LENGTH_SHORT).show();
		}
		cursor.close();
		db.close();
		datos.close();
	}
	
	public void pulsarAtras(View v) {
		this.finish();
	}
}
