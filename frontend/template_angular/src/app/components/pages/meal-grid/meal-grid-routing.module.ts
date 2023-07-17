import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MealGridComponent } from './meal-grid.component';

const routes: Routes = [{ path: '', component: MealGridComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MealGridRoutingModule { }
