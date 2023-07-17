import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgxPaginationModule } from 'ngx-pagination';

import { MealGridRoutingModule } from './meal-grid-routing.module';
import { MealGridComponent } from './meal-grid.component';
import { SharedModule } from '../../shared/shared.module';
import { ContentComponent } from './content/content.component';
import { SlickCarouselModule } from 'ngx-slick-carousel';


@NgModule({
  declarations: [
    MealGridComponent,
    ContentComponent
  ],
  imports: [
    CommonModule,
    MealGridRoutingModule,
    SharedModule,
    NgbModule,
    NgxPaginationModule,
    SlickCarouselModule,


  ]
})
export class MealGridModule { }
