package com.example.quizres;

import java.util.Random;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class JuegoAct extends Activity {;
	private int miProgreso = 100;
    private int totalPreguntas;
    private int preguntaActual;
    private int tiempo;
    private int aciertos;
    private int nPreguntas;
    private boolean inicio;
    private String respuestaCorrecta;
	private ProgressBar progreso;
	private int [] orden;
    private TextView tvAvance;
    private TextView tvCategoria;
    private TextView tvPregunta;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    Intent intent;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {  
    	super.onCreate(savedInstanceState);
    	this.setContentView(R.layout.pantallajuego);
    	progreso=(ProgressBar)findViewById(R.id.progresoJuego);
        tvAvance=(TextView)findViewById(R.id.textoAvance);
        tvCategoria=(TextView)findViewById(R.id.textoCategoria);
        tvPregunta=(TextView)findViewById(R.id.textoPregunta);
        btn1=(Button)findViewById(R.id.boton1);
        btn2=(Button)findViewById(R.id.boton2);
        btn3=(Button)findViewById(R.id.boton3);
        btn4=(Button)findViewById(R.id.boton4);
        cargarPregunta();    
    }
	
	@Override
	protected void onStart() {
		super.onStart();
		new Thread(myThread).start();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean accion=true;
		if(keyCode==KeyEvent.KEYCODE_BACK)
			accion=false;
		return accion;
	}

	public void cargarPregunta() {
		BaseDatos datos = new BaseDatos (this,"db_quizres.sqlite3",null,1);
		SQLiteDatabase db=datos.getReadableDatabase();
	    int posicion[];
		inicio=getIntent().getBooleanExtra("Inicio", false);
		if (inicio==false) {
			SharedPreferences configuracion = getSharedPreferences("Configuracion",Context.MODE_PRIVATE);
			tiempo=configuracion.getInt("Nivel", 100);
			totalPreguntas=configuracion.getInt("NPreguntas", 10);
			Cursor cursor=db.rawQuery("SELECT _id From preguntas", null);
			if(cursor.moveToLast()){
				nPreguntas=Integer.parseInt(cursor.getString(0));
			}
			orden=llenarVector(nPreguntas);
			cursor.close();
		} else {
			tiempo=getIntent().getIntExtra("Tiempo", 100);
			totalPreguntas=getIntent().getIntExtra("TotalPreguntas", 10);
			orden=getIntent().getIntArrayExtra("Array");
		}
		preguntaActual=getIntent().getIntExtra("PreguntaActual", 1);
		aciertos=getIntent().getIntExtra("Correctas",0);
		tvAvance.setText("Pregunta "+String.valueOf(preguntaActual)+"/"+String.valueOf(totalPreguntas));
		Cursor cursor=db.rawQuery("SELECT categoria, pregunta, res_cor, res1, res2, res3 From preguntas where cod_pre=" + orden[preguntaActual-1], null);
		if (cursor.moveToFirst()) {
			tvCategoria.setText(cursor.getString(0));
			tvPregunta.setText(cursor.getString(1));
			respuestaCorrecta=cursor.getString(2);
			posicion=llenarVector(4);
			for (int i=0; i<posicion.length;i++) {
				if (posicion[i]==1) {
					btn1.setText(" "+cursor.getString(i+2)+" ");
				} else if (posicion[i]==2) {
					btn2.setText(" "+cursor.getString(i+2)+" ");
				} else if (posicion[i]==3) {
					btn3.setText(" "+cursor.getString(i+2)+" ");
				} else if (posicion[i]==4) {
					btn4.setText(" "+cursor.getString(i+2)+" ");
				}
			}
		}
		cursor.close();
		db.close();
		datos.close();
	}
	
	public int[] llenarVector(int numero) {
		int n=numero;
		int k=n;
		int resultado[]=new int[n];
		int numeros[]=new int[n];
		Random rnd=new Random();
		int res;
		for(int i=0;i<n;i++) {
			numeros[i]=i+1;
		}
		for(int i=0;i<n;i++) {	    
			res=rnd.nextInt(k);            
			resultado[i]=numeros[res];
			numeros[res]=numeros[k-1];
			k--;	            
		}
		return resultado;
	}

	public void cargarActivity() {
		this.finish();
		if (preguntaActual<totalPreguntas) {
			intent=new Intent(this, JuegoAct.class);
			intent.putExtra("Inicio",true);
			intent.putExtra("Tiempo",tiempo);
			intent.putExtra("TotalPreguntas",totalPreguntas);
			intent.putExtra("Array",orden);
			intent.putExtra("PreguntaActual",++preguntaActual);
			intent.putExtra("Correctas",aciertos);
			this.startActivity(intent);
		} else {
			intent=new Intent(this, FinalAct.class);
			intent.putExtra("Correctas",aciertos);
			intent.putExtra("Nivel",tiempo);
			this.startActivity(intent);
		}
	}
	
	public void pulsarB1(View v) {
		if (btn1.getText().equals(" "+respuestaCorrecta+" ")) {
			aciertos++;
		}
		miProgreso=1;
	}
	
	public void pulsarB2(View v) {
		if (btn2.getText().equals(" "+respuestaCorrecta+" ")) {
			aciertos++;
		}
		miProgreso=1;
	}
	
	public void pulsarB3(View v) {
		if (btn3.getText().equals(" "+respuestaCorrecta+" ")) {
			aciertos++;
		}
		miProgreso=1;
	}
	
	public void pulsarB4(View v) {
		if (btn4.getText().equals(" "+respuestaCorrecta+" ")) {
			aciertos++;
		}
		miProgreso=1;
	}
	
	private Runnable myThread = new Runnable() { 
		public void run() {
            while (miProgreso>0) {
                try {
                	if (miProgreso==1) {
                		btn1.setEnabled(false);
                		btn2.setEnabled(false);
                		btn3.setEnabled(false);
                		btn4.setEnabled(false);
                		miProgreso--; 
                        progreso.setProgress(miProgreso);
                    	cargarActivity();
                    }
                	miProgreso--; 
                    progreso.setProgress(miProgreso);
                    Thread.sleep(tiempo); 
                } catch(Throwable t) {}
            }
        }
	};
}
