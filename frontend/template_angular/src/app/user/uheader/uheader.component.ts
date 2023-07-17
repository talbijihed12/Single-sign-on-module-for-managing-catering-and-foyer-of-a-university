import { Component, Input, OnInit } from '@angular/core';
import { OAuthService, AuthConfig, NullValidationHandler } from 'angular-oauth2-oidc';
import { HelperService } from 'src/app/components/helper/helper.service';
import { UserService } from 'src/app/components/shared/services/user.service';

@Component({
  selector: 'app-uheader',
  templateUrl: './uheader.component.html',
  styleUrls: ['./uheader.component.css']
})
export class UHeaderComponent extends HelperService {
  userName!:string;
  isLoggedIn!:boolean;
  isAdmin!:boolean;
  @Input() isLogged!:boolean;
  @Input() isAdminn!:boolean;
  // token=this.oauthService.getAccessToken();
   //payload=this.token.split('.')[1];
   //payloadDecodedJson=atob(this.payload);
   //payloadDecoded= JSON.parse(this.payloadDecodedJson);
   //preferredUsername = this.payloadDecoded.preferred_username


  constructor(private oauthService:OAuthService,private userService:UserService) {
    super();
    this.configure();
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
        this.isLoggedIn=this.getIsLoggedIn();
        this.isAdmin=this.getIsAdmin();
        this.userName=this.getUsername();

       // this.preferredUsername;
       // this.username=this.oauthService.()[`preferred_username`];
      }
    });

  }
  login():void{

    this.oauthService.initImplicitFlowInternal();
  }
  public getIsLoggedIn():boolean{

    return (this.oauthService.hasValidAccessToken() && this.oauthService.hasValidAccessToken());
  }
  logout():void{
    this.oauthService.logOut();

  }
  public getIsAdmin():boolean{
    const token=this.oauthService.getAccessToken();
    const payload=token.split('.')[1];
    const payloadDecodedJson=atob(payload);
    const payloadDecoded= JSON.parse(payloadDecodedJson);
    const preferredUsername = payloadDecoded.preferred_username
    console.log(payloadDecoded);
    console.log(preferredUsername)
    return payloadDecoded.realm_access.roles.indexOf('ROLE_ADMIN','ROLE_ADMIN_FORUM','ROLE_ADMIN_FOYER','ROLE_ADMIN_RESTEAU') !==-1;

  }
  onDelete():void{
    this.userService.deleteMyAccount().subscribe(
      data=>{
        console.log(data);
        alert("User Deleted Successfully")

      },
      error =>console.log(error)
    )
  }
  public getUsername():string{
    const token=this.oauthService.getAccessToken();
    const payload=token.split('.')[1];
    const payloadDecodedJson=atob(payload);
    const payloadDecoded= JSON.parse(payloadDecodedJson);
    const preferredUsername = payloadDecoded.preferred_username
   return preferredUsername;
  }

}
