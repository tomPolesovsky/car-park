import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {EmployeesService} from "../../../api/services/employees.service";
import {Employee} from "../../../api/models/employee.model";

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
        console.log(data);
        this.employees = data;
      });
  }

}
