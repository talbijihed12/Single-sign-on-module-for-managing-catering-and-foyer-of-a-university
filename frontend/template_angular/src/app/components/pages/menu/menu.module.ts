import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { NiceSelectModule } from 'ng-nice-select';

import { HomeRoutingModule } from './menu-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { MenusComponent } from './menus/menus.component';
import { MenuComponent } from './menu.component';

@NgModule({
  declarations: [
    MenuComponent,
    MenusComponent,


  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    SharedModule,
    NgbModule,
    SlickCarouselModule,
    NiceSelectModule,
    FormsModule,
    ReactiveFormsModule,
    


  ]
})
export class MenuModule { }
