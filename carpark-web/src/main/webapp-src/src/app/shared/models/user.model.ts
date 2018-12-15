import {Roles} from "./roles.enum";

export interface User {
  employeeId: number;
  firstName: string;
  lastName: string;
  role: Roles.APPROVER;
  token: string;
}
