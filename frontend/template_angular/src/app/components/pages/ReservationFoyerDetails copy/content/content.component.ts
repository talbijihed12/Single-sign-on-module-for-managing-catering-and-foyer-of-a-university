import { Component, OnInit } from '@angular/core';
import { SingleReservationFoyerCosumerService } from 'src/app/Services/SingleReservationService/SingleReservationFoyer-cosumer.service';

import { ActivatedRoute, Router } from '@angular/router';
import { ReservationFoyer } from 'src/app/components/data/SingleReservationFoyer/ReservationFoyer';
import { FormControl } from '@angular/forms';
import { Room } from 'src/app/components/models/Room';
import { RoomService } from 'src/app/components/shared/services/room.service';
import { HttpClient } from '@angular/common/http';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

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
  reservationFoyer! : ReservationFoyer;
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
  imageUrl!:SafeUrl;
  list: number[] = [];
  roomsMales!:Room[];
  roomsFemales!:Room[];
  roomsMixte!:Room[];
  showForm:Boolean=false;
  constructor(private sanitizer:DomSanitizer,private http:HttpClient,private roomservice:RoomService,private router: Router,private route: ActivatedRoute,private singleReservationFoyerCosumerService:SingleReservationFoyerCosumerService) { }
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.id = Number(params.get('id'));
       this.singleReservationFoyerCosumerService.getReservationFoyerById(this.id).subscribe(
         data => {
           this.reservationFoyer = data;

       console.log(this.id); // do something with the ID
       return this.reservationFoyer;
     });

   })
   this.roomservice.RoomAvailable("RESIDANT_Student","MALE").subscribe(rooms => {
    this.roomsMales = rooms;
    if (this.roomsMales) {
      this.roomsMales = this.roomsMales.filter(room => (room.nbrPlace - room.placesOcc >0));
    }
  });
  this.roomservice.RoomAvailable("RESIDANT_Student","FEMALE").subscribe(rooms => {
    this.roomsFemales = rooms;
    if (this.roomsFemales) {
      this.roomsFemales = this.roomsFemales.filter(room => (room.nbrPlace - room.placesOcc >0));
    }
  });
  this.roomservice.RoomAvailable("RESIDANT_Student","MIXTE").subscribe(rooms => {
    this.roomsMixte = rooms;
    if (this.roomsMixte) {
      this.roomsMixte = this.roomsMixte.filter(room => (room.nbrPlace - room.placesOcc >0));
    }
  });

}
GetSingleReservationFoyerById() {

}
cancelReservation() {
  console.log("before");
this.singleReservationFoyerCosumerService.cancelReservationFoyerGroup(this.id).subscribe();
this.router.navigate(['/ReservationFoyers']);
console.log("after");
}
deleteReservation() {
this.singleReservationFoyerCosumerService.deleteReservationFoyer(this.id).subscribe();
this.router.navigate(['/ReservationFoyers']);
}
UpdateReservationFoyer() {
  if (this.newdd) {this.reservationFoyer.dateStart=this.newdd}
  if (this.newdf) {this.reservationFoyer.dateStart=this.newdf}
  if (this.newnbrfemale) {this.reservationFoyer.nbrFemale=this.newnbrfemale}
  if (this.newnbrmale) {this.reservationFoyer.nbrMale=this.newnbrmale}
  if (this.newnbrmixte) {this.reservationFoyer.nbrMixte=this.newnbrmixte}
  if (this.newroomid) {this.reservationFoyer.nameGrp=this.newroomid}
  this.singleReservationFoyerCosumerService.updateReservationFoyer(this.reservationFoyer,this.id).subscribe();
}
approveReservationFoyer(){
  this.singleReservationFoyerCosumerService.approveReservationFoyer(this.id,this.list).subscribe();
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
onButtonClick(id: number) {
  const url = 'http://your-api-url.com/get-image';
  this.http.get('http://localhost:8095/rf/getQRCodeWithUsername/' + this.id, { responseType: 'blob' })
    .subscribe(response => {
      const objectURL = URL.createObjectURL(response);
      this.imageUrl = this.sanitizer.bypassSecurityTrustUrl(objectURL);
    });
    setTimeout(() => {
      this.imageUrl = '';
    }, 5000);
}
}
