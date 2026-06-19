package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Line;

public interface LineRepository extends JpaRepository<Line, Long>{

}
