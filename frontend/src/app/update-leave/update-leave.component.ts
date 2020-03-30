import { Component, OnInit } from '@angular/core';
import { Leave } from '../leave';
import { ActivatedRoute, Router } from '@angular/router';
import { LeaveServiceService } from '../leave-service.service';

@Component({
  selector: 'app-update-leave',
  templateUrl: './update-leave.component.html',
  styleUrls: ['./update-leave.component.css']
})
export class UpdateLeaveComponent implements OnInit {
  employeeId: number;
  leaveId: number;
  leave: Leave;
  // submitted = false
  constructor(private route: ActivatedRoute,private router: Router,
    private leaveService: LeaveServiceService) { }

  ngOnInit(): void {
    this.leave = new Leave();
    this.employeeId = this.route.snapshot.params['id'];
    this.leaveId = this.route.snapshot.params['leaveId'];
    this.leaveService.getLeave(this.leaveId)
    .subscribe(data => {
      console.log(data)
      this.leave = data;
    }, error => console.log(error));
  }

  updateLeave() {
    console.log(this.employeeId, this.leaveId, this.leave)
    this.leaveService.updateLeaveById(this.employeeId, this.leaveId, this.leave)
      .subscribe(data => console.log(data), error => console.log(error));
    this.leave = new Leave();
    console.log("works");
  }
 
  onSubmit(){
    this.updateLeave()
    this.router.navigate(['/employees/'+this.employeeId]);
  }
  


}
