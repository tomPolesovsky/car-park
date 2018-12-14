import { NgModule } from '@angular/core';
import { MatDatepickerModule,
  MatNativeDateModule,
  MatFormFieldModule,
  MatInputModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatSelectModule} from '@angular/material/select';

@NgModule({
  imports: [
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatSelectModule,
  ],
  exports: [
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatSelectModule,
  ],
  providers: [ MatDatepickerModule ],
})

export class MaterialModule {}
