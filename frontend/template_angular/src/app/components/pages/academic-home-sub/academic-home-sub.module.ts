import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AcademicHomeSubRoutingModule } from './academic-home-sub-routing.module';
import { AcademicHomeSubComponent } from './academic-home-sub/academic-home-sub.component';
import { ContentComponent } from './academic-home-sub/content/content.component';
import { SharedModule } from '../../shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NiceSelectModule } from 'ng-nice-select';
import { NgxPaginationModule } from 'ngx-pagination';
import { AcademicHomeSubService } from './academic-home-sub.service';
import { AppComponent } from 'src/app/app.component';


@NgModule({
  declarations: [
    AcademicHomeSubComponent,
    ContentComponent
  ],
  imports: [
    CommonModule,
    AcademicHomeSubRoutingModule,
    SharedModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    NiceSelectModule,
    NgxPaginationModule
  ],
  providers: [AcademicHomeSubService], // ajoutez votre service à la liste des fournisseurs
  bootstrap: [AppComponent]
})
export class AcademicHomeSubModule { }
