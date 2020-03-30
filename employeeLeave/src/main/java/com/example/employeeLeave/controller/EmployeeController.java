package com.example.employeeLeave.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeLeave.exception.ResourceNotFoundException;
import com.example.employeeLeave.model.Employee;
import com.example.employeeLeave.service.EmployeeService;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	// Get all employees
	
	@GetMapping("/employees")
	public List<Employee> getAll(){
	      return employeeService.getAllEmployees();
	    }
	
	// Get employee By Id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException{
		return employeeService.getEmployeeById(employeeId);
	}
    
//	// Get employee By id Alternative
//	@GetMapping("/emp/{id}")
//    public Employee getEmployeetByID(@PathVariable Long id) throws ResourceNotFoundException {
//      Optional<Employee> optEmployee = employeeRepository.findById(id);
//      if(optEmployee.isPresent()) {
//        return optEmployee.get();
//      }else {
//        throw new ResourceNotFoundException("Employee not found with id " + id);
//      }
//	}
	
	
	// Post employee
	@PostMapping("/employees")
	public Employee setEmployee(@Valid @RequestBody Employee employee) {
	  return employeeService.createEmployee(employee);
	 }
	
	//Update employee
	@PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                   @Valid @RequestBody Employee employeeUpdated) throws ResourceNotFoundException {
        return employeeService.updateEmployeeById(id, employeeUpdated);
        		
    }
	
	// Delete employee
	@DeleteMapping("/employees/{id}")
    public String dropEmployee(@PathVariable Long id) throws ResourceNotFoundException {
        return employeeService.deleteEmployeeById(id);
	}
}
