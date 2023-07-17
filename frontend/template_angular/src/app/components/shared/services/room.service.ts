import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { BlockService } from './block.service';
import { HttpClient } from '@angular/common/http';
import { Room } from '../../models/Room';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private apiServerUrl = environment.apiBaseUrl2;
  private Block = BlockService ;

  constructor(private http: HttpClient){}
  public ajouterRoom(room : Room ,idB:number): Observable<any> {

    return this.http.post<any>(`${this.apiServerUrl}/ajouterRoom/${idB}`,room);
  }
  public getRoom(): Observable<Room[]> {
    return this.http.get<Room[]>(`${this.apiServerUrl}/Rooms`);
}
deleteRoom(idR : any){
  return  this.http.delete(`${this.apiServerUrl}/deleteRoom/${idR}`)
}
updateRoom(room : Room, idR : any){
  return this.http.put(`${this.apiServerUrl}/updateRoom/${idR}`,room )
}
public assignRoomBlock(idR : number, idB : number): Observable<Room>{
  return this.http.put<Room>(`${this.apiServerUrl}/affecterRoomBlock/${idR}/${idB}`,{} )
}
public BookPlace(idR : number) : Observable<any>{
  return this.http.put<Room>(`${this.apiServerUrl}/BookPlaceRoom/${idR}`,{} )
}
public UnBookPlace(idR : number) : Observable<any>{
  return this.http.put<Room>(`${this.apiServerUrl}/UnBookPlaceRoom/${idR}`,{} )
}
public RoomAvailable(types:String,sexe:string) : Observable<Room[]>{
  return this.http.get<Room[]>(`${this.apiServerUrl}/RoomParTypeSexe/${types}/${sexe}`,{} )

}

 }
