import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { GalleryRoutingModule } from './gallery-routing.module';
import { GalleryComponent } from './gallery.component';
import { SharedModule } from '../../shared/shared.module';

import { NgxPaginationModule } from 'ngx-pagination';

import { FormsModule } from '@angular/forms';
import { ReservationFoyeridebarComponent } from '../../shared/ReservationFoyer-sidebar copy/SingleReservationFoyer-sidebar.component';
import { ProductComponent } from './content/ProductComponent';


@NgModule({
  declarations: [
    GalleryComponent,
    ReservationFoyeridebarComponent,
    ProductComponent
  ],
  imports: [
    CommonModule,
    GalleryRoutingModule,
    SharedModule,
    FormsModule,
    NgbModule,
    NgxPaginationModule
  ],
  providers: [
    ProductComponent
  ]

})
export class ReservationFoyerModule { }
