import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddSingleReservationFoyerComponent } from './contact.component';

const routes: Routes = [{ path: '', component: AddSingleReservationFoyerComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContactRoutingModule { }
