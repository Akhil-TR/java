import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListEmployeeComponent} from './list-employee/list-employee.component';
import { EmployeeDetailsComponent} from './employee-details/employee-details.component';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { LeaveDetailsComponent } from './leave-details/leave-details.component';
import { ListLeaveComponent } from './list-leave/list-leave.component';
import { CreateLeaveComponent } from './create-leave/create-leave.component';
import { UpdateLeaveComponent } from './update-leave/update-leave.component';



const routes: Routes = [
  {path: 'employees', component: ListEmployeeComponent},
  {path: 'employees/:id', component: EmployeeDetailsComponent},
  {path: 'add', component: CreateEmployeeComponent },
  {path: 'employees/update/:id', component: UpdateEmployeeComponent},
  {path: 'employees/leave/*', component: ListLeaveComponent},
  {path: 'employees/:id/leave', component: CreateLeaveComponent},
  {path: 'employees/:id/leave/:leaveId/update', component:UpdateLeaveComponent,},


  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents =[ ListEmployeeComponent, 
                                  EmployeeDetailsComponent,
                                  CreateEmployeeComponent, 
                                  UpdateEmployeeComponent,
                                  LeaveDetailsComponent, 
                                  ListLeaveComponent,
                                  CreateLeaveComponent,
                                  UpdateLeaveComponent,
                                ]
