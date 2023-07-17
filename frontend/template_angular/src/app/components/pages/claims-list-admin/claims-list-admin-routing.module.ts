import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClaimsListAdminComponent } from './claims-list-admin.component';
//import { ClaimsListAdminComponent } from './claims-list-admin.component';

const routes: Routes = [{ path: '', component: ClaimsListAdminComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClaimsListAdminRoutingModule { }
