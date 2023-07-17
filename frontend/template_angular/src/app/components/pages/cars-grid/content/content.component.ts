import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
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


  constructor(private BlockService : BlockService) {}

  ngOnInit(){
     this.getBlock();
  }
  public getBlock(): void{
    this.BlockService.getBlock().subscribe(
      (response) =>{
        this.blocks = response;
        console.log(this.blocks);
      },
      (error: HttpErrorResponse) =>{
        console.log(error);
      }
    );

  }
  deleteBlock(idB: any) {
    this.BlockService.deleteBlock(idB).subscribe(() => {
      this. getBlock();
    });

  }}
