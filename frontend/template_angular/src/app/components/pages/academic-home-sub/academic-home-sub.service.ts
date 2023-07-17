import { LoginService } from './../../shared/services/login.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Abonnement_F } from '../../models/Abonnement_F';


@Injectable({
  providedIn: 'root'
})
export class AcademicHomeSubService {
  private apiUrl = 'http://localhost:8097/abonn/allF';

  constructor(private http: HttpClient,private loginService:LoginService) { }

  getListe(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  deleteSubscription(id:any) {
    const uri = `http://localhost:8097/abonn/deleteAbonnementF/${id}`
    return this.http.delete(uri, {
      headers: {
        'Authorization': `Bearer ${this.loginService.getTOKEN()}`,
        'Content-type':'application/json'

      }
    })
  }
  addSubscription(Content:Abonnement_F){

    const uri = `http://localhost:8097/abonn/addAbonnementF`
    return this.http.post(uri,Content,{headers:{
      'Authorization': `Bearer ${this.loginService.getTOKEN()}`
    }})
  }
}
