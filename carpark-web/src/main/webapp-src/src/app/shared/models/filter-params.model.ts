import {Moment} from "moment";

export interface FilterParams {
  page: number;
  page_size: number;
  query: string;
  from: string | Moment;
  to: string | Moment;
}
