import { Component, OnInit } from '@angular/core';
import { SingleReservationFoyerCosumerService } from 'src/app/Services/SingleReservationService/SingleReservationFoyer-cosumer.service';

import { ActivatedRoute, Router } from '@angular/router';
import { ReservationRestaurant } from 'src/app/components/data/SingleReservationFoyer/ReservationRestaurant';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
  roomCountControl = new FormControl(0);
  roomOptions = [
    { id: 1, name: 'Room 1' },
    { id: 2, name: 'Room 2' },
    { id: 3, name: 'Room 3' },
    // add more rooms as needed
  ];
  selectedRooms: number[] = [];
  reservationFoyer! : ReservationRestaurant;
  selectedRoomId!: number;
  numberofrooms!:number;
  id!:number;
  newdd!:Date;
  newdf!:Date;
  newnbrmale!:number;
  userInput: string = '';
  newnbrfemale!:number;
  newnbrmixte!:number;
  newroomid!:string;
  x!:number;
  list: number[] = [];
  showForm:Boolean=false;
  constructor(private router: Router,private route: ActivatedRoute,private singleReservationFoyerCosumerService:SingleReservationFoyerCosumerService) { }
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.id = Number(params.get('id'));
       this.singleReservationFoyerCosumerService.getReservationRestaurantById(this.id).subscribe(
         data => { 
           this.reservationFoyer = data;
           
       console.log(this.id); // do something with the ID
       return this.reservationFoyer;
     });
     
   })
  
}
GetSingleReservationFoyerById() {

}
cancelReservation() {
  console.log("before");
this.singleReservationFoyerCosumerService.cancelReservationRestaurantGroup(this.id).subscribe();
this.router.navigate(['/ReservationFoyers']);
console.log("after");
}
deleteReservation() {
this.singleReservationFoyerCosumerService.deleteReservationRestaurant(this.id).subscribe();
this.router.navigate(['/ReservationFoyers']);
}
UpdateReservationFoyer() {
  if (this.newdd) {this.reservationFoyer.dateStart=this.newdd}
  if (this.newdf) {this.reservationFoyer.dateStart=this.newdf}
  if (this.newnbrfemale) {this.reservationFoyer.nbrPerson=this.newnbrfemale}
  if (this.newroomid) {this.reservationFoyer.nameGrp=this.newroomid}
  this.singleReservationFoyerCosumerService.updateReservationRestaurant(this.reservationFoyer,this.id).subscribe();
}
approveReservationFoyer(){
  this.singleReservationFoyerCosumerService.approveReservationRestaurant(this.id,this.list).subscribe();
}
onSelectRooms(): void {
  const selectedCount = this.numberofrooms;
  // select the first `selectedCount` rooms
  this.selectedRooms = this.roomOptions.slice(0, selectedCount).map(room => room.id);
}
addItem(y:number) {
  console.log(y);
  this.list.push(y);
  
}
clearInput() {
  this.userInput = '';
}
}
