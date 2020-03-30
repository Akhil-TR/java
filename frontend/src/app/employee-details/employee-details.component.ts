import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../employee.service';
import { ListEmployeeComponent } from '../list-employee/list-employee.component';
import { Leave } from '../leave'
import { EmployeeLeave } from '../employeeLeave';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {
  public isViewable: boolean;
  id: number;
  employee: Employee;
  leave: Leave; //
  emps: EmployeeLeave[] = [];
  constructor(private route: ActivatedRoute,private router: Router,
    private employeeService: EmployeeService) { }

    ngOnInit() {
      this.isViewable = false;
      this.employee = new Employee();
      this.leave = new Leave();
      this.id = this.route.snapshot.params['id'];
      this.employeeService.getEmployee(this.id).subscribe(data => {
          console.log(data)
          this.employee = data;
          // console.log(typeof this.leave);
          this.emps.push(this.employee.leave);
          console.log(this.emps)
        }, error => console.log(error));
    }

    employeeUpdate(id: number){
      this.router.navigate(['employees/update', id]);

    }
  
    backEmployeeList(){
      this.router.navigate(['employees']);
    }

    toggle(){ 
      this.isViewable = !this.isViewable; }

  }

