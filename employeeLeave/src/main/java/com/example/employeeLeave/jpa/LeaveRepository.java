package com.example.employeeLeave.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeeLeave.model.Leave;

public interface LeaveRepository  extends JpaRepository<Leave, Long> {
	  List<Leave> findByEmployeeId(Long employeeId);  
	}