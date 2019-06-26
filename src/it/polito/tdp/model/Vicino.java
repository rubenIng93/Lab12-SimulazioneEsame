package it.polito.tdp.model;




public class Vicino implements Comparable<Vicino>{
	
	private Integer vicino;
	private Double distanza;
	
	public Vicino(Integer vicino, Double distanza) {
		super();
		this.vicino = vicino;
		this.distanza = distanza;
	}

	public Integer getVicino() {
		return vicino;
	}

	public void setVicino(Integer vicino) {
		this.vicino = vicino;
	}

	public Double getDistanza() {
		return distanza;
	}

	public void setDistanza(Double distanza) {
		this.distanza = distanza;
	}

	@Override
	public int compareTo(Vicino o) {
		return this.distanza.compareTo(o.distanza);
	}
	
	
	
	

}
