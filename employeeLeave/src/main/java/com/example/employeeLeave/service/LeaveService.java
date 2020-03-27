package com.example.employeeLeave.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employeeLeave.exception.ResourceNotFoundException;
import com.example.employeeLeave.jpa.EmployeeRepository;
import com.example.employeeLeave.jpa.LeaveRepository;
import com.example.employeeLeave.model.Leave;

@Service
public class LeaveService {
	@Autowired
	LeaveRepository leaveRepository;
	
	@Autowired 
	EmployeeRepository employeeRepository;
	
	// Get all
	@Transactional
	public List<Leave> getAllLeave() {
		return leaveRepository.findAll();
	}
	
	// Get by Id
	@Transactional
	public List<Leave> getLeaveByEmployeeId(Long employeeId) throws ResourceNotFoundException {
		if(!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found!");
        	}
		return leaveRepository.findByEmployeeId(employeeId);
	}
	
	
	// Post 
	@Transactional
	public Leave addLeaveByEmployeId(Long employeeId, @Valid Leave leave) throws ResourceNotFoundException {
		return employeeRepository.findById(employeeId).map(employee -> {
                    leave.setEmployee(employee);
                    return leaveRepository.save(leave);
                	}).orElseThrow(() -> new ResourceNotFoundException("Employee not found!"));	
	}
	
	// Update
	@Transactional
	public Leave updateLeave(Long employeeId, Long leaveId, @Valid Leave leaveUpdated) throws ResourceNotFoundException {
		if(!employeeRepository.existsById(employeeId)) {
	        throw new ResourceNotFoundException("Employee not found!");
	      }
	    
		return leaveRepository.findById(leaveId).map(leave -> {
                    leave.setDescription(leaveUpdated.getDescription());
                    leave.setDays(leaveUpdated.getDays());
                    leave.setFromDate(leaveUpdated.getFromDate());
                    leave.setToDate(leaveUpdated.getToDate());
                    return leaveRepository.save(leave);
	                }).orElseThrow(() -> new ResourceNotFoundException("Leave not found!"));
	}

	// Delete
	@Transactional
	public String deleteLeave(Long employeeId, Long leaveId) throws ResourceNotFoundException {
		if(!employeeRepository.existsById(employeeId)) {
	        throw new ResourceNotFoundException("Employee not found!");
	      }
	      
	    return leaveRepository.findById(leaveId).map(assignment -> {
                    leaveRepository.delete(assignment);
                    return "Deleted Successfully!";
	                }).orElseThrow(() -> new ResourceNotFoundException("Leave not found!"));
	}

}
