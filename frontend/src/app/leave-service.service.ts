import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LeaveServiceService {
  
  private baseUrl = 'http://localhost:8080/api/';


 

  constructor(private http: HttpClient) { }
  getLeaveList(): Observable<any> {
    // console.log(`${this.baseUrl}`+"employees");
    return this.http.get(`${this.baseUrl}`+"leave");
  }

  getLeaveByEmployeeID(employeeId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}`+"employees"+`/${employeeId}`+"/leave");
  }

  getLeave(leaveId: number):Observable<any> {
    return this.http.get(`${this.baseUrl}`+"leave"+`/${leaveId}`);
  }

  createLeave(employeeId: number,leave: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`+"employees"+`/${employeeId}`+"/leave", leave);
  }

  updateLeaveById(employeeId: number, leaveId: number, leave: Object){
    return this.http.put(`${this.baseUrl}`+"employees"+`/${employeeId}`+'/leave'+`/${leaveId}`, leave);

  }

  deleteLeave(employeeId: number, leaveId: number): Observable<Object> {
    return this.http.delete(`${this.baseUrl}`+"employees"+`/${employeeId}`+'/leave'+`/${leaveId}`, { responseType: 'text' });
  }
}
 
 