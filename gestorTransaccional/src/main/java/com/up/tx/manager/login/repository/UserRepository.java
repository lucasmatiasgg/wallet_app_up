package com.up.tx.manager.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.up.tx.manager.login.dto.UserDto;
import com.up.tx.manager.login.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT new com.up.tx.manager.login.dto.UserDto(u.id, u.userName, u.password, u.firstName, u.lastName, u.isCommerce, u.socialReason, u.address, u.cuit, u.dni) " +
            "FROM User u \n" +
            "WHERE u.userName = ?1" )
    UserDto findUserByUserName(String userName);

    @Query(value= "SELECT count(*) " +
            "FROM user u \n" +
            "WHERE user_name = ?1", nativeQuery = true )
    Integer userExists(String userName);
    
    @Query("SELECT u.password " +
            "FROM User u \n" +
            "WHERE u.userName = ?1" )
    String getPasswordByUserName(String userName);

    @Query("SELECT new com.up.tx.manager.login.dto.UserDto(u.id, u.userName, u.password, u.firstName, u.lastName, u.isCommerce, u.socialReason, u.address, u.cuit, u.dni) " +
            "FROM User u \n" +
            "WHERE u.cuit = ?1" )
    UserDto findUserByCuit(String cuit);
    
    @Query("SELECT u.id " +
            "FROM User u \n" +
            "WHERE u.userName= ?1" )
    Long getUserId(String userName);
    
    @Transactional
	@Modifying
	@Query("UPDATE User " +
            "SET first_name = ?2, last_name = ?3, address = ?4, dni = ?5 \n" +
            "WHERE id = ?1" )
	void updateUser(Long id, String firstName, String lastName, String address, String dni);
}