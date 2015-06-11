package com.example.quizres;

import java.util.ArrayList;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class PuntuacionAct extends Activity {
	private String posicion[]={"I","II","III","IV","V","VI","VII","VIII","IX","X"};
	private ListView lista;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.pantallapuntuacion);
		lista=(ListView)findViewById(R.id.lista);
        ArrayList<ItemPuntos> itemsCompra = obtenerItems();    
	    ItemPuntuacionAdapter adapter = new ItemPuntuacionAdapter(this, itemsCompra);  	    	   
	    lista.setAdapter(adapter);
	    lista.setEnabled(false); 
	}
	
	private ArrayList<ItemPuntos> obtenerItems() {	
		ArrayList<ItemPuntos> items = new ArrayList<ItemPuntos>();
		BaseDatos datos = new BaseDatos (this,"db_quizres.sqlite3",null,1);
		SQLiteDatabase db=datos.getReadableDatabase();
		Cursor cursor=db.rawQuery("SELECT * From puntuacion", null);
		while(cursor.moveToNext()) {
			int i=Integer.parseInt(cursor.getString(0));
			items.add(new ItemPuntos(i, posicion[i-1], cursor.getString(1),cursor.getString(2)));
		}
		cursor.close();
		db.close();
		datos.close();
		return items;	 
	}
	
	public void pulsarAtras(View v) {
		this.finish();
	}
}
