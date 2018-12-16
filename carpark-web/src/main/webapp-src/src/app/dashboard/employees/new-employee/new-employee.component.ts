import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {EmployeesService} from "../../../shared/services/employees.service";
import {Employee} from "../../../shared/models/employee.model";
import {touchAllChildren} from "../../../shared/utils";

@Component({
  selector: 'app-new-employee',
  templateUrl: './new-employee.component.html',
  styleUrls: ['./new-employee.component.scss']
})
export class NewEmployeeComponent implements OnInit {

  warningText = 'This is required!';
  employeeId = '';
  editMode = false;
  employee: Employee;

  employeeForm = this.fb.group({
    id: '',
    first_name: ['', Validators.required],
    last_name: ['', Validators.required],
    username: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
    position: ['', Validators.required],
  });

  constructor(private readonly fb: FormBuilder,
              private readonly router: Router,
              private readonly route: ActivatedRoute,
              private readonly employeeService: EmployeesService) {
  }

  ngOnInit() {
    this.route.queryParams
      .subscribe(params => {
        this.employeeId = params.edit;
        if (this.employeeId) {
          this.editMode = true;
          this.employeeService.getEmployees()
            .subscribe(data => {
              this.employee = data.find(employee => String(employee.id) === String(this.employeeId));
              this.initEmployeeForm();
            });
        }
      });
  }

  initEmployeeForm(): void {
    this.editMode = true;
    this.employeeForm.setValue(this.employee);
  }

  editEmployee(): void {
    if (this.employeeForm.valid) {
      if (JSON.stringify(Object.values(this.employee)) !== JSON.stringify(Object.values(this.employeeForm.value))) {
        console.log(this.employeeForm.value);
        this.employeeService.updateEmployee(this.employeeForm.value)
          .subscribe(() => this.router.navigateByUrl('/dashboard/employees'));
      }
      this.router.navigateByUrl('/dashboard/employees');

    } else {
      touchAllChildren(this.employeeForm);
    }
  }

  createEmployee(): void {
    if (this.employeeForm.valid) {
      this.employeeService.createEmployee(this.employeeForm.value)
        .subscribe(() => this.router.navigateByUrl('/dashboard/employees'));
    } else {
      touchAllChildren(this.employeeForm);
    }
  }
}
