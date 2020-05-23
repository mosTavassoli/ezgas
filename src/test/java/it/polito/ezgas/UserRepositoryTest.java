package it.polito.ezgas;


import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	    @Autowired
	    UserRepository userRepo;
	    
	    @Before
	    public void init() {
	        userRepo.save(new User("user1", "password1", "user1@example.com", 2));
	        userRepo.save(new User("user2", "password2", "user2@example.com", -3));
	        userRepo.save(new User("user3", "password3", "user3@example.com", 1));
	        userRepo.save(new User("user4", "password4", "user4@example.com", 5));
	    }
	    
	    @Test
	    public void testFindByEmail() {
	        assertNotNull(userRepo.findByEmail("user3@example.com"));
	    }


}
