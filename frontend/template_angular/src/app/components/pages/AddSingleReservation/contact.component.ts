import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { SingleReservationFoyer } from '../../data/SingleReservationFoyer/SingleReservationFoyer';
import { ReservationFoyer } from '../../data/SingleReservationFoyer/ReservationFoyer';
import { SingleReservationFoyerService } from '../../../Services/SingleReservationService/SingleReservationFoyer.service';
import { SingleReservationFoyerCosumerService } from '../../../Services/SingleReservationService/SingleReservationFoyer-cosumer.service';
import { LoginService } from '../../shared/services/login.service';



@Component({
  selector: 'app-addSingleReservationFoyer',
  templateUrl: './addSingleReservationFoyer.component.html',
  styleUrls: ['./addSingleReservationFoyer.component.css']
})

export class AddSingleReservationFoyerComponent implements OnInit {
  singleReservationFoyer!:SingleReservationFoyer;
  singleReservationFoyers !: SingleReservationFoyer[];
  reservationFoyer!:ReservationFoyer;
  reservationFoyers!:ReservationFoyer[];

  email!:string
  id!:any
  constructor(private loginServie:LoginService,private _productService: SingleReservationFoyerService,private _router:Router,private productConsumer:SingleReservationFoyerCosumerService,private activateRoute:ActivatedRoute) { }

  ngOnInit(): void {
    console.log(this.loginServie.getUsername());
    console.log("aa");
    this.id = this.activateRoute.snapshot.params['id'];
    if(this.id!=null){
      this.productConsumer.getProductById(this.id).subscribe({
        next : (data)=>this.singleReservationFoyer=data
      })
    }else {
      this.singleReservationFoyer = new SingleReservationFoyer();
      this.reservationFoyer = new ReservationFoyer();
    }
  }

  ajouter(){
    //this._productService.addProduct(this.product)
    if(this.id!=null){
      this.productConsumer.updateProduct(this.singleReservationFoyer,this.singleReservationFoyer.id).subscribe({
        next: ()=>this._router.navigateByUrl('/'),
        error: (error)=>console.log(error),
        complete:()=>console.log("I m finishing")
      })
    }else {
      this.reservationFoyer.expired=false;
      this.reservationFoyer.approved=false;
    this.productConsumer.addProductUsername(this.singleReservationFoyer).subscribe({
      next: ()=>this._router.navigateByUrl('/SingleReservationFoyer'),
      error: (error)=>console.log(error),
      complete:()=>console.log("I m finishing")
    })
  }
    // => routerLink="/products" HTML
    console.log(this.reservationFoyer)
  }
  ajouterGroupe(){
    //this._productService.addProduct(this.product)

      this.singleReservationFoyer.expired=false;
    this.productConsumer.addGroupRese(this.reservationFoyer).subscribe({
      next: ()=>this._router.navigateByUrl('/ReservationFoyers'),
      error: (error)=>console.log(error),
      complete:()=>console.log("I m finishing")
    })

    // => routerLink="/products" HTML
    console.log(this.singleReservationFoyer)
  }
}

