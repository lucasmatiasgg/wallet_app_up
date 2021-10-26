package com.up.tx.manager.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}