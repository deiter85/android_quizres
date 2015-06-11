package com.example.quizres;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class HomeAct extends Activity {
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.pantallahome);
	}
	
	public void pulsarIAcerca(View v) {
		intent=new Intent(this, AcercaAct.class);
		this.startActivity(intent);
	}
	
	public void pulsarIAyuda(View v) {
		intent=new Intent(this, AyudaAct.class);
		this.startActivity(intent);
	}
	
	public void pulsarIConfiguracion(View v) {
		intent=new Intent(this, ConfiguracionAct.class);
		this.startActivity(intent);
	}
	
	public void pulsarBPartida(View v) {
		intent=new Intent(this, JuegoAct.class);
		this.startActivity(intent);
	}
	
	public void pulsarBPuntuacion(View v) {
		intent=new Intent(this, PuntuacionAct.class);
		this.startActivity(intent);
	}
	
	public void pulsarBSalir(View v) {
		this.finish();
	}
}
