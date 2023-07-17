import { LoginService } from 'src/app/components/shared/services/login.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SingleReservationFoyer } from '../../components/data/SingleReservationFoyer/SingleReservationFoyer';
import { Observable,Subject  } from 'rxjs';
import { ReservationFoyer } from 'src/app/components/data/SingleReservationFoyer/ReservationFoyer';
import { ReservationRestaurant } from 'src/app/components/data/SingleReservationFoyer/ReservationRestaurant';

@Injectable({
  providedIn: 'root'
})
export class ProductCosumerService {
  apiUrl:string = "http://localhost:8095/srf"
  apiUrlRF:string = "http://localhost:8095/rf"
  apiUrlRR:string = "http://localhost:8095/rr"
  constructor(private loginservice:LoginService,private http:HttpClient) { }
  minPrice!:Date;
  maxPrice!:Date;
  private searchResults$!: Observable<SingleReservationFoyer[]>;

  getProducts():Observable<SingleReservationFoyer[]>{
    return this.http.get<SingleReservationFoyer[]>(this.apiUrl+"/retrieveAllSingleReservationFoyer")
  }
  getProductsByUsername():Observable<SingleReservationFoyer[]>{
    return this.http.get<SingleReservationFoyer[]>(this.apiUrl+"/retrieveSingleReservationFoyerByUsername/"+this.loginservice.getUsername())
  }


  SearchBetweenTwoDates(dd:Date,df:Date):Observable<SingleReservationFoyer[]>{
    return this.http.get<SingleReservationFoyer[]>(this.apiUrl+"/retrieveSingleReservationFoyerBetweenTwoDates/"+dd+"/"+df)
  }

  getProductById(id:number){
    return this.http.get<SingleReservationFoyer>(this.apiUrl+"/"+id)
  }

  addProduct(product:SingleReservationFoyer){
    return this.http.post(this.apiUrl,product)
  }

  updateProduct(product:SingleReservationFoyer){
    return this.http.put(this.apiUrl+'/'+product.id,product)
  }

  deleteProduct(id:number){
    return this.http.delete(this.apiUrl+"/"+id)
  }


  GetActiveSingleReservationFoyerByUsernameAdmin(username:String):Observable<SingleReservationFoyer[]>{
    return this.http.get<SingleReservationFoyer[]>(this.apiUrl+"/retrieveActiveSingleReservationFoyerByUsername/"+username);
  }
  setSearchResults(results$: Observable<SingleReservationFoyer[]>) {
      this.searchResults$ = results$;
    }

  getSearchResults(): Observable<SingleReservationFoyer[]> {
    return this.searchResults$;
    }
  retrieveActiveSingleReservationFoyer():Observable<SingleReservationFoyer[]> {
    return this.http.get<SingleReservationFoyer[]>(this.apiUrl+"/retrieveActiveSingleReservationFoyer/");
  }
  retrieveActiveSingleReservationFoyerByUsername(username:String):Observable<SingleReservationFoyer[]> {
    return this.http.get<SingleReservationFoyer[]>(this.apiUrl+"/retrieveActiveSingleReservationFoyerByUsername/"+username);
  }
  retrieveSingleReservationFoyerByUsername(username:String):Observable<SingleReservationFoyer[]> {
    return this.http.get<SingleReservationFoyer[]>(this.apiUrl+"/retrieveSingleReservationFoyerByUsername/"+username);
  }




  //rf
  getProductsRF():Observable<ReservationFoyer[]>{
    return this.http.get<ReservationFoyer[]>(this.apiUrlRF+"/retrieveAllReservationFoyer")
  }

  SearchBetweenTwoDatesRF(dd:Date,df:Date):Observable<ReservationFoyer[]>{
    return this.http.get<ReservationFoyer[]>(this.apiUrlRF+"/retrieveReservationFoyerBetweenTwoDates/"+dd+"/"+df)
  }

  getProductByIdRF(id:number){
    return this.http.get<ReservationFoyer>(this.apiUrlRF+"/retrieveReservationFoyer"+id)
  }

  addProductRF(product:ReservationFoyer){
    return this.http.post(this.apiUrlRF+"/add",product)
  }

