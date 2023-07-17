import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

import { Observable } from 'rxjs';
import { Menu } from '../../../models/Menu';


@Injectable({
  providedIn: 'root'
})



export class MenuService {



private apiServerUrl = environment.apiBaseUrl3;

  constructor(private http: HttpClient){}
  public getmenus(): Observable<Menu[]> {
    console.log('getMenu')
    return this.http.get<Menu[]>(`${this.apiServerUrl}/getMenu`);

  }
  getMenuById(id: any): Observable<Menu>{
   console.log('ID', id)
    return this.http.get<Menu>(`${this.apiServerUrl}/getOnemenu/${id}`)
  }

  addmenu(menu: any) {
     console.log('add menu')
    return this.http.post<Menu>(`${this.apiServerUrl}/ajouterMenu`,menu)
  }

  deleteMenuById(id: any): Observable<Menu>{
   console.log('ID', id)
    return this.http.delete<Menu>(`${this.apiServerUrl}/deletemenu/${id}`)
  }


  updatemenu(id:any,menu: any): Observable<Menu>{
    console.log('updatemenu')
    return this.http.put<Menu>(`${this.apiServerUrl}/updateMenu/${id}`, menu)
  }




}
