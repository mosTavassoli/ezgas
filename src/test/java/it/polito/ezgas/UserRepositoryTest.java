package it.polito.ezgas;


import org.junit.Assert;
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
	     
	    @Test
	    public void testRepository() 
	    {
	        User user = new User();
	        user.setUserName("user1");
	        user.setPassword("user123");
	        user.setEmail("user123");
	        user.setReputation(5);
	        user.setAdmin(false);
	        
	         
	        userRepo.save(user);
	         
	        Assert.assertNotNull(user.getUserId());
	    }


}
