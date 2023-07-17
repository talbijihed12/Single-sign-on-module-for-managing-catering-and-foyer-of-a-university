import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddReservationRestaurantRoutingModule } from './AddReservationRestaurant-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { ContactInfoComponent } from './contact-info/contact-info.component';
import { ContactFormComponent } from './contact-form/contact-form.component';
import { AddReservationRestaurantComponent } from './AddReservationRestaurant.component';


@NgModule({
  declarations: [
    ContactInfoComponent,
    ContactFormComponent,
    AddReservationRestaurantComponent
  ],
  imports: [
    AddReservationRestaurantRoutingModule,
    CommonModule,
    SharedModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AddReservationRestaurantModule { }
