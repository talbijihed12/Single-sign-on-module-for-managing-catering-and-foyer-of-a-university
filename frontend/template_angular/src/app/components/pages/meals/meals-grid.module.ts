import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NiceSelectModule } from 'ng-nice-select';
import { NgxPaginationModule } from 'ngx-pagination';

import { MealsGridRoutingModule } from './meals-grid-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { ContentComponent } from './content/content.component';
import { MealsGridComponent } from './meals-grid.component';


@NgModule({
  declarations: [
    MealsGridComponent,
    ContentComponent
  ],
  imports: [
    CommonModule,
    MealsGridRoutingModule,
    SharedModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    NiceSelectModule,
    NgxPaginationModule
  ]
})
export class MealsGridModule { }
