import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Directive, Injectable } from '@angular/core';
import { SingleReservationFoyer } from '../../components/data/SingleReservationFoyer/SingleReservationFoyer';
import { ReservationFoyer } from 'src/app/components/data/SingleReservationFoyer/ReservationFoyer';
import { ReservationRestaurant } from 'src/app/components/data/SingleReservationFoyer/ReservationRestaurant';
import { LoginService } from 'src/app/components/shared/services/login.service';

@Injectable({
  providedIn: 'root'
})
export class SingleReservationFoyerCosumerService {
  page: number = 1;

  apiUrl:string = "http://localhost:8095/srf"
  apiUrlRF:string = "http://localhost:8095/rf"
  apiUrlRR:string = "http://localhost:8095/rr"
  singleReservationFoyer: SingleReservationFoyer[]=[]
  constructor(private http:HttpClient,private loginService:LoginService) { }



  getProducts(){
    return this.http.get<SingleReservationFoyer[]>(this.apiUrl+"/retrieveAllSingleReservationFoyer")
  }
  getProductById(id:number){
    return this.http.get<SingleReservationFoyer>(this.apiUrl+"/retrieveSingleReservationFoyer/"+id)
  }
  addProduct(product:SingleReservationFoyer,){
    const aa = new HttpHeaders().set('Authorization', 'Bearer ' + this.loginService.getTOKEN());
    return this.http.post(this.apiUrl+"/addSingleReservationFoyer",product, { headers: aa })
  }
  addProductUsername(product:SingleReservationFoyer){
    const aa = new HttpHeaders().set('Authorization', 'Bearer ' + this.loginService.getTOKEN());
    return this.http.post(this.apiUrl+"/addSingleReservationFoyer/"+product.username,product, { headers: aa })
  }
  updateProduct(product:SingleReservationFoyer,id:number){
    return this.http.put(this.apiUrl+'/updateSingleReservationFoyer/'+id,product)
  }
  deleteProduct(id:number){
    return this.http.delete(this.apiUrl+"/deleteSingleReservationFoyer/"+id);
  }
  setData(singleReservationFoyer: SingleReservationFoyer[]) {
    this.singleReservationFoyer = singleReservationFoyer;
    console.log(this.singleReservationFoyer);
  }
  getData(): any[] {
    return this.singleReservationFoyer;
  }
  cancelReservationFoyer(id:number) {
    return this.http.delete(this.apiUrl+"/cancelSingleReservationFoyer/"+id);
    console.log("aa");
  }
  //ReservationFoyerGroupe
  addGroupRese(product:ReservationFoyer){
    headers: new HttpHeaders( {
      'Content-Type': 'application/json'
      //'Authorization': 'Basic ' + token
    } )
    return this.http.post(this.apiUrlRF+"/add/"+product.username,product)
  }
  addGroupReseClient(product:ReservationFoyer){
    headers: new HttpHeaders( {
      'Content-Type': 'application/json'
      //'Authorization': 'Basic ' + token
    } )
    product.username=this.loginService.getUsername();
    return this.http.post(this.apiUrlRF+"/addWithUsername",product)
  }

  getReservationFoyerById(id:number) {
    return this.http.get<ReservationFoyer>(this.apiUrlRF+"/retrieveReservationFoyer/"+id)
  }
  cancelReservationFoyerGroup(id:number) {
    return this.http.delete(this.apiUrlRF+"/cancelSingleReservationFoyer/"+id);
    console.log("aa");
  }
  deleteReservationFoyer(id:number){
    return this.http.delete(this.apiUrlRF+"/deleteSingleReservationFoyer/"+id);
  }
  updateReservationFoyer(product:ReservationFoyer,id:number){
    return this.http.put(this.apiUrlRF+'/modifyReservationFoyer/'+id,product)
  }
  approveReservationFoyer(id:number,list:number[]){
    return this.http.put(this.apiUrlRF+'/approveReservationFoyer/'+id,list)
  }
  //ReservationRestaurant


   addReservationRestaurant (product:ReservationRestaurant){
    headers: new HttpHeaders( {
      'Content-Type': 'application/json'
      //'Authorization': 'Basic ' + token
    } )
    return this.http.post(this.apiUrlRR+"/add/",product)
  }
  getReservationRestaurantById(id:number) {
    return this.http.get<ReservationRestaurant>(this.apiUrlRR+"/retrieveReservationRestaurant/"+id)
  }
  cancelReservationRestaurantGroup(id:number) {
    return this.http.delete(this.apiUrlRR+"/removeReservationRestaurant/"+id);
    console.log("aa");
  }
  deleteReservationRestaurant(id:number){
    return this.http.delete(this.apiUrlRR+"/removeReservationRestaurant/"+id);
  }
  updateReservationRestaurant(product:ReservationRestaurant,id:number){
    return this.http.put(this.apiUrlRR+'/modifyReservationRestaurant/'+id,product)
  }
  approveReservationRestaurant(id:number,list:number[]){
    return this.http.put(this.apiUrlRR+'/approveReservationRestaurant/'+id,list)
  }

}
