package it.polito.tdp.model;

import java.time.Year;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.db.EventsDao;

public class Model {
	
	private EventsDao dao;
	private Graph<Integer, DefaultWeightedEdge> grafo;
	private List<Integer> distretti;
	
	
	
	public Model() {
		dao = new EventsDao();
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	}
	
	public List<Year> getAnni(){
		List<Year> anni = dao.getAnni();
		Collections.sort(anni);
		return anni;
	}
	
	public void creaGrafo(Year anno) {
		this.distretti = dao.listAllDistrict(anno);
		Graphs.addAllVertices(this.grafo, distretti);
		
		for(Integer v1 : this.grafo.vertexSet()) {
			for(Integer v2 : this.grafo.vertexSet()) {
				if(!v1.equals(v2)) {
					if(this.grafo.getEdge(v1, v2) == null) {
						
						Double latMediaV1 = dao.getLatMedia(anno, v1);
						Double lonMediaV1 = dao.getLonMedia(v1, anno);
						
						Double latMediaV2 = dao.getLatMedia(anno, v2);
						Double lonMediaV2 = dao.getLonMedia(v2, anno);
						
						Double distanzaMedia = LatLngTool.distance(new LatLng(latMediaV1, lonMediaV1),
								new LatLng(latMediaV2, lonMediaV2),
								LengthUnit.KILOMETER);
						
						Graphs.addEdgeWithVertices(this.grafo, v1, v2, distanzaMedia);
					}
					
				}
			}
		}
		
		System.out.println("Grafo creato!");
		System.out.println("# vertici: " + this.grafo.vertexSet().size());
		System.out.println("# archi: " + this.grafo.edgeSet().size());
	}
	
	public List<Vicino> getVicini(Integer distretto){
		List<Vicino> vicini = new LinkedList<>();
		List<Integer> neighbors = Graphs.neighborListOf(grafo, distretto);
		for(Integer n : neighbors) {
			vicini.add(new Vicino(n, this.grafo.getEdgeWeight(this.grafo.getEdge(distretto, n))));
		}
		Collections.sort(vicini);
		return vicini;
	}

	public List<Integer> getDistretti() {
		return this.distretti;
	}
	
	
}
