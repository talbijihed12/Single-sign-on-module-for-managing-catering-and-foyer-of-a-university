import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MealsGridComponent } from './meals-grid.component';

const routes: Routes = [{ path: '', component: MealsGridComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MealsGridRoutingModule { }
