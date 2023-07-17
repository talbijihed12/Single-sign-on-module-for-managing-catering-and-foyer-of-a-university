import { Injectable } from '@angular/core';
import { Claim } from '../../models/Claim';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";
import { LoginService } from './login.service';


@Injectable({
  providedIn: 'root'
})
export class ClaimService {
  private apiServerUrl = environment.apiBaseUrl4;

  constructor(private http: HttpClient,private loginService:LoginService){}
  public addReclamation(claim : Claim): Observable<any> {
    return this.http.post<any>(`${this.apiServerUrl}/addClaim`,claim,{headers:{
      'Authorization': `Bearer ${this.loginService.getTOKEN()}`
    }});
  }
  public getReclamations(): Observable<Claim[]> {
    return this.http.get<Claim[]>(`${this.apiServerUrl}/findAllByStatusNotAnnuler`);
}
public getAllClaimsByAdmin(): Observable<Claim[]> {
  return this.http.get<Claim[]>(`${this.apiServerUrl}/allClaims`);
}
public getClaimsByType(type:any): Observable<Claim[]> {
  return this.http.get<Claim[]>(`${this.apiServerUrl}/findClaimsByTypeReclamation/${type}`);
}

public getById(id:any): Observable<Claim> {
  console.log('ID', id)
  return this.http.get<Claim>(`${this.apiServerUrl}/findClaim/${id}`);
}

public modifier(claim : Claim): Observable<any> {
  return this.http.put<any>(`${this.apiServerUrl}/modifier`,claim);
}
public changeStatus(claim : Claim): Observable<any> {
  return this.http.put<any>(`${this.apiServerUrl}/changeStatus/`,claim);
}


public archiver(claim:Claim): Observable<any> {
  return this.http.put<any>(`${this.apiServerUrl}/archive`,claim);
}

public resolve(claim : Claim): Observable<any> {
  return this.http.put<any>(`${this.apiServerUrl}/resoudre`,claim);
}
public retourner(claim : Claim): Observable<any> {
  return this.http.put<any>(`${this.apiServerUrl}/retour`,claim);
}









}
