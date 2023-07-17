import { NgModule } from '@angular/core';
import { ProductComponent } from './content/content.component';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { GalleryRoutingModule } from './gallery-routing.module';
import { GalleryComponent } from './gallery.component';
import { SharedModule } from '../../shared/shared.module';
import { NgxPaginationModule } from 'ngx-pagination';
import { FormsModule } from '@angular/forms';
import { ReservationRestaurantidebarComponent } from '../../shared/ReservationRestaurant-sidebar/ReservationRestaurant-sidebar.component';



@NgModule({
  declarations: [
    GalleryComponent,
    ReservationRestaurantidebarComponent,
    ProductComponent
  ],
  imports: [
    CommonModule,
    GalleryRoutingModule,
    SharedModule,
    FormsModule,
    NgbModule,
    NgxPaginationModule
  ]

})
export class  ReservationRestaurantModule { }
