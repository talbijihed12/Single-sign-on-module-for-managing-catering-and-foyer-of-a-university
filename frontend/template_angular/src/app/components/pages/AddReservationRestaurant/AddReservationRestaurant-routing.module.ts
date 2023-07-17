import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddReservationRestaurantComponent } from './AddReservationRestaurant.component';

const routes: Routes = [{ path: '', component: AddReservationRestaurantComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AddReservationRestaurantRoutingModule { }
