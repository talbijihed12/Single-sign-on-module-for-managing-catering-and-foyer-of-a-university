import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MenusDetailsComponent } from './menus-details.component';

const routes: Routes = [{ path: '', component: MenusDetailsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MenusDetailsRoutingModule { }
