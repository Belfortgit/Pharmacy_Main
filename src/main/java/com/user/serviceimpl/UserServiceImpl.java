package com.user.serviceimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.entities.User;
import com.user.exception.InvalidRoleException;
import com.user.exception.InvalidUserIdException;
import com.user.repository.UserRepository;
import com.user.service.JWTService;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepository repo;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public boolean verifyStatus(String str) {
        Set<String> statusMap = new HashSet();
        statusMap.add("PLACED");
        statusMap.add("VERIFIED");
        statusMap.add("PICKED_UP");
        statusMap.add("CANCELLED");
        statusMap.add("OUT_OF_STOCK");

        return statusMap.contains(str);
    }
    
    @Override
    public User add(User user) throws InvalidRoleException {
    	
        logger.info("Adding user: {}", user);
        
        user.setRole(user.getRole().toUpperCase());
        
		if(user.getRole().equals("DOCTOR") || user.getRole().equals("ADMIN"))
		{
			logger.info("User added successfully with id: {}", user.getUserId());
			return repo.save(user);
		}
		else
		{
			logger.warn("User Status is not Valid");
			throw new InvalidRoleException("The Role Should either be Doctor or Admin.!");
		}
    }

    @Override
    public List<User> view() {
        logger.info("Fetching all users.");
        List<User> users = repo.findAll();
        logger.info("Retrieved {} users.", users.size());
        return users;
    }

    @Override
    public List<User> viewByRole(String role) throws InvalidRoleException {
    	
        logger.info("Fetching users by role: {}", role);
        role = role.toUpperCase();
		if(role.equals("DOCTOR") || role.equals("ADMIN"))
		{
			logger.info("Retrieved users with role {}.", role);
			return repo.findByRole(role);
		}
		else 
		{
			logger.warn("Invalid Role specified {}", role);
			throw new InvalidRoleException("The Role Should either be Doctor or Admin.!");
		}
    }

    @Override
    public User viewById(int userid) throws InvalidUserIdException {
        logger.info("Fetching user with id: {}", userid);
        Optional<User> obj = repo.findById(userid);
        if (obj.isPresent()) {
            logger.info("User with id {} found.", userid);
            return obj.get();
        } else {
            logger.warn("User with id {} not found.", userid);
            throw new InvalidUserIdException("No such User with id " + userid);
        }
    }

    @Override
    public String delete(int userid) throws InvalidUserIdException {
        logger.info("Deleting user with id: {}", userid);
        Optional<User> obj = repo.findById(userid);
        if (obj.isPresent()) {
            repo.deleteById(userid);
            logger.info("User with id {} deleted successfully.", userid);
            return "User with id " + userid + " Deleted..............";
        } else {
            logger.warn("User with id {} not found, cannot delete.", userid);
            throw new InvalidUserIdException("No such User with id " + userid);
        }
    }

	
	@Override
	public User findByUserName(String username) {
		return repo.findByUserName(username);
	}
	
	

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }

    @Override
    public String verify(User user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUserName());
        } else {
            return "fail";
        }
    }
	
}