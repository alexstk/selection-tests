package com.example.demo.repository;

import com.example.demo.model.Covid;
import com.example.demo.repository.custom.CovidCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CovidRepository extends JpaRepository<Covid, Long>, CovidCustomRepository {

//  Page<Covid> findAll(Pageable pageable);

}
