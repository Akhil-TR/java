package com.example.employeeLeave.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employeeLeave.exception.ResourceNotFoundException;
import com.example.employeeLeave.jpa.EmployeeRepository;
import com.example.employeeLeave.model.Employee;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository  employeeRepository;

	
	// Get all 
	@Transactional
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	// Get By Id
	@Transactional
	public ResponseEntity<Employee> getEmployeeById(Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(
       		 			()-> new ResourceNotFoundException("Employee not found for this ID ::" + employeeId)
		             );
	return ResponseEntity.ok().body(employee);
	}	
	
	// Post 
	@Transactional
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
		
	}
	
	// Update By Id
	@Transactional
	public Employee updateEmployeeById(Long id, Employee employeeUpdated) throws ResourceNotFoundException{
		return employeeRepository.findById(id).map(employee -> {
			            employee.setName(employeeUpdated.getName());
			            employee.setAge(employeeUpdated.getAge());
			            employee.setDesignation(employeeUpdated.getDesignation());
        return employeeRepository.save(employee);
        }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
		
	}
	
	// Delete By Id
	@Transactional 
	public String deleteEmployeeById(Long id) throws ResourceNotFoundException {
		return employeeRepository.findById(id).map(employee -> {
                    	employeeRepository.delete(employee);
        return "Delete Successfully!";
        }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }
		
	
}
