package com.up.tx.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.up.tx.manager.model.Configuration;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, String> {

}
