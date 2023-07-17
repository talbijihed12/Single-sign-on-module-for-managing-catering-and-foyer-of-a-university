import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { NiceSelectModule } from 'ng-nice-select';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { SharedModule } from '../../shared/shared.module';
import { AboutUsComponent } from './statistics-claim/about-us.component';
import { NgChartsModule } from 'ng2-charts';
import {ChartModule} from 'primeng/chart';





@NgModule({
  declarations: [
    HomeComponent,
    AboutUsComponent,

  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    SharedModule,
    NgbModule,
    NgChartsModule,
    SlickCarouselModule,
    NiceSelectModule,
    FormsModule,
    ReactiveFormsModule,
    ChartModule
  ]
})
export class HomeModule { }
