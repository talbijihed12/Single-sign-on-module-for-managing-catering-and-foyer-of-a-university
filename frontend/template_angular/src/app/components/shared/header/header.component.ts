import { Component, Input } from '@angular/core';
import { HelperService } from '../../helper/helper.service';
import { AuthConfig, NullValidationHandler, OAuthService } from 'angular-oauth2-oidc';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent extends HelperService {
  username!:string;
  isLoggedIn!:boolean;
  isAdmin!:boolean;
  @Input() isLogged!:boolean;
  @Input() isAdminn!:boolean;
  // token=this.oauthService.getAccessToken();
   //payload=this.token.split('.')[1];
   //payloadDecodedJson=atob(this.payload);
   //payloadDecoded= JSON.parse(this.payloadDecodedJson);
   //preferredUsername = this.payloadDecoded.preferred_username


  constructor(private oauthService:OAuthService,private userService:UserService,private router:Router) {
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
        this.username=this.getUsername();

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
  onDelete():void{
    if (confirm('this will delete your profile?')) {
      if (confirm('Are you sure you want to delete this profile?')){
      this.userService.deleteMyAccount().subscribe(
        data => {
          console.log(data);
          alert("User Deleted Successfully");
          this.router.navigate(['']); // Redirect to home page
          this.logout(); // Call the logout function
        },
        error => console.log(error)
      );
    }
  }
  this.logout();
  this.router.navigate(['']);
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
  public getUsername():string{
    const token=this.oauthService.getAccessToken();
    const payload=token.split('.')[1];
    const payloadDecodedJson=atob(payload);
    const payloadDecoded= JSON.parse(payloadDecodedJson);
    const preferredUsername = payloadDecoded.preferred_username
   return preferredUsername;
  }

}
