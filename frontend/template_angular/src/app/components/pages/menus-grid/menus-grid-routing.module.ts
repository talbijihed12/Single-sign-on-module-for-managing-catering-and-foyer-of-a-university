import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MenusGridComponent } from './menus-grid.component';

const routes: Routes = [{ path: '', component: MenusGridComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MenusGridRoutingModule { }
 