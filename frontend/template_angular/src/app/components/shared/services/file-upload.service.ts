import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  private baseUrl = environment.apiBaseUrl4 +'/upload';
  constructor(private httpClient: HttpClient) { }

  upload(formData: FormData, idClaim:any): Observable<HttpEvent<any>> {


    const req = new HttpRequest('POST', `${this.baseUrl}/uploadFile/` +idClaim, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.httpClient.request(req);
  }
  downloadFile(fileName:any): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/files/' + fileName);
  }
  public getFleByClaim(id:any): Observable<any[]> {
    return this.httpClient.get<any[]>(`${this.baseUrl}/findByClaim/` + id );
  }
}
