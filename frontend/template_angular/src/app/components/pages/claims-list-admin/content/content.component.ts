import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import { ConfirmationService, MessageService } from 'primeng/api';
import { Claim } from 'src/app/components/models/Claim';
import { ClaimService } from 'src/app/components/shared/services/claim.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
  p: number = 1;
  claims: Claim[] = [];
  //claims : Claim[] = [];
  claim!: Claim;
  id!:any;
  searchQuery!:any;



  constructor(private claimService : ClaimService, private router: Router) {}

  ngOnInit(){
     this.getAllReclamations();
  }
  private getAllReclamations() {
    this.claimService.getAllClaimsByAdmin().subscribe(
      (response) => {
        this.claims = response.filter(rec => rec.status !== 'ARCHIVER');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

 /* public getClaimsByType(): void{
    this.claimService.getClaimsByType(this.type).subscribe(
      (response) =>{
        this.claims = response;
        console.log(this.claims);
      },
      (error: HttpErrorResponse) =>{
        console.log(error);
      }
    );

  }*/
    onSearch() {
      const filteredClaims = this.claims.filter(claim =>
      claim.title.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
      claim.description.toLowerCase().includes(this.searchQuery.toLowerCase())

    );
    if (filteredClaims.length === 0) {
      this.claims = this.claims;
    } else {
      this.claims = filteredClaims;
    }
  }


  filterByCategory(claims: Claim[], category: any): Claim[] {
    if (category) {
      return claims.filter((product) => product.typeReclamation === category);
    } else {
      return claims;
    }
  }

  archiver(claim: Claim) {

    this.claimService.archiver(claim).subscribe(res => {
        if (res.success) {

          console.log({severity: 'success', summary: res.message, detail: res.detail});
          //this.getAllReclamations();
        } else {
          console.log({severity: 'warn', summary: res.message, detail: res.detail});
          this.router.navigate(['/historics']);

        }

      },
      (error: HttpErrorResponse) => {
        console.log({severity: 'error', summary: 'Erreur', detail: 'Opération non effectuée'});
        console.log(error);
      }
    );
  }




}
