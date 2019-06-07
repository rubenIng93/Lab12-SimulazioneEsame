package it.polito.tdp.model;

import java.time.Year;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.db.EventsDao;

public class Model {
	
	EventsDao dao;
	
	public Model() {
		dao = new EventsDao();
	}
	
	public List<Year> getAnni(){
		List<Year> anni = dao.getAnni();
		Collections.sort(anni);
		return anni;
	}
	
}
