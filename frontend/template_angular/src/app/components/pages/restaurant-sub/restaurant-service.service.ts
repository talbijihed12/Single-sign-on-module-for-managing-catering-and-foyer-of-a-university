import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginService } from '../../shared/services/login.service';
import { Observable } from 'rxjs';
import { Abonnement_R } from '../../models/Abonnement_R';

@Injectable({
  providedIn: 'root'
})
export class RestaurantServiceService {
  private apiUrl = 'http://localhost:8097/abonn/allR';

  constructor(private http: HttpClient,private loginService:LoginService) { }

  getListe(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
  addSubRestaurant(Content:Abonnement_R){
console.log(Content);
console.log(this.loginService.getTOKEN());
    const uri = `http://localhost:8097/abonn/addAbonnementR`
    return this.http.post(uri,Content,{headers:{
      'Authorization': `Bearer ${this.loginService.getTOKEN()}`
    }})
  }
  deleteSubscription(id:any) {
    const uri = `http://localhost:8097/abonn/deleteAbonnementR/${id}`
    return this.http.delete(uri, {
      headers: {
        'Authorization': `Bearer ${this.loginService.getTOKEN()}`,
        'Content-type':'application/json'

      }
    })
  }
  updateSubscription(id:any) {
    const uri = `http://localhost:8097/abonn/deleteAbonnementR/${id}`
    return this.http.delete(uri, {
      headers: {
        'Authorization': `Bearer ${this.loginService.getTOKEN()}`,
        'Content-type':'application/json'

      }
    })
  }
  updatejeton(nbJeton:any) {

    const uri = `http://localhost:8097/abonn/addJeton/${nbJeton}/${this.loginService.getUsername()}`
    return this.http.put(uri, {
      headers: {
        'Authorization': `Bearer ${this.loginService.getTOKEN()}`,
        'Content-type':'application/json'

      }
    })
  }
}
