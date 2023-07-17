import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { BreadcrumbService, Breadcrumb } from 'angular-crumbs';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { AuthConfig, NullValidationHandler, OAuthModule, OAuthService } from 'angular-oauth2-oidc';
import { LoginService } from './components/shared/services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],

  providers: [
    Location, {
      provide: LocationStrategy,
      useClass: PathLocationStrategy
    }
  ]
})
export class AppComponent implements OnInit {
  isLoggedIn!:boolean;
  isAdmin!:boolean;
  username!:string;
  //token=this.oauthService.getAccessToken();
  // payload=this.token.split('.')[1];
   //payloadDecodedJson=atob(this.payload);
  // payloadDecoded= JSON.parse(this.payloadDecodedJson);
   //preferredUsername = this.payloadDecoded.preferred_username
  constructor(
    private loginService:LoginService,
    private titleService: Title,
    private breadcrumbService: BreadcrumbService,
    private oauthService:OAuthService) {
  }
  authFlowConfig: AuthConfig = {
    issuer: 'http://localhost:8180/auth/realms/SSO-Acedmic-Restauration',
    redirectUri: window.location.origin,
    clientId: 'angular-client',
    responseType: 'code',
    scope: 'openid profile email offline_access',

    showDebugInformation: true,
  };
  configure():void{
    this.oauthService.configure(this.authFlowConfig);
    this.oauthService.tokenValidationHandler=new NullValidationHandler;
    this.oauthService.setupAutomaticSilentRefresh();
    this.oauthService.loadDiscoveryDocument().then(()=>this.oauthService.tryLogin())
    .then (()=>{
      if(this.oauthService.getIdentityClaims()){
        this.isLoggedIn=this.loginService.getIsLoggedIn();
        this.isAdmin=this.loginService.getIsAdmin();
        this.username=this.loginService.getUsername();

       // this.preferredUsername;
       // this.username=this.oauthService.()[`preferred_username`];
      }
    });
  }


  ngOnInit(): void {
    this.breadcrumbService.breadcrumbChanged.subscribe(crumbs => {
      this.titleService.setTitle(this.createTitle(crumbs));
    });
  }
  onActivate(_event:any){
    window.scroll(0,0);
  }
  private createTitle(routesCollection: Breadcrumb[]) {
    const title = "Toor - Travel Booking | Angular Template";
    const titles = routesCollection.filter((route) => route.displayName);

    if (!titles.length) { return title; }

    const routeTitle = this.titlesToString(titles);
    return `${routeTitle}${title}`;
  }

  private titlesToString(titles: any[]) {
    return titles.reduce((prev, curr) => {
      return `${curr.displayName} | `;
    }, '');
  }


}
