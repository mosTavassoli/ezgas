package it.polito.ezgas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.polito.ezgas.entity.User;


public interface UserRepository extends JpaRepository<User,Integer>{
	
}
