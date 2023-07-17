import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RestaurantSubRoutingModule } from './restaurant-sub-routing.module';
import { RestaurantSubComponent } from './restaurant-sub/restaurant-sub.component';
import { ContentComponent } from './restaurant-sub/content/content.component';
import { SharedModule } from "../../shared/shared.module";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NiceSelectModule } from 'ng-nice-select';
import { NgxPaginationModule } from 'ngx-pagination';
import { RestaurantServiceService } from './restaurant-service.service';
import { AppComponent } from 'src/app/app.component';


@NgModule({
    declarations: [
        RestaurantSubComponent,
        ContentComponent
    ],
    imports: [
        CommonModule,
        RestaurantSubRoutingModule,
        SharedModule,
        NgbModule,
        FormsModule,
        ReactiveFormsModule,
        NiceSelectModule,
        NgxPaginationModule
    ],
    providers: [RestaurantServiceService], // ajoutez votre service à la liste des fournisseurs
    bootstrap: [AppComponent]
})
export class RestaurantSubModule { }
