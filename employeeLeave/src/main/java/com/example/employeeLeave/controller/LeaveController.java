package com.example.employeeLeave.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.employeeLeave.jpa.LeaveRepository;
import com.example.employeeLeave.model.Leave;


@RestController
@RequestMapping("/api")
public class LeaveController {
	@Autowired
	private LeaveRepository leaveRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// Get all leave 
	@GetMapping("/leave")
	  public List<Leave> getAllLeave(){
	    return leaveRepository.findAll();
	  }
	
	
	// Get leave By Employee Id
	@GetMapping("/employees/{employeeId}/leave")
	public List<Leave> getLeaveByEmployeeId(@PathVariable Long employeeId) throws ResourceNotFoundException {
	      
        if(!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found!");
        }
      
      return leaveRepository.findByEmployeeId(employeeId);
	}
    
	
	
	@PostMapping("/employees/{employeeId}/leave")
    public Leave addLeave(@PathVariable Long employeeId,
                            @Valid @RequestBody Leave leave) throws ResourceNotFoundException {
        return employeeRepository.findById(employeeId)
                .map(employee -> {
                    leave.setEmployee(employee);
                    return leaveRepository.save(leave);
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found!"));
    }
	
	// Update leave By leaveId along with employee Id
	@PutMapping("/employees/{employeeId}/leave/{leaveId}")
    public Leave updateLeave(@PathVariable Long employeeId,
                    @PathVariable Long leaveId,
                    @Valid @RequestBody Leave leaveUpdated) throws ResourceNotFoundException {
      
      if(!employeeRepository.existsById(employeeId)) {
        throw new ResourceNotFoundException("Employee not found!");
      }
      
        return leaveRepository.findById(leaveId)
                .map(leave -> {
                    leave.setdescription(leaveUpdated.getdescription());
                    leave.setDays(leaveUpdated.getDays());
                    return leaveRepository.save(leave);
                }).orElseThrow(() -> new ResourceNotFoundException("Leave not found!"));
    }
    
	// Delete leave By leaveId along with employee Id
    @DeleteMapping("/employees/{employeeId}/leave/{leaveId}")
    public String deleteLeave(@PathVariable Long employeeId,
                     @PathVariable Long leaveId) throws ResourceNotFoundException {
      
      if(!employeeRepository.existsById(employeeId)) {
        throw new ResourceNotFoundException("Employee not found!");
      }
      
        return leaveRepository.findById(leaveId)
                .map(assignment -> {
                    leaveRepository.delete(assignment);
                    return "Deleted Successfully!";
                }).orElseThrow(() -> new ResourceNotFoundException("Leave not found!"));
    }
	
}
