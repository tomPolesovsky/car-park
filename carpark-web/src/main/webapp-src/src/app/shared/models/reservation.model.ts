import {Employee} from "./employee.model";
import {Vehicle} from "./vehicle.model";
import {ReservationStatus} from "./reservation-status.enum";
import {Moment} from "moment";


export interface Reservation {
  id: string;
  from: string | Moment;
  to: string | Moment;
  employee: Employee;
  vehicle: Vehicle;
  status: ReservationStatus;
}
