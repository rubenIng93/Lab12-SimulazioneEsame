package it.polito.tdp.model;

import java.time.LocalTime;

public class Evento implements Comparable<Evento>{
	
	public enum TipoEvento{
		CRIMINE,
		ARRIVA_AGENTE,
		GESTITO
	}
	
	private TipoEvento tipo;
	private LocalTime data;
	private Event crimine;
	
		
	public Evento(TipoEvento tipo, LocalTime data, Event crimine) {
		super();
		this.tipo = tipo;
		this.data = data;
		this.crimine = crimine;
	}
	public TipoEvento getTipo() {
		return tipo;
	}
	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}
	public LocalTime getData() {
		return data;
	}
	public void setData(LocalTime data) {
		this.data = data;
	}
	public Event getCrimine() {
		return crimine;
	}
	public void setCrimine(Event crimine) {
		this.crimine = crimine;
	}
	@Override
	public int compareTo(Evento arg0) {
		return this.data.compareTo(arg0.getData());
	}
	
	

}
