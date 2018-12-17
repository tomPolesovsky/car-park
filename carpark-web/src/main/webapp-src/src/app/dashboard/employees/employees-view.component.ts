import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {EmployeesService} from "../../shared/services/employees.service";
import {Employee} from "../../shared/models/employee.model";
import {Observable} from "rxjs";
import {Roles} from "../../shared/models/roles.enum";

@Component({
  selector: 'app-employees',
  templateUrl: './employees-view.component.html',
  styleUrls: ['./employees-view.component.scss']
})
export class EmployeesViewComponent implements OnInit {

  employees$: Observable<Employee[]> = this.employeesService.getEmployees();
  userRoles = Roles;

  currentUserRole = JSON.parse(localStorage.getItem('currentUser')).role;

  constructor(private readonly router: Router,
              private readonly employeesService: EmployeesService) {
  }

  ngOnInit(): void {
    this.employees$
      .subscribe(() => {});
  }

  addEmployee(): void {
    this.router.navigateByUrl('/dashboard/new-employee');
  }

  editEmployee(employee: Employee): void {
    this.router.navigate(['/dashboard/new-employee'], { queryParams: { edit: employee.id } });
  }

  deleteEmployee(employee: Employee): void {
    this.employeesService.deleteEmployee(employee)
      .subscribe(() => {
        this.employees$ = this.employeesService.getEmployees();
      },
        error => {
          alert('You don\'t have permission to remove this employee!');
        });
  }
}
