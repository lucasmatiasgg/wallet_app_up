package com.up.tx.manager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.up.tx.manager.login.dto.UserDto;
import com.up.tx.manager.login.repository.UserRepository;
import com.up.tx.manager.login.service.UserService;

@RunWith(SpringRunner.class)
public class UserServiceTest {

	@TestConfiguration
    static class UserServiceTestContextConfiguration {
 
		@Bean
		public PasswordEncoder pass() {
			return new PasswordEncoder() {
				
				@Override
				public boolean matches(CharSequence arg0, String arg1) {
					return false;
				}
				
				@Override
				public String encode(CharSequence arg0) {
					return null;
				}
			};
		}
		
		@Bean
        public UserService userService() {
            return new UserService();
        }
    }
    
	@Autowired
    private PasswordEncoder passwordEncoder;
	
    @Autowired
    private UserService userService;
    
    @MockBean
    private UserRepository userRepository;
	
    
    @Before
    public void setUp() {
    	UserDto user = new UserDto(2L, "lgomez", "pass", "Lucas", "Gomez", false, "", "Calle 124", "20112223334", "11222333");
        
    	Mockito.when(userRepository.findUserByUserName(user.getUserName()))
          .thenReturn(user);
        
        Mockito.when(userRepository.getPasswordByUserName(user.getUserName())).thenReturn(user.getPassword());
        
        Mockito.when(userRepository.findUserByCuit(user.getCuit())).thenReturn(user);
    }
    
    @Test
    public void getUserInfo() throws Exception {
    	UserDto user = new UserDto(2L, "lgomez", "pass", "Lucas", "Gomez", false, "", "Calle 124", "20112223334", "11222333");
        UserDto found = userRepository.findUserByUserName("lgomez");
     
         assertThat(found.getUserName()).isEqualTo(user.getUserName());
         assertThat(found.getDni()).isEqualTo(user.getDni());
      
    }
    
    @Test
    public void validateCredentials() throws Exception {
    	UserDto user = new UserDto(2L, "lgomez", "pass", "Lucas", "Gomez", false, "", "Calle 124", "20112223334", "11222333");
        String password = userRepository.getPasswordByUserName("lgomez");
         
         assertThat(passwordEncoder.matches(password, user.getPassword()));
      
    }
    
    
    @Test
    public void getInfoUserByCuit() throws Exception {
    	
    	UserDto user = new UserDto(2L, "lgomez", "pass", "Lucas", "Gomez", false, "", "Calle 124", "20112223334", "11222333");
        UserDto found = userRepository.findUserByCuit("20112223334");
     
         assertThat(found.getUserName()).isEqualTo(user.getUserName());
         assertThat(found.getCuit()).isEqualTo(user.getCuit());
         
    }
    
}
