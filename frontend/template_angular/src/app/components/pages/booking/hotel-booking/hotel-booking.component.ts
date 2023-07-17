import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UntypedFormGroup, UntypedFormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HotelHelperService } from 'src/app/components/helper/hotel/hotel-helper.service';
import { Block } from 'src/app/components/models/Block';
import { BlockService } from 'src/app/components/shared/services/block.service';

@Component({
  selector: 'app-hotel-booking',
  templateUrl: './hotel-booking.component.html',
  styleUrls: ['./hotel-booking.component.css']
})
export class HotelBookingComponent implements OnInit {
  blocks: Block[] = [];
  block: Block = new Block();


    constructor(private blockService: BlockService,
                private router: Router) {
    }

    ngOnInit(): void {
    }

    public onAddBlock(): void {
      this.blockService.AddBlock(this.block).subscribe(res => {
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
      this.router.navigate(['/blog-grid'])

    }
}
