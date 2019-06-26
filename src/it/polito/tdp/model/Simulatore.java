package it.polito.tdp.model;

import java.time.Month;
import java.time.Year;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulatore {
	
	private Integer malGestiti;
	private Integer N;
	private Year anno;
	private Month mese;
	private Integer giorno;
	private Graph<Integer, DefaultWeightedEdge> grafo;
	private PriorityQueue<Evento> queue;
	
	//mappa di distretto - agenti
	private Map<Integer, Integer> agenti;
	
	private void init() {
		
	}
	
	private void run() {
		
	}

}
