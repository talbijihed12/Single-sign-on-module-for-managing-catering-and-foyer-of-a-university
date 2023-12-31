import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { LoginService } from '../components/shared/services/login.service';

@Injectable({
  providedIn: 'root'
})
export class SignupGuard implements CanActivate {
  constructor(private loginService:LoginService,private router:Router){}
  canActivate(route: ActivatedRouteSnapshot,state: RouterStateSnapshot): boolean  {
    if(this.loginService.getIsLoggedIn()){
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }

}
