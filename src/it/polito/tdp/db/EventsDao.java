package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.model.Event;


public class EventsDao {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Year> getAnni(){
		
		String sql = "SELECT DISTINCT YEAR(e.reported_date) AS anni " + 
				"FROM `events` e";
		
		try {
			
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			List<Year> anni = new ArrayList<>();
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				anni.add(Year.of(rs.getInt("anni")));
			}
			
			conn.close();
			
			return anni;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Integer> listAllDistrict(Year anno) {
		
		String sql = "SELECT DISTINCT district_id FROM events WHERE YEAR(reported_date) = ?";
		
		try {
			
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno.getValue());
			ResultSet rs = st.executeQuery();
			List<Integer> distretti = new ArrayList<>();
			
			while(rs.next()) {
				distretti.add(rs.getInt("district_id"));
			}
			
			conn.close();
			
			return distretti;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	public Double getLatMedia(Year anno, Integer distretto) {
		
		String sql = "SELECT AVG(geo_lat) AS media FROM events WHERE YEAR(reported_date) = ? AND district_id = ?";
		
		try {
			
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno.getValue());
			st.setInt(2, distretto);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				conn.close();
				return rs.getDouble("media");
			}
			
			conn.close();
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public Double getLonMedia(Integer distretto, Year anno) {
		
		String sql = "SELECT AVG(geo_lon) AS media FROM events WHERE YEAR(reported_date) = ? AND district_id = ?";
		
		try {
			
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno.getValue());
			st.setInt(2, distretto);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				conn.close();
				return rs.getDouble("media");
			}
			
			conn.close();
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
