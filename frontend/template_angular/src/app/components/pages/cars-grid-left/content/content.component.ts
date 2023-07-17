import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CarHelperService } from 'src/app/components/helper/cars/car-helper.service';
import { Block } from 'src/app/components/models/Block';
import { BlockService } from 'src/app/components/shared/services/block.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {

  public  blocks : Block[] = [];

  block !: any;


  constructor(private blockService : BlockService,private route: ActivatedRoute,
    private _routerUp: Router) {
      this.block={
        sexe : null,
        type : null,
      }
    }

  ngOnInit(){
    // const idB = this.route.snapshot.params.id;
    // this.blockService.getBlock(idB).subscribe(
    //   data=>{
    //     this.block=data;
    //   },
    //   error=>console.log(error)
    // )

  }
  updateBlock(){
    const idB = this.route.snapshot.params.id;
    this.blockService.updateBlock(this.block, idB).subscribe(data =>{
      console.log(data);


    },
    error => console.log(error)

    );
    this._routerUp.navigate(['/blog-grid'])


  }


}
