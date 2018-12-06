import {Employee} from "./employee.model";
import {Vehicle} from "./vehicle.model";
import {ReservationStatus} from "./reservation-status.enum";


export interface Reservation {
  id: string;
  from: Date;
  to: Date;
  employee: Employee;
  vehicle: Vehicle;
  status: ReservationStatus;
}
