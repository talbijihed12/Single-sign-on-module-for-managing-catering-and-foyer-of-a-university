import { Block } from './../../../models/Block';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
//import { error } from 'console';
import { CarHelperService } from 'src/app/components/helper/cars/car-helper.service';
import { Room } from 'src/app/components/models/Room';
import { RoomService } from 'src/app/components/shared/services/room.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {

  public  rooms : Room[] = [];
  room!: any;



  constructor(private roomService : RoomService,private route: ActivatedRoute,
    private _routerUp : Router) {
    this.room={

  number :null,
  floor : null,
  nbrPlace : null ,
  placesOcc : null,
  cleaningRoom :null,


    }
  }

  ngOnInit(){

    /*this.route.paramMap.subscribe(params => {
      this.idR = +params.get('idR');
      this.updateRoom(this.room, this.idR);
    });*/

  }
  updateRoom(){
    const idR = this.route.snapshot.params.id ;
    this.roomService.updateRoom(this.room,idR).subscribe(data => {
      console.log(data);
    },
    error => console.log(error)
    );

    this._routerUp.navigate(['/car-details/1'])
  }

}
