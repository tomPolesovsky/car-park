import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Employee} from "../models/employee.model";

const httpOptions = {
  headers: new HttpHeaders(({'Content-Type': 'application/json'}))
};

@Injectable()
export class EmployeesService {

  constructor(private readonly http: HttpClient) {
  }

  private employeesUrl = '/employees';

  getEmployees() {
    return this.http.get<Employee[]>(this.employeesUrl);
  }

  deleteEmployee(employee: Employee) {
     return this.http.delete(this.employeesUrl + '/' + employee.id);
  }

  createEmployee(employee: Employee) {
    return this.http.post<Employee>(this.employeesUrl, employee);
  }

}
