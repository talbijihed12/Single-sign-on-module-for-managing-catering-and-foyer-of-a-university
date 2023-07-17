import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { BlogHelperService } from 'src/app/components/helper/blog/blog-helper.service';
import { Block } from 'src/app/components/models/Block';
import { BlockService } from 'src/app/components/shared/services/block.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
  public  blocks : Block[] = [];
  searchtxt !: string;

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

  }
  search(){
    this.blocks=this.blocks.filter((x)=>x.sexe.match (this.searchtxt))
  }
  // search1(){
  //    this.blocks=this.blocks.filter((x)=>x.type.match(this.searchtxt))

  // }
}
