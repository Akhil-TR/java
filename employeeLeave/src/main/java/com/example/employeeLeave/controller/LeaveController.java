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
import com.example.employeeLeave.model.Leave;
import com.example.employeeLeave.service.LeaveService;


@RestController
@RequestMapping("/api")
public class LeaveController {
	@Autowired
	private LeaveService leaveService;
	
	
	// Get all leave 
	@GetMapping("/leave")
	public List<Leave> getAll(){
		return leaveService.getAllLeave();
	  }
	
	// Get leave By Employee Id
	@GetMapping("/employees/{employeeId}/leave")
	public List<Leave> getLeaveByEmployeeId(@PathVariable Long employeeId) throws ResourceNotFoundException {
		return leaveService.getLeaveByEmployeeId(employeeId);
	  }
   
	// Post leave
	@PostMapping("/employees/{employeeId}/leave")
    public Leave addLeave(@PathVariable Long employeeId,
                            @Valid @RequestBody Leave leave) throws ResourceNotFoundException {
        return leaveService.addLeaveByEmployeId(employeeId, leave);
	  }
	
	// Update leave By leaveId along with employee Id
	@PutMapping("/employees/{employeeId}/leave/{leaveId}")
    public Leave updateLeave(@PathVariable Long employeeId,
                    		 @PathVariable Long leaveId,
                    		 @Valid @RequestBody Leave leaveUpdated) throws ResourceNotFoundException {
		return leaveService.updateLeave(employeeId, leaveId, leaveUpdated);
 
    }
    
	// Delete leave By leaveId along with employee Id
    @DeleteMapping("/employees/{employeeId}/leave/{leaveId}")
    public String deleteLeave(@PathVariable Long employeeId,
                     		  @PathVariable Long leaveId) throws ResourceNotFoundException {
    	return leaveService.deleteLeave(employeeId, leaveId);
    }
	
}
