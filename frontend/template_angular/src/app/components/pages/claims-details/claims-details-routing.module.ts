import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClaimsDetailsComponent } from './claims-details.component';

const routes: Routes = [{ path: '', component: ClaimsDetailsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClaimsDetailsRoutingModule { }
