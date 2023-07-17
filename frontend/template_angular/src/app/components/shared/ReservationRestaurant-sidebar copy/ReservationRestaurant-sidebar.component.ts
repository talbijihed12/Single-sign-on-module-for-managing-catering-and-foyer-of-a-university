import { Component, EventEmitter, Inject, Output, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SingleReservationFoyerCosumerService } from 'src/app/Services/SingleReservationService/SingleReservationFoyer-cosumer.service';
import { ProductService } from 'src/app/Services/SingleReservationService/product.service';
import { CalculService } from 'src/app/Services/SingleReservationService/calcul.service';
import { ProductCosumerService } from 'src/app/Services/SingleReservationService/product-cosumer.service';
import { ProductComponent } from '../../pages/reservationFoyer/content/ProductComponent';
import { ReservationRestaurant } from '../../data/SingleReservationFoyer/ReservationRestaurant';


@Component({
  selector: 'ReservationFoyer-sidebar',
  templateUrl: './ReservationRestaurant-sidebar.component.html',
  styleUrls: ['./ReservationRestaurant-sidebar.component.css']
})
export class ReservationRestaurantidebarComponent  {
  @Output() updateValues = new EventEmitter<any>();
  page: number = 1;
  dd!:Date;
  df!:Date;
  active:Boolean=false;
  approved:Boolean=false;
  username!:String;
  products: ReservationRestaurant[]=[];
  products2: ReservationRestaurant[]=[];
  products3: ReservationRestaurant[]=[];
  reservationFoyer! : ReservationRestaurant;
  c!:number;
  searchResults: any[] = []; 
  @ViewChild(ProductComponent) productszComponent!: ProductComponent;
  constructor(private router: Router,private singleReservationFoyerCosumerService : SingleReservationFoyerCosumerService,private _productService: ProductService, private calcul:CalculService,private productConsumer:ProductCosumerService,private activateRoute:ActivatedRoute,private productsComponent:ProductComponent) { }

