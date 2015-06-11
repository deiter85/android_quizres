package com.example.quizres;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class AcercaAct extends Activity {
	TextView textEnlace;
	String url = "https://www.gnu.org/licenses/gpl-3.0.html";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.pantallaacerca);
		textEnlace = (TextView)findViewById(R.id.textoGNU);
		textEnlace.setText(Html.fromHtml("<a href=" + url + ">GPLv3.0</a>"));
		textEnlace.setMovementMethod(LinkMovementMethod.getInstance());
	}
	
	public void pulsarAtras(View v) {
		this.finish();
	}
}
