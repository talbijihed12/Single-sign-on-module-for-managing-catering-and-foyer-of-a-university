import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ContactRoutingModule } from './vote-routing.module';
import { VoteComponent } from './vote.component';
import { SharedModule } from '../../shared/shared.module';
import { VoteFormComponent } from './vote-form/vote-form.component';


@NgModule({
  declarations: [
    VoteComponent,
    VoteFormComponent
  ],
  imports: [
    CommonModule,
    ContactRoutingModule,
    SharedModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class VoteModule { }