  chercher2dates(){
    //active and dates
    if (this.active==true && this.dd && this.df && !this.username){
      this.productConsumer.SearchBetweenTwoDatesRR(this.dd,this.df).subscribe(
        singleReservationFoyer => { 
          this.products2 = singleReservationFoyer;
          //this.singleReservationFoyerCosumerService.setData(this.products);
          
          console.log(this.products)});
      this.productConsumer.retrieveActiveSingleReservationFoyerRR().subscribe(
        singleReservationFoyer => { 
          this.products3 = singleReservationFoyer;
          //this.singleReservationFoyerCosumerService.setData(this.products);
          
          console.log(this.products)});
          this.products = this.products2.filter(person1 => this.products3.some(person2 => person2.idR === person1.idR));
          this.productsComponent.reloadComponent.emit(this.products);
    }
    //active and dates and approved
    if (this.active==true && this.dd && this.df && !this.username && this.approved==true){
      this.productConsumer.retrieveApprovedReservationRestaurantBetweenTwoDates(this.dd,this.df).subscribe(
        singleReservationFoyer => { 
          this.products2 = singleReservationFoyer;
          //this.singleReservationFoyerCosumerService.setData(this.products);
          
          console.log(this.products)});
      this.productConsumer.retrieveActiveSingleReservationFoyerRR().subscribe(
        singleReservationFoyer => { 
          this.products3 = singleReservationFoyer;
          //this.singleReservationFoyerCosumerService.setData(this.products);
          
          console.log(this.products)});
          this.products = this.products2.filter(person1 => this.products3.some(person2 => person2.idR === person1.idR));
          this.productsComponent.reloadComponent.emit(this.products);
    }
    //dates only
    else if (this.active==false && this.dd && this.df && !this.username)  {
    this.productConsumer.retrieveApprovedReservationRestaurantBetweenTwoDates(this.dd,this.df).subscribe(
      singleReservationFoyer => { 
        this.products = singleReservationFoyer;
      

        //this.singleReservationFoyerCosumerService.setData(this.products);
        this.productsComponent.reloadComponent.emit(this.products);
      
        console.log(this.products)});
      }
      //dates and approved
    else if (this.active==false && this.dd && this.df && !this.username)  {
      this.productConsumer.retrieveApprovedReservationRestaurantBetweenTwoDates(this.dd,this.df).subscribe(
        singleReservationFoyer => { 
          this.products = singleReservationFoyer;
        
  
          //this.singleReservationFoyerCosumerService.setData(this.products);
          this.productsComponent.reloadComponent.emit(this.products);
        
          console.log(this.products)});
        }
      //active only
      else if (this.active==true && (!this.dd || !this.df) && !this.username) 
      {
        this.productConsumer.retrieveActiveSingleReservationFoyerRR().subscribe(
          singleReservationFoyer => { 
            this.products = singleReservationFoyer;
            //this.singleReservationFoyerCosumerService.setData(this.products);
            this.productsComponent.reloadComponent.emit(this.products);
            console.log(this.products)});
           
      }
      //active and dates and username
      else if (this.active==true && this.dd && this.df && this.username && this.approved==false) {
        this.productConsumer.retrieveApprovedReservationRestaurantBetweenTwoDates(this.dd,this.df).subscribe(
          singleReservationFoyer => { 
            this.products2 = singleReservationFoyer;
            //this.singleReservationFoyerCosumerService.setData(this.products);
            
            console.log(this.products)});
        this.productConsumer.retrieveActiveSingleReservationFoyerByUsernameRR(this.username).subscribe(
          singleReservationFoyer => { 
            this.products3 = singleReservationFoyer;
           // this.singleReservationFoyerCosumerService.setData(this.products);
            
            console.log(this.products)});
            this.products = this.products2.filter(person1 => this.products3.some(person2 => person2.idR === person1.idR));
            this.productsComponent.reloadComponent.emit(this.products);
      }
      //active and username 
      else if (this.active==true && (!this.dd || !this.df) && this.username) {
       
        this.productConsumer.retrieveActiveSingleReservationFoyerByUsernameRR(this.username).subscribe(
          singleReservationFoyer => { 
            this.products = singleReservationFoyer;
            //this.singleReservationFoyerCosumerService.setData(this.products);
            
            console.log(this.products)});
            
            this.productsComponent.reloadComponent.emit(this.products);
      }
      //username only 
      else if (this.active==false && (!this.dd && !this.df) && this.username) {
       
        this.productConsumer.retrieveSingleReservationFoyerByUsernameRR(this.username).subscribe(
          singleReservationFoyer => { 
            this.products = singleReservationFoyer;
            
            
            console.log(this.products)});
            
            this.productsComponent.reloadComponent.emit(this.products);
      }
      //username and dates
      else if (this.active==false && this.dd && this.df && this.username)
      {
        this.productConsumer.retrieveActiveSingleReservationFoyerByUsernameRR(this.username).subscribe(
          singleReservationFoyer => { 
            this.products3 = singleReservationFoyer;
           // this.singleReservationFoyerCosumerService.setData(this.products);
            
            console.log(this.products)});
            this.productConsumer.retrieveApprovedReservationRestaurantBetweenTwoDates(this.dd,this.df).subscribe(
              singleReservationFoyer => { 
                this.products2 = singleReservationFoyer;
                //this.singleReservationFoyerCosumerService.setData(this.products);
                
                console.log(this.products)});
                this.products = this.products2.filter(person1 => this.products3.some(person2 => person2.idR === person1.idR));
            this.productsComponent.reloadComponent.emit(this.products);

      }
      //username and dates and approved
      else if (this.active==false && this.dd && this.df && this.username)
      {
        this.productConsumer.retrieveActiveSingleReservationFoyerByUsernameRR(this.username).subscribe(
          singleReservationFoyer => { 
            this.products3 = singleReservationFoyer;
           // this.singleReservationFoyerCosumerService.setData(this.products);
            
            console.log(this.products)});
            this.productConsumer.retrieveApprovedReservationRestaurantBetweenTwoDates(this.dd,this.df).subscribe(
              singleReservationFoyer => { 
                this.products2 = singleReservationFoyer;
                //this.singleReservationFoyerCosumerService.setData(this.products);
                
                console.log(this.products)});
                this.products = this.products2.filter(person1 => this.products3.some(person2 => person2.idR === person1.idR));
            this.productsComponent.reloadComponent.emit(this.products);

      }
      else {
        this.productConsumer.getProductsRR().subscribe(
          singleReservationFoyer => { 
            this.products = singleReservationFoyer;
            
            this.productsComponent.reloadComponent.emit(this.products);
            console.log(this.products)});
      }
        
  }
  
}

