import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Claim } from 'src/app/components/models/Claim';
import { ClaimService } from 'src/app/components/shared/services/claim.service';
import { FileUploadService } from 'src/app/components/shared/services/file-upload.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
  attachements!: any[];
  id!: string;
  claims! : Claim;
  public claimss : Claim[] = [];

  constructor(private claimService: ClaimService, private route: ActivatedRoute, private fileUploadService: FileUploadService,) {}

  

  ngOnInit(): void {
    this.route.params.subscribe(params => {
    this.id = params['id'];
    console.log(this.id); // This will log the id to the console
  });
    this.claimService.getById(this.id).subscribe({
    next: (data)=>this.claims=data, //code : 200 ,204
    error : (error)=>console.log(error),// code : 500 ,404
    complete : ()=>console.log("I m finshsing")
  })
  this.getReclamations();

   
  }




  
 public getReclamations(): void{
   this.claimService.getReclamations().subscribe(
     (response) =>{
       this.claimss = response;
       console.log(this.claims);
     },
     (error: HttpErrorResponse) =>{
       console.log(error);
     }
   );

 }

  public annuler(claim: Claim){
  claim.status= 'ANNULER';
  this.claimService.changeStatus(claim).subscribe(res => {
      if (res.success) {
        console.log({severity: 'success', summary: "Success", detail: "Operation effectueded"});
    this.getReclamations();
      } else {
        console.log({severity: 'warn', summary: "Error", detail: "Operation not effectueded"});
      }

    },
    (error: HttpErrorResponse) => {
      console.log({severity: 'error', summary: 'Erreur', detail: 'Opération non effectuée'});
      console.log(error);
    }
  );
}




  downloadFile(fileName:any) {
  this.fileUploadService.downloadFile(fileName).subscribe(res => {
    console.log(res);
  }, ex=> console.log(ex)) ;
  }
}


 
