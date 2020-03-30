import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { Leave } from '../leave';
import { LeaveServiceService } from '../leave-service.service';
import { Router, ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-leave-details',
  templateUrl: './leave-details.component.html',
  styleUrls: ['./leave-details.component.css']
})
export class LeaveDetailsComponent implements OnInit {
  @Input() id: number;
  leaveDetails: Observable<Leave[]>;
  flag: boolean;
  message: string;
  output: Object;
  constructor(private route: ActivatedRoute, private leaveService: LeaveServiceService,
    private router: Router) {
   }

  ngOnInit(): void {
    this.flag = false;
    if (this.route.snapshot.params['id']){
        this.id = this.route.snapshot.params['id'];
        }
      // console.log(this.id)
      this.leaveService.getLeaveByEmployeeID(this.id).subscribe(
        data => {
          console.log(data);
          this.leaveDetails = data;
          var len = Object.keys(data).length;
          console.log(len)
            if (len==0){ 
                this.flag=true;
                this.message = "You did't applied for leave";
            }
      },
      error => console.log(error));
      console.log(this.message)
  }

  createLeave(){
    this.router.navigate(["employees"+`/${this.id}`+"/leave"]);

  }

  update(leaveId: number){
    this.router.navigate(["employees"+`/${this.id}`+"/leave"+`/${leaveId}`+"/update"]);
  }

  deleteLeave(leaveId: number){
    this.leaveService.deleteLeave(this.id, leaveId )
    .subscribe(
      data => {
        console.log(data);
        this.output=data;
        this.ngOnInit()
      },
      error => console.log(error));
  }

}
