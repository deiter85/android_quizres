package com.example.quizres;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemPuntuacionAdapter extends BaseAdapter {
	protected Activity activity;
	protected ArrayList<ItemPuntos> items;
	         
	public ItemPuntuacionAdapter(Activity activity, ArrayList<ItemPuntos> items) {
		this.activity = activity;
		this.items = items;
	}

	public int getCount() {
		return items.size();
	}
	 
	public Object getItem(int position) {	
		return items.get(position);
	}
		
	public long getItemId(int position) {	
		return items.get(position).getId();
	}
	
	public View getView(int position, View contentView, ViewGroup parent) {
		View vi=contentView;	         
	    if(contentView == null) {	   
	    	LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);	      
	    	vi = inflater.inflate(R.layout.list_item_layout, null);
	    }         
	    ItemPuntos item = items.get(position);    
	    TextView posicion = (TextView) vi.findViewById(R.id.posicion);
	    posicion.setText(item.getPosicion());    
	    TextView nombre = (TextView) vi.findViewById(R.id.nombre);
	    nombre.setText(item.getNombre());   
	    TextView tipo = (TextView) vi.findViewById(R.id.puntos);
	    tipo.setText(item.getPuntos());
	    return vi;	 
	}
}