package com.userfront.service;

import java.util.Set;

import com.userfront.domain.User;
import com.userfront.domain.security.UserRole;

public interface UserService {

	
	User findByUsername(String username);

    User findByEmail(String email);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);
    
    User saveUser (User user);
    
    public User createUser(User user, Set<UserRole> userRoles);
    
  
    
   
}
