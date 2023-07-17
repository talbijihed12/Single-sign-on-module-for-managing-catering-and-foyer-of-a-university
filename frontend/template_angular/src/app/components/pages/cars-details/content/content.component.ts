import { RouterModule } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CarHelperService } from 'src/app/components/helper/cars/car-helper.service';
import { Block } from 'src/app/components/models/Block';
import { Room } from 'src/app/components/models/Room';
import { BlockService } from 'src/app/components/shared/services/block.service';
import { RoomService } from 'src/app/components/shared/services/room.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
  public  rooms : Room[] = [];
  public blocks : Block[]=[];
  searchtxt !: string;
nbr!: number;
nbr1!: number;


  constructor(private roomService : RoomService,
    private blockService : BlockService) {}

  ngOnInit(){
     this.getRoom();

     }


  public getRoom(): void{
    this.roomService.getRoom().subscribe(
      (response) =>{
        this.rooms = response;
        console.log(this.rooms);
      },
      (error: HttpErrorResponse) =>{
        console.log(error);
      }
    );

  }
  deleteRoom(idR: any) {
    this.roomService.deleteRoom(idR).subscribe(() => {
      this. getRoom();
    });

  }
  BookPlace(idR : any){
    this.rooms.map((rooms)=>{
      if(rooms.idR.toExponential(idR)){
        rooms.placesOcc=rooms.placesOcc+1;
      }
    })
  }
  UnBookPlace(idR : any){
    this.rooms.map((rooms)=>{
      if(rooms.idR.toExponential(idR)){
        rooms.placesOcc=rooms.placesOcc-1;
      }
    })
  }
  search(){
    this.rooms=this.rooms.filter((x)=>x.floor.match(this.searchtxt))
  }
Bilan(){
  this.nbr=0;
  for(let i=0; i<this.rooms.length; i++)
  if(this.rooms[i].placesOcc==0) this.nbr++;
}
Bilan1(){
  this.nbr1=0;
  for(let i=0; i<this.rooms.length; i++)
  if(this.rooms[i].placesOcc!=0) this.nbr1++;
}
}
