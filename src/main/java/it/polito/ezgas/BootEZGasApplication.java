package it.polito.ezgas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;

@SpringBootApplication
public class BootEZGasApplication {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GasStationRepository gasStationRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BootEZGasApplication.class, args);
	}
	
	@PostConstruct
	public void setupDbWithData() throws SQLException{
		
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		conn.close();
		
		//Add admin
		User user = userRepository.findByEmail("admin@ezgas.com");
		if(user==null) {
			user= new User("admin", "admin", "admin@ezgas.com", 5);
			user.setAdmin(true);
			userRepository.save(user);
		}
		
		/**
		//Add test user
		user = userRepository.findByEmail("user@ezgas.com");
		if(user==null) {
			user= new User("user", "user", "user@ezgas.com", 5);
			user.setAdmin(false);
			userRepository.save(user);
		}
		
		//Add test gas station
		List<GasStation> gasStationList = gasStationRepository.findByProximity(-8.788462, -124.507958);
		GasStation gasStation;
		if(gasStationList.size()==0) {
			gasStation= new GasStation("gasStationName", "gasStationAddress", true, true, true, true, true, 
					"carSharing", -8.788462, -124.5079580, 0.0, 0.0, 0.0, 0.0, 0.0, 1, 
					(new Timestamp(new Date().getTime())).toString(), 0.0);
			gasStationRepository.save(gasStation);
		}
		**/
		/*
		 
		list all the users stored in the database and, if there is no an admin user create it
		 
			User user= new User("admin", "admin", "admin@ezgas.com", 5);
			user.setAdmin(true);
			
		and then save it in the db
	
			
		*/

	}

}
