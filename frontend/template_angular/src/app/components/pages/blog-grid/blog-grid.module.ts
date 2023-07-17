import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgxPaginationModule } from 'ngx-pagination';

import { BlogGridRoutingModule } from './blog-grid-routing.module';
import { BlogGridComponent } from './blog-grid.component';
import { SharedModule } from '../../shared/shared.module';
import { ContentComponent } from './content/content.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    BlogGridComponent,
    ContentComponent
  ],
  imports: [
    FormsModule,
    CommonModule,
    BlogGridRoutingModule,
    SharedModule,
    NgbModule,
    NgxPaginationModule
  ]
})
export class BlogGridModule { }
