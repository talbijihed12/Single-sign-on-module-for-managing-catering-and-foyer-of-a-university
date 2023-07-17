import { Component, OnInit } from '@angular/core';
import data from "../../../data/whyus.json";
import { ClaimService } from 'src/app/components/shared/services/claim.service';
import { Claim } from 'src/app/components/models/Claim';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.css']
})
export class AboutUsComponent implements OnInit {
  p: number = 1;
  public claims: Claim[] = [];
  public claim: Claim = new Claim();
 


  constructor(private claimService : ClaimService) {}
  ngOnInit(): void {
    this.getReclamationsArchiver();
  }
  public getReclamationsArchiver(): void {
    this.claimService.getAllClaimsByAdmin().subscribe(
      (response) => {
        this.claims = response.filter(rec => rec.status == 'ARCHIVER');
        this.claims.forEach(rec => rec.dateDiff = new Date(rec.dateDiff));

      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );

  }

}
