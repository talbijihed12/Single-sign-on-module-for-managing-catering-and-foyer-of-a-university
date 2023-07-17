import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReservationRestaurantDetailsilsComponent } from './SingleReservationFoyerDetails.component';

const routes: Routes = [{ path: '', component: ReservationRestaurantDetailsilsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReservationRestaurantDetailsRoutingModule { }
