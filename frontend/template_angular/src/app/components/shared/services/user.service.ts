import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from '../../models/User';
import { Observable } from 'rxjs';
import { SignupRequestPayload } from 'src/app/auth/signup/signupRequestPayload';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiServiceUrl=environment.apiBaseUrl;
  httpClient: any;
  httpOption={headers:new HttpHeaders({'Content-type':'application/json'})}

  constructor(private http:HttpClient,private loginService:LoginService) { }
  signup(signupRequestPayload: SignupRequestPayload): Observable<any> {
    console.log(signupRequestPayload)
    return this.http.post<any>(`${this.apiServiceUrl}/keycloakUser/signup`, signupRequestPayload);
  }
  public list():Observable<User[]>{
    return this.http.get<User[]>(`${this.apiServiceUrl}/keycloakUser/admin/findAll`,this.httpOption)
  }
  public detail(userName:string):Observable<User>{
    return this.http.get<User>(`${this.apiServiceUrl}/keycloakUser/admin/details/${userName}`,this.httpOption)
  }
  public create(user:User):Observable<any>{
    return this.http.post<any>(`${this.apiServiceUrl}/keycloakUser/addUserByAdmin`,user,this.httpOption)
  }
  public update(id:number,user:User):Observable<any>{
    return this.http.put<any>(`${this.apiServiceUrl}/keycloakUser/admin/UpdateUserByAdmin/${id}`,user,this.httpOption)
  }
  public delete(id:number):Observable<any>{
    return this.http.delete<any>(`${this.apiServiceUrl}/keycloakUser/deleteAccount/${id}`,this.httpOption)
  }
  public deleteMyAccount():Observable<any>{
    return this.http.delete<any>(`${this.apiServiceUrl}/keycloakUser/deleteMyAccount`, {
      headers: {
        'Authorization': `Bearer ${this.loginService.getUserId()}`,
        'Content-type':'application/json'

      }
    })
  }
  public updateprofile(user:User):Observable<any>{
    return this.http.put<any>(`${this.apiServiceUrl}/keycloakUser/UpdateUser`,user,this.httpOption)
  }
}