  updateProductRF(product:ReservationFoyer){
    return this.http.put(this.apiUrlRF+'/modifyReservationFoyer'+product.id,product)
  }

  deleteProductRF(id:number){
    return this.http.delete(this.apiUrlRF+"/removeReservationFoyer"+id)
  }


  GetActiveSingleReservationFoyerByUsernameAdminRF(username:String):Observable<ReservationFoyer[]>{
    return this.http.get<ReservationFoyer[]>(this.apiUrlRF+"/retrieveActiveReservationFoyerByUsername/"+username);
  }

  retrieveActiveSingleReservationFoyerRF():Observable<ReservationFoyer[]> {
    return this.http.get<ReservationFoyer[]>(this.apiUrlRF+"/retrieveActiveReservationFoyer/");
  }
  retrieveActiveSingleReservationFoyerByUsernameRF(username:String):Observable<ReservationFoyer[]> {
    return this.http.get<ReservationFoyer[]>(this.apiUrlRF+"/retrieveActiveReservationFoyerByUsername/"+username);
  }
  retrieveSingleReservationFoyerByUsernameRF(username:String):Observable<ReservationFoyer[]> {
    return this.http.get<ReservationFoyer[]>(this.apiUrlRF+"/retrieveReservationFoyerByUserId/"+username);
  }
  retrieveApprovedReservationFoyerBetweenTwoDates(dd:Date,df:Date):Observable<ReservationFoyer[]> {
    return this.http.get<ReservationFoyer[]>(this.apiUrlRF+"/retrieveApprovedReservationFoyerBetweenTwoDates/"+dd+"/"+df);
  }


  //rr

   getProductsRR():Observable<ReservationRestaurant[]>{
    return this.http.get<ReservationRestaurant[]>(this.apiUrlRR+"/retrieveAllReservationRestaurant")
  }

  SearchBetweenTwoDatesRR(dd:Date,df:Date):Observable<ReservationRestaurant[]>{
    return this.http.get<ReservationRestaurant[]>(this.apiUrlRR+"/retrieveReservationFoyerBetweenTwoDates/"+dd+"/"+df)
  }

  getProductByIdRR(id:number){
    return this.http.get<ReservationRestaurant>(this.apiUrlRR+"/retrieveReservationFoyer"+id)
  }

  addProductRR(product:ReservationRestaurant){
    return this.http.post(this.apiUrlRR+"/add",product)
  }

  updateProductRR(product:ReservationRestaurant){
    return this.http.put(this.apiUrlRR+'/modifyReservationFoyer'+product.idR,product)
  }

  deleteProductRR(id:number){
    return this.http.delete(this.apiUrlRR+"/removeReservationFoyer"+id)
  }


  GetActiveSingleReservationFoyerByUsernameAdminRR(username:String):Observable<ReservationRestaurant[]>{
    return this.http.get<ReservationRestaurant[]>(this.apiUrlRR+"/retrieveActiveReservationFoyerByUsername/"+username);
  }

  retrieveActiveSingleReservationFoyerRR():Observable<ReservationRestaurant[]> {
    return this.http.get<ReservationRestaurant[]>(this.apiUrlRR+"/retrieveActiveReservationFoyer/");
  }
  retrieveActiveSingleReservationFoyerByUsernameRR(username:String):Observable<ReservationRestaurant[]> {
    return this.http.get<ReservationRestaurant[]>(this.apiUrlRR+"/retrieveActiveReservationFoyerByUsername/"+username);
  }
  retrieveSingleReservationFoyerByUsernameRR(username:String):Observable<ReservationRestaurant[]> {
    return this.http.get<ReservationRestaurant[]>(this.apiUrlRR+"/retrieveReservationFoyerByUserId/"+username);
  }
  retrieveApprovedReservationRestaurantBetweenTwoDates(dd:Date,df:Date):Observable<ReservationRestaurant[]> {
    return this.http.get<ReservationRestaurant[]>(this.apiUrlRR+"/retrieveApprovedReservationFoyerBetweenTwoDates/"+dd+"/"+df);
  }

}
