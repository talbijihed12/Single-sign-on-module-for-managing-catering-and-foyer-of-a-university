import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReservationFoyerDetailsilsComponent } from './SingleReservationFoyerDetails.component';

const routes: Routes = [{ path: '', component: ReservationFoyerDetailsilsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReservationFoyerDetailsRoutingModule { }
