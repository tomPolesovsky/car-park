import {Employee} from "./employee.model";

export interface ReservationSettings {
  id: string;
  employee: Employee;
  allowed: boolean;
  auto_approval: boolean;
}
