import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NiceSelectModule } from 'ng-nice-select';

import { ClaimsDetailsRoutingModule } from './claims-details-routing.module';
import { ClaimsDetailsComponent } from './claims-details.component';
import { SharedModule } from '../../shared/shared.module';
import { ContentComponent } from './content/content.component';


@NgModule({
  declarations: [
    ClaimsDetailsComponent,
    ContentComponent
  ],
  imports: [
    CommonModule,
    ClaimsDetailsRoutingModule,
    SharedModule,
    NgbModule,
    SlickCarouselModule,
    FormsModule,
    ReactiveFormsModule,
    NiceSelectModule
  ]
})
export class ClaimsDetailsModule { }
