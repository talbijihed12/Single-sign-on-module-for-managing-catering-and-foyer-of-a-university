import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AcademicHomeSubComponent } from './academic-home-sub/academic-home-sub.component';

const routes: Routes = [{ path: '', component: AcademicHomeSubComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AcademicHomeSubRoutingModule { }
