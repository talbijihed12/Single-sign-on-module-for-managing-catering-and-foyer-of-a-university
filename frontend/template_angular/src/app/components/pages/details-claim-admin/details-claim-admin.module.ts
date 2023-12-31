import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NiceSelectModule } from 'ng-nice-select';

import { CarsDetailsRoutingModule } from './details-claim-admin-routing.module';
import { CarsDetailsComponent } from './details-claim-admin.component';
import { SharedModule } from '../../shared/shared.module';
import { ContentComponent } from './content/content.component';


@NgModule({
  declarations: [
    CarsDetailsComponent,
    ContentComponent
  ],
  imports: [
    CommonModule,
    CarsDetailsRoutingModule,
    SharedModule,
    NgbModule,
    SlickCarouselModule,
    FormsModule,
    ReactiveFormsModule,
    NiceSelectModule,
  ]
})
export class CarsDetailsModule { }
