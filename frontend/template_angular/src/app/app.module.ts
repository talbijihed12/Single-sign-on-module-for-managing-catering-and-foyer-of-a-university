import { OAuthModule } from 'angular-oauth2-oidc';
import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddClaimComponent } from './claims/add-claim/add-claim.component';
import { SignupComponent } from './auth/signup/signup.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {  BreadcrumbModule } from 'angular-crumbs';
import { ToastrModule } from 'ngx-toastr';
import { ListeComponent } from './user/liste/liste.component';
import { DetailsComponent } from './user/details/details.component';
import { UpdateComponent } from './user/update/update.component';
import { CreateComponent } from './user/create/create.component';
import { UpdateByAdminComponent } from './user/update-by-admin/update-by-admin.component';
import { FooterComponent } from './components/shared/footer/footer.component';
import { HeaderComponent } from './components/shared/header/header.component';
import { UHeaderComponent } from './user/uheader/uheader.component';
import { UFooterComponent } from './user/ufooter/ufooter.component';
import { RouterModule } from '@angular/router';
import { PipePipe } from './user/pipes/pipe.pipe';
import { NgxPaginationModule } from 'ngx-pagination';
import { SidebarComponent } from './user/sidebar/sidebar.component';
import { NgxConfirmBoxModule } from 'ngx-confirm-box';
import { NgChartsConfiguration, NgChartsModule } from 'ng2-charts';
import { SnotifyService, ToastDefaults } from 'ng-snotify';
import { ChartModule } from 'primeng/chart';
import { MessageService } from 'primeng/api';


@NgModule({
  declarations: [
    AppComponent,
    AddClaimComponent,
    SignupComponent,
    ListeComponent,
    DetailsComponent,
    UpdateComponent,
    CreateComponent,
    UpdateByAdminComponent,
    UHeaderComponent,
    UFooterComponent,
    PipePipe,
    SidebarComponent,







  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    BreadcrumbModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    NgChartsModule,
    ChartModule,
    ToastrModule.forRoot(),
    OAuthModule.forRoot({
      resourceServer: {
          allowedUrls: ['http://localhost:8093'],
          sendAccessToken: true
      }
  })

  ],

  providers: [{ provide: 'SnotifyToastConfig', useValue: ToastDefaults },
  SnotifyService,MessageService],

  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
