package com.example.employeeLeave.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeLeave.exception.ResourceNotFoundException;
import com.example.employeeLeave.jpa.EmployeeRepository;
import com.example.employeeLeave.model.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	// Get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
	      return employeeRepository.findAll();
	    }
	
	// Get employee By Id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
	throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(employeeId)
		         .orElseThrow(
		        		 ()-> new ResourceNotFoundException("Employee not found for this ID ::" + employeeId)
				             );
		return ResponseEntity.ok().body(employee);
	}
    
	// Get employee By id Alternative
	@GetMapping("/emp/{id}")
    public Employee getEmployeetByID(@PathVariable Long id) throws ResourceNotFoundException {
      Optional<Employee> optEmployee = employeeRepository.findById(id);
      if(optEmployee.isPresent()) {
        return optEmployee.get();
      }else {
        throw new ResourceNotFoundException("Employee not found with id " + id);
      }
	}
	
	
	// Post employee
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
	  return employeeRepository.save(employee);
	 }
	
	//Update employee
	@PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                   @Valid @RequestBody Employee employeeUpdated) throws ResourceNotFoundException {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(employeeUpdated.getName());
                    employee.setAge(employeeUpdated.getAge());
                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }
	
	// Delete employee
	@DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Long id) throws ResourceNotFoundException {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employeeRepository.delete(employee);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }
	
}
