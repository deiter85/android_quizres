package com.example.quizres;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ConfiguracionAct extends Activity {
    private int tiempo;
    private int totalPreguntas;
    private String txtSpiner1[]={"Normal","Dificil"};
    private String txtSpiner2[]={"7","10","15"};
    private Spinner sp1;
    private Spinner sp2;
	SharedPreferences configuracion;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.pantallaconfiguracion);
		sp1=(Spinner)findViewById(R.id.spinnerNivel);
		sp2=(Spinner)findViewById(R.id.spinnerNPreguntas);
		ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,txtSpiner1);
        sp1.setAdapter(adapter1);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,txtSpiner2);
        sp2.setAdapter(adapter2);
		configuracion = getSharedPreferences("Configuracion",Context.MODE_PRIVATE);
		this.cargarSpinner();
	}
	
	public void cargarSpinner() {
		tiempo=configuracion.getInt("Nivel", 100);
		totalPreguntas=configuracion.getInt("NPreguntas", 10);
		if(tiempo==100) {
			sp1.setSelection(0);
		} else {
			sp1.setSelection(1);
		}
		if (totalPreguntas==10) {
			sp2.setSelection(1);
		} else if (totalPreguntas==7) {
			sp2.setSelection(0);
		} else {
			sp2.setSelection(2);
		}
	}
	
	public void pulsarBGuardar(View v) {
		configuracion = getSharedPreferences("Configuracion",Context.MODE_PRIVATE);
		String seleccion1=sp1.getSelectedItem().toString();
		String seleccion2=sp2.getSelectedItem().toString();
		Editor editor=configuracion.edit();
		editor.putInt("NPreguntas",Integer.parseInt(seleccion2));
		if (seleccion1.equals("Normal")) {
			editor.putInt("Nivel",100);
		} else {
			editor.putInt("Nivel",50);
		}
		editor.commit();
		Toast.makeText(this, "Configuración guardada correctamente", Toast.LENGTH_SHORT).show();
		this.cargarSpinner();
	}
	
	public void pulsarAtras(View v) {
		this.finish();
	}

}
