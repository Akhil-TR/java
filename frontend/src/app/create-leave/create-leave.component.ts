import { Component, OnInit } from '@angular/core';
import { Leave } from '../leave';
import { LeaveServiceService } from '../leave-service.service';
import { Router, ActivatedRoute } from '@angular/router';
import { LeaveDetailsComponent } from '../leave-details/leave-details.component';

@Component({
  selector: 'app-create-leave',
  templateUrl: './create-leave.component.html',
  styleUrls: ['./create-leave.component.css']
})
export class CreateLeaveComponent implements OnInit {
  leave: Leave = new Leave;
  employeeId: number;
  constructor(private route: ActivatedRoute, private leaveService: LeaveServiceService,
    private router: Router) { }

  ngOnInit(): void {
    this.employeeId = this.route.snapshot.params['id'];
    
  }

  save() {
    this.leaveService.createLeave(this.employeeId, this.leave)
      .subscribe(data => console.log(data), error => console.log(error));
      this.leave = new Leave();
      this.ngOnInit();
  }
  onSubmit() {
    this.save();  
    this.router.navigate(['/employees/'+this.employeeId]);

  }


}
