import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditClaimComponent } from './edit-claim.component';

const routes: Routes = [{ path: '', component: EditClaimComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarsGridRoutingModule { }
