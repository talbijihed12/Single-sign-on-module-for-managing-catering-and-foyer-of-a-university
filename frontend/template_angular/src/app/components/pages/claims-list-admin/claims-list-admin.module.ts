import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NiceSelectModule } from 'ng-nice-select';
import { NgxPaginationModule } from 'ngx-pagination';

import { SharedModule } from '../../shared/shared.module';
import { ContentComponent } from './content/content.component';
import { ClaimsListAdminComponent } from './claims-list-admin.component';
import { ClaimsListAdminRoutingModule } from './claims-list-admin-routing.module';
import { ConfirmationService, MessageService } from 'primeng/api';


@NgModule({
  declarations: [
    ClaimsListAdminComponent,
    ContentComponent,
  ],
  imports: [
    CommonModule,
    ClaimsListAdminRoutingModule,
    SharedModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    NiceSelectModule,
    NgxPaginationModule,
    
  ],
  providers: [
    ConfirmationService,
    MessageService],
})
export class ClaimsListAdminModule { }
