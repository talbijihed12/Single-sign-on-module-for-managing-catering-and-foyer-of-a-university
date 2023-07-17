import { NgModule ,CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NiceSelectModule } from 'ng-nice-select';

import { SingleReservationFoyerDetailsRoutingModule } from './SingleReservationFoyerDetails-routing.module';
import { SingleReservationFoyerDetailsilsComponent } from './SingleReservationFoyerDetails.component';
import { SharedModule } from '../../shared/shared.module';
import { ContentComponent } from './content/content.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    SingleReservationFoyerDetailsilsComponent,
    ContentComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    NgbModule,
    NgxPaginationModule,
    SingleReservationFoyerDetailsRoutingModule,
    SlickCarouselModule,
    ReactiveFormsModule,
    NiceSelectModule
  ]
})
export class SingleReservationFoyerModule { }
