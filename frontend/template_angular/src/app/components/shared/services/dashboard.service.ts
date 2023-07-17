import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private apiServerUrl = environment.apiBaseUrl4 +'/dashboard';

  constructor(private http: HttpClient){}

  getClaimsByStatus(): Observable<any> {
    return this.http.get(this.apiServerUrl + '/claim/admin')
  }
  getClaimsByMonth(): Observable<any> {
    return this.http.get(this.apiServerUrl + '/claims' )
  }

  getClaimsByType(): Observable<any> {
    return this.http.get(this.apiServerUrl + '/getNbClaimsByType' )
  }





}
