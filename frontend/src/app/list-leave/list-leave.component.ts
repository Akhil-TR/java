import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Leave } from '../leave';
import { LeaveServiceService } from '../leave-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-leave',
  templateUrl: './list-leave.component.html',
  styleUrls: ['./list-leave.component.css']
})
export class ListLeaveComponent implements OnInit {
  leave: Observable<Leave[]>;
  employeeId: number;

  constructor(private leaveService: LeaveServiceService,
    private router: Router) { }

  ngOnInit(): void {
    this.leave = this.leaveService.getLeaveList();
    console.log(this.leave);
    console.log(typeof this.leave)

  }
  onClick(){
    this.getLeaveOfEmployee()
  }

  getLeaveOfEmployee(){
    if (this.employeeId==null){
        this.ngOnInit()
    }
    else{
      this.leave = this.leaveService.getLeaveByEmployeeID(this.employeeId);  
    }
  } 


}
