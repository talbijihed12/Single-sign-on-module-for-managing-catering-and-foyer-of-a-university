import { NgModule ,CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NiceSelectModule } from 'ng-nice-select';
import { SharedModule } from '../../shared/shared.module';
import { ContentComponent } from './content/content.component';
import { NgxPaginationModule } from 'ngx-pagination';
import {ReservationRestaurantDetailsilsComponent } from './SingleReservationFoyerDetails.component';
import { ReservationRestaurantDetailsRoutingModule } from './SingleReservationFoyerDetails-routing.module';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    ReservationRestaurantDetailsilsComponent,
    ContentComponent
  ],
  imports: [
    RouterModule,
    CommonModule,
    SharedModule,
    FormsModule,
    NgbModule,
    NgxPaginationModule,
    ReservationRestaurantDetailsRoutingModule,
    SlickCarouselModule,
    ReactiveFormsModule,
    NiceSelectModule
  ]
})
export class ReservationRestaurantModule { }
