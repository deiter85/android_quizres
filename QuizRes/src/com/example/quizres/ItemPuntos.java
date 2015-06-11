package com.example.quizres;

public class ItemPuntos {	
	protected long id;
	protected String posicion;
	protected String nombre;
	protected String puntos;

	public ItemPuntos(long id, String posicion, String nombre, String puntos) {	
		this.id = id;
		this.posicion = posicion;
		this.nombre = nombre;
		this.puntos = puntos;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPosicion() {
		return posicion;
	}
	
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPuntos() {
		return puntos;
	}
	
	public void setPuntos(String puntos) {
		this.puntos = puntos;
	}	
}
