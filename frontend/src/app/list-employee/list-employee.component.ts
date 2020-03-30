import { Component, OnInit } from '@angular/core';
import { EmployeeDetailsComponent } from '../employee-details/employee-details.component';
import { Observable } from 'rxjs';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';
import { Router } from '@angular/router';
// import {observableToBeFn} from 'rxjs/internal/testing/TestScheduler';

@Component({
  selector: 'app-list-employee',
  templateUrl: './list-employee.component.html',
  styleUrls: ['./list-employee.component.css']
})
export class ListEmployeeComponent implements OnInit {
  static ngOnInit() {
    throw new Error("Method not implemented.");
  }
  output: any;
  employees: Observable<Employee[]>;

  constructor(private employeeService: EmployeeService,
              private router: Router) {}

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData() {
   this.employees = this.employeeService.getEmployeesList()
   
  }

  employeeDetails(id: number){
    this.router.navigate(['employees', id]);
  }

  deleteEmployee(id: number) {
    this.employeeService.deleteEmployee(id)
      .subscribe(
        data => {
          console.log(data);
          this.output=data;
          this.reloadData();
        },
        error => console.log(error));
  }
  
  
}
