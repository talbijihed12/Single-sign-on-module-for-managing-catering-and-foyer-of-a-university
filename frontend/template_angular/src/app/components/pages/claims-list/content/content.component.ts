import { HttpErrorResponse } from '@angular/common/http';
import { Component,OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Claim } from 'src/app/components/models/Claim';
import { ClaimService } from 'src/app/components/shared/services/claim.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent  implements OnInit {
  p: number = 1;
  claims: Claim[] = [];
  //claims : Claim[] = [];
  type!:any;
  products!: Claim;
  types!: string[];
  selectedType!: string;


  
  constructor(private claimService : ClaimService) {}


   ngOnInit(){
     this.getReclamations();
     this.getClaimsByType();
    //findById reclamation
  }

  onSelectType() {
    this.claimService.getClaimsByType(this.selectedType)
    .subscribe(claims => this.claims = claims);
  }





  /*onTypeSelect() {
    this.claimService.getClaimsByType(this.selectedType)
    .subscribe(claims => this.claims = claims);
  }*/

  public getReclamations(): void{
    this.claimService.getReclamations().subscribe(
      (response) =>{
        this.claims = response;
        console.log(this.claims);
      },
      (error: HttpErrorResponse) =>{
        console.log(error);
      }
    );

  }

  public getClaimsByType(): void{
    this.claimService.getClaimsByType(this.type).subscribe(
      (response) =>{
        this.claims = response;
        console.log(this.claims);
      },
      (error: HttpErrorResponse) =>{
        console.log(error);
      }
    );

  }

  filterByCategory(claims: Claim[], category: any): Claim[] {
    if (category) {
      return claims.filter((product) => product.typeReclamation === category);
    } else {
      return claims;
    }
  }


  
}