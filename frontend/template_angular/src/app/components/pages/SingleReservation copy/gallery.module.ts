import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { GalleryRoutingModule } from './gallery-routing.module';
import { GalleryComponent } from './gallery.component';
import { SharedModule } from '../../shared/shared.module';
import { ProductsComponent } from './content/content.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { SingleReservationFoyersidebarComponent } from '../../shared/SingleReservationFoyer-sidebar copy/SingleReservationFoyer-sidebar.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    GalleryComponent,
    ProductsComponent,
    SingleReservationFoyersidebarComponent
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
    ProductsComponent
  ]
})
export class GalleryModule { }
