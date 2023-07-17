import { Component, EventEmitter, Output, ViewChild } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { SingleReservationFoyerCosumerService } from 'src/app/Services/SingleReservationService/SingleReservationFoyer-cosumer.service';
import { SingleReservationFoyer } from '../../data/SingleReservationFoyer/SingleReservationFoyer';
import { ProductService } from 'src/app/Services/SingleReservationService/product.service';
import { CalculService } from 'src/app/Services/SingleReservationService/calcul.service';
import { ProductCosumerService } from 'src/app/Services/SingleReservationService/product-cosumer.service';
import { Observable, of } from 'rxjs';
import { GalleryComponent } from '../../pages/gallery/gallery.component';
import { ProductsComponent } from '../../pages/SingleReservation/content/content.component';

@Component({
  selector: 'SingleReservationFoyer-sidebar',
  templateUrl: './SingleReservationFoyer-sidebar.component.html',
  styleUrls: ['./SingleReservationFoyer-sidebar.component.css']
})
export class SingleReservationFoyersidebarComponent  {
  @Output() updateValues = new EventEmitter<any>();
  page: number = 1;
  dd!:Date;
  df!:Date;
  active:Boolean=false;
  username!:String;
  products: SingleReservationFoyer[]=[];
  products2: SingleReservationFoyer[]=[];
  products3: SingleReservationFoyer[]=[];
  singleReservationFoyer! : SingleReservationFoyer;
  c!:number;
  searchResults: any[] = [];
  @ViewChild(ProductsComponent) productszComponent!: ProductsComponent;
  constructor(private router: Router,private singleReservationFoyerCosumerService : SingleReservationFoyerCosumerService,private _productService: ProductService, private calcul:CalculService,private productConsumer:ProductCosumerService,private activateRoute:ActivatedRoute,private productsComponent:ProductsComponent) { }

  chercher2dates(){
    //active and dates
    if (this.active==true && this.dd && this.df && !this.username){
      this.productConsumer.SearchBetweenTwoDates(this.dd,this.df).subscribe(
        singleReservationFoyer => {
          this.products2 = singleReservationFoyer;
          this.singleReservationFoyerCosumerService.setData(this.products);

          console.log(this.products)});
      this.productConsumer.retrieveActiveSingleReservationFoyer().subscribe(
        singleReservationFoyer => {
          this.products3 = singleReservationFoyer;
          this.singleReservationFoyerCosumerService.setData(this.products);

          console.log(this.products)});
          this.products = this.products2.filter(person1 => this.products3.some(person2 => person2.id === person1.id));
          this.productsComponent.reloadComponent.emit(this.products);
    }
    //dates only
    else if (this.active==false && this.dd && this.df && !this.username)  {
    this.productConsumer.SearchBetweenTwoDates(this.dd,this.df).subscribe(
      singleReservationFoyer => {
        this.products = singleReservationFoyer;


        this.singleReservationFoyerCosumerService.setData(this.products);
        this.productsComponent.reloadComponent.emit(this.products);

        console.log(this.products)});
      }
      //active only
      else if (this.active==true && (!this.dd || !this.df) && !this.username)
      {
        this.productConsumer.retrieveActiveSingleReservationFoyer().subscribe(
          singleReservationFoyer => {
            this.products = singleReservationFoyer;
            this.singleReservationFoyerCosumerService.setData(this.products);
            this.productsComponent.reloadComponent.emit(this.products);
            console.log(this.products)});

      }
      //active and dates and username
      else if (this.active==true && this.dd && this.df && this.username) {
        this.productConsumer.SearchBetweenTwoDates(this.dd,this.df).subscribe(
          singleReservationFoyer => {
            this.products2 = singleReservationFoyer;
            this.singleReservationFoyerCosumerService.setData(this.products);

            console.log(this.products)});
        this.productConsumer.retrieveActiveSingleReservationFoyerByUsername(this.username).subscribe(
          singleReservationFoyer => {
            this.products3 = singleReservationFoyer;
            this.singleReservationFoyerCosumerService.setData(this.products);

            console.log(this.products)});
            this.products = this.products2.filter(person1 => this.products3.some(person2 => person2.id === person1.id));
            this.productsComponent.reloadComponent.emit(this.products);
      }
      //active and username
      else if (this.active==true && (!this.dd || !this.df) && this.username) {

        this.productConsumer.retrieveActiveSingleReservationFoyerByUsername(this.username).subscribe(
          singleReservationFoyer => {
            this.products = singleReservationFoyer;
            this.singleReservationFoyerCosumerService.setData(this.products);

            console.log(this.products)});

            this.productsComponent.reloadComponent.emit(this.products);
      }
      //username only
      else if (this.active==false && (!this.dd && !this.df) && this.username) {

        this.productConsumer.retrieveSingleReservationFoyerByUsername(this.username).subscribe(
          singleReservationFoyer => {
            this.products = singleReservationFoyer;
            this.singleReservationFoyerCosumerService.setData(this.products);

            console.log(this.products)});

            this.productsComponent.reloadComponent.emit(this.products);
      }
      //username and dates
      else {
        this.productConsumer.getProducts().subscribe(
          singleReservationFoyer => {
            this.products = singleReservationFoyer;
            this.singleReservationFoyerCosumerService.setData(this.products);
            this.productsComponent.reloadComponent.emit(this.products);
            console.log(this.products)});
      }

  }

}

