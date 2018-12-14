import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Employee} from "../../shared/models/employee.model";
import {EmployeesService} from "../../shared/services/employees.service";


@Component({
  selector: 'app-employees',
  templateUrl: './employees-view.component.html',
  styleUrls: ['./employees-view.component.scss']
})
export class EmployeesViewComponent implements OnInit {

  employees: Employee[] = [];

  constructor(private readonly router: Router,
              private readonly employeesService: EmployeesService) {
  }

  ngOnInit(): void {
    this.employeesService.getEmployees()
      .subscribe(data => {
        this.employees = data;
      });
  }

}
