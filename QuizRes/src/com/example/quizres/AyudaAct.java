package com.example.quizres;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class AyudaAct extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.pantallaayuda);
	}
	
	public void pulsarAtras(View v) {
		this.finish();
	}
}
