import {Moment} from "moment";

export interface FilterParams {
  page: number;
  pageSize: number;
  query: string;
  from: string | Moment;
  to: string | Moment;
}
