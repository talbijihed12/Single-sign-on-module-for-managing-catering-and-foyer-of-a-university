import { NgModule } from "@angular/core";
import { ListeComponent } from "./liste.component";
import { CommonModule } from "@angular/common";
import { SharedModule } from "src/app/components/shared/shared.module";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NiceSelectModule } from "ng-nice-select";
import { NgxPaginationModule } from "ngx-pagination";
import { HeaderComponent } from "src/app/components/shared/header/header.component";
import { FooterComponent } from "src/app/components/shared/footer/footer.component";
import { UHeaderComponent } from "../uheader/uheader.component";

@NgModule({
  declarations: [
    

  ],
  imports: [
    CommonModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    NiceSelectModule,
    NgxPaginationModule,

  ]
})
export class ListeModule{}
