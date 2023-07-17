import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UntypedFormGroup, UntypedFormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FlightHelperService } from 'src/app/components/helper/flight/flight-helper.service';
import { Block } from 'src/app/components/models/Block';
import { Room } from 'src/app/components/models/Room';
import { BlockService } from 'src/app/components/shared/services/block.service';
import { RoomService } from 'src/app/components/shared/services/room.service';

@Component({
  selector: 'app-flight-booking',
  templateUrl: './flight-booking.component.html',
  styleUrls: ['./flight-booking.component.css']
})
export class FlightBookingComponent implements OnInit{
  rooms : Room[]=[];
  room : Room = new Room();
  blocklist!:Block[];
  roomid!:number;
  constructor(private blockService:BlockService,private roomService : RoomService,
    private router : Router){}
    ngOnInit(): void {
      this.blockService.getBlock().subscribe(rooms => {
        this.blocklist = rooms;

      });
      console.log(this.blocklist)

    }
    public onAddRoom():void{
      this.roomService.ajouterRoom(this.room,this.roomid).subscribe(res => {
        if (res) {

            console.log({severity: 'success', summary: 'Sucess', detail: 'Operation effectued'});



          }
          else{
            (error: HttpErrorResponse) => {
              console.log({severity: 'error', summary: 'Error', detail: 'Operation not effectued'});
              console.log(error);
            }
          }


      },

    );
    this.roomService.assignRoomBlock(this.room.idR,this.roomid).subscribe();
    this.router.navigate(['/car-details/1'])

  }
  showAvailableRoomsByBlocType() {

      this.blockService.getBlock().subscribe(rooms => {
        this.blocklist = rooms;

      });
      console.log(this.blocklist)




  }
}
