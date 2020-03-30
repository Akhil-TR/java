import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private baseUrl = 'http://localhost:8080/api/';
  constructor(private http: HttpClient) { }

  getEmployeesList(): Observable<any> {
    // console.log(`${this.baseUrl}`+"employees");
    return this.http.get(`${this.baseUrl}`+"employees");
  }

  getEmployee(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}`+"employees"+`/${id}`);
  }

  createEmployee(employee: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`+"employees", employee);
  }

  updateEmployee(id: number,employee: Object): Observable<Object> {
    return this.http.put(`${this.baseUrl}`+"employees"+`/${id}`, employee);
  }

  deleteEmployee(id: number): Observable<Object> {
    return this.http.delete(`${this.baseUrl}`+"employees"+`/${id}`, { responseType: 'text' });
  }
}
