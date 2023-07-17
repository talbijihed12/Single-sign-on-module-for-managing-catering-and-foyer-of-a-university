import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { SingleReservationFoyer } from '../../data/SingleReservationFoyer/SingleReservationFoyer';
import { ReservationFoyer } from '../../data/SingleReservationFoyer/ReservationFoyer';
import { SingleReservationFoyerService } from '../../../Services/SingleReservationService/SingleReservationFoyer.service';
import { SingleReservationFoyerCosumerService } from '../../../Services/SingleReservationService/SingleReservationFoyer-cosumer.service';
import { LoginService } from '../../shared/services/login.service';
import { Room } from '../../models/Room';
import { RoomService } from '../../shared/services/room.service';
import { Observable } from 'rxjs';



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
  sexe!:string;
  email!:string
  roomid!:number;
  roomlist!:Room[];
  MaleisChecked = false;
  FemaleisChecked = false;
  MixteisChecked = false;
  radioChecked=false;
  id!:any
  constructor(private roomservice:RoomService,private loginServie:LoginService,private _productService: SingleReservationFoyerService,private _router:Router,private productConsumer:SingleReservationFoyerCosumerService,private activateRoute:ActivatedRoute) { }

  ngOnInit(): void {

    console.log(this.roomlist);
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
      this.singleReservationFoyer.idRoom=this.roomid;
    this.productConsumer.addProduct(this.singleReservationFoyer).subscribe({
      next: ()=>this._router.navigateByUrl('/SingleReservationFoyerClient'),
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
    this.productConsumer.addGroupReseClient(this.reservationFoyer).subscribe({
      next: ()=>this._router.navigateByUrl('/ReservationFoyersClient'),
      error: (error)=>console.log(error),
      complete:()=>console.log("I m finishing")
    })

    // => routerLink="/products" HTML
    console.log(this.singleReservationFoyer)
  }
  showAvailableRoomsByBlocType() {
    if (this.MaleisChecked) {
      this.roomservice.RoomAvailable("RESIDANT_Student","MALE").subscribe(rooms => {
        this.roomlist = rooms;
        if (this.roomlist) {
          this.roomlist = this.roomlist.filter(room => (room.nbrPlace - room.placesOcc >0));
        }
      });
      console.log(this.roomlist)


      this.MaleisChecked=false;
    }
    else  if (this.FemaleisChecked) {
      this.roomservice.RoomAvailable("RESIDANT_Student","FEMALE").subscribe(rooms => {
        this.roomlist = rooms;
        if (this.roomlist) {
          this.roomlist = this.roomlist.filter(room => (room.nbrPlace - room.placesOcc >0));
        }
      });

      console.log("2",this.roomlist)
      this.FemaleisChecked=false;
    }
    else  if (this.MixteisChecked) {
      this.roomservice.RoomAvailable("RESIDANT_Student","MIXTE").subscribe(rooms => {
        this.roomlist = rooms;
        if (this.roomlist) {
          this.roomlist = this.roomlist.filter(room => (room.nbrPlace - room.placesOcc >0));
        }
      });

      console.log(this.roomlist)
      this.MixteisChecked=false;
    }
  }
}

