import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SingleReservationFoyerService } from '../../../Services/SingleReservationService/SingleReservationFoyer.service';
import { SingleReservationFoyerCosumerService } from '../../../Services/SingleReservationService/SingleReservationFoyer-cosumer.service';
import { ReservationRestaurant } from '../../data/SingleReservationFoyer/ReservationRestaurant';



@Component({
  selector: 'app-addSingleReservationFoyer',
  templateUrl: './AddReservationRestaurant.component.html',
  styleUrls: ['./AddReservationRestaurant.component.css']
})

export class AddReservationRestaurantComponent implements OnInit {
 
  reservationFoyer!:ReservationRestaurant;
  reservationFoyers!:ReservationRestaurant[];
  
  email!:string
  id!:any
  constructor(private _productService: SingleReservationFoyerService,private _router:Router,private productConsumer:SingleReservationFoyerCosumerService,private activateRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.activateRoute.snapshot.params['id'];
    if(this.id!=null){
      this.productConsumer.getReservationRestaurantById(this.id).subscribe({
        next : (data)=>this.reservationFoyer=data
      })
    }else {
      
      this.reservationFoyer = new ReservationRestaurant();
    }
  }

  
  ajouterGroupe(){
    //this._productService.addProduct(this.product)
    
      
    this.productConsumer.addReservationRestaurant(this.reservationFoyer).subscribe({
      next: ()=>this._router.navigateByUrl('/ReservationFoyers'),
      error: (error)=>console.log(error),
      complete:()=>console.log("I m finishing")
    })
  
    // => routerLink="/products" HTML
    console.log(this.reservationFoyer)
  }
}

