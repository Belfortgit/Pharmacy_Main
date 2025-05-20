package com.user;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.entities.User;
import com.user.exception.InvalidRoleException;
import com.user.exception.InvalidUserIdException;
import com.user.service.UserService;

import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AdminDoctorPharmacyApplicationTests {

	@Mock
	UserService service;
	
	@Test
	void testForUserAdd() throws InvalidRoleException {
		
		User u = new User (1,"userName","email","password","role", (long)23456790);
		
		when(service.add(u)).thenReturn(u);
		assertSame(service.add(u),u);
	}
	
	@Test
	void testForUserAddThrowException() throws InvalidRoleException {
		
		User u = new User (1,"userName","email","password","role", (long)23456790);
		when(service.add(u)).thenThrow(new InvalidRoleException(null));
		assertThrows(InvalidRoleException.class, () -> {
			service.add(u);
		});
	}
	
	@Test
	void testForUserGet() {
		
		User u = new User (1,"userName","email","password","role", (long)23456790);
		List<User> list = new ArrayList();
		list.add(u);
		
		when(service.view()).thenReturn(list);
		assertSame(service.view(),list);
	}
	
	@Test
	void testForUserGetByRole() throws InvalidRoleException {
		
		User u = new User (1,"userName","email","password","role", (long)23456790);
		List<User> list = new ArrayList();
		list.add(u);
		
		when(service.viewByRole("role")).thenReturn(list);
		assertSame(service.viewByRole("role"),list);
	}
	
	@Test
	void testForUserGetById() throws InvalidUserIdException 
	{	
		User u = new User (1,"userName","email","password","role", (long)23456790);
		
		when(service.viewById(1)).thenReturn(u);
		assertSame(service.viewById(1),u);
	}
	
	@Test
	void testForUserGetByIdThrowsException() throws InvalidUserIdException 
	{	
		when(service.viewById(999999)).thenThrow(new InvalidUserIdException (null));
		assertThrows(InvalidUserIdException .class, () -> {
			service.viewById(999999);
		});
	}
	
	@Test
	void testForUserDelete() throws InvalidUserIdException {
		
		when(service.delete(1)).thenReturn("Deleted");
		assertSame(service.delete(1),"Deleted");
	}
	
	@Test
	void testForUserDeleteThrowException() throws InvalidUserIdException {
		
		when(service.delete(98787)).thenThrow(new InvalidUserIdException(null));
		assertThrows(InvalidUserIdException.class, () -> {
			service.delete(98787);
		});
	}

}
