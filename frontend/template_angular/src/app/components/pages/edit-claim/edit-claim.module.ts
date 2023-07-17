import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NiceSelectModule } from 'ng-nice-select';
import { NgxPaginationModule } from 'ngx-pagination';

import { CarsGridRoutingModule } from './edit-claim-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { ContentComponent } from './content/content.component';
import { EditClaimComponent } from './edit-claim.component';
import { SnotifyService, ToastDefaults } from 'ng-snotify';


@NgModule({
  declarations: [
    EditClaimComponent,
    ContentComponent
  ],
  imports: [
    CommonModule,
    CarsGridRoutingModule,
    SharedModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    NiceSelectModule,
    NgxPaginationModule,

  ],
  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults },
  SnotifyService]
})
export class CarsGridModule { }
