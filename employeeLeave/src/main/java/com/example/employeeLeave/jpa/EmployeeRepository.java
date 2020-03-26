package com.example.employeeLeave.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeeLeave.model.Employee;
 
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}