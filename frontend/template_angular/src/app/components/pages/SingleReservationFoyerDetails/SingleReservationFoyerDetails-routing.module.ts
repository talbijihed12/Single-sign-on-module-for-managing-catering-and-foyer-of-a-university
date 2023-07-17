import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SingleReservationFoyerDetailsilsComponent } from './SingleReservationFoyerDetails.component';

const routes: Routes = [{ path: '', component: SingleReservationFoyerDetailsilsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SingleReservationFoyerDetailsRoutingModule { }
