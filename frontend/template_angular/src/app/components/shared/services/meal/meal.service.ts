import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Meal } from 'src/app/components/models/Meals';
import { Menu } from 'src/app/components/models/Menu';



@Injectable({
  providedIn: 'root'
})
export class MealService {


private apiServerUrl = environment.apiBaseUrl3;

  constructor(private http: HttpClient){}


   public getmeal(): Observable<Meal[]> {
    console.log('getMeals')
    return this.http.get<Meal[]>(`${this.apiServerUrl}/getMeal`);

   }

  getMealById(id: any): Observable<Meal>{
   console.log('ID', id)
    return this.http.get<Meal>(`${this.apiServerUrl}/getOnemeal/${id}`)
  }

  deleteMealById(id: any): Observable<Meal>{
   console.log('ID', id)
    return this.http.delete<Meal>(`${this.apiServerUrl}/deletemeal/${id}`)
  }


  addmeal(meal: Meal) {
    console.log('add meal', meal)
    return this.http.post<Meal>(`${this.apiServerUrl}/addmeal`,meal)
  }


  updatemeal(id:any,meal: any): Observable<Meal>{
    console.log('updatemeal')
    return this.http.put<Meal>(`${this.apiServerUrl}/updateMeal/${id}`, meal)
  }

  affectMealToMenu(id: any, menu: Menu): Observable<Meal> {
    console.log('affectMealToMenuuuuuu')
        return this.http.post<Meal>(`${this.apiServerUrl}/affectMealToMenuu/${id}`, menu)
  }



}
