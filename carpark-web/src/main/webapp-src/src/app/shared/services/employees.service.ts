import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Employee} from "../models/employee.model";
import {requestPath} from "../utils";

@Injectable()
export class EmployeesService {

  private employeesUrl = '/employees';

  constructor(private readonly http: HttpClient) {
  }

  getEmployees() {
    return this.http.get<Employee[]>(`${requestPath}${this.employeesUrl}`);
  }

  deleteEmployee(employee: Employee) {
     return this.http.delete(`${requestPath}${this.employeesUrl}/${employee.id}`);
  }

  createEmployee(employee: Employee) {
    return this.http.post<Employee>(`${requestPath}${this.employeesUrl}`, employee);
  }

  updateEmployee(employee: Employee) {
    console.log('update', JSON.stringify(employee));
    return this.http.put<Employee>(`${requestPath}${this.employeesUrl}`, employee);
  }
}
