import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Claim } from 'src/app/components/models/Claim';
import { ClaimService } from 'src/app/components/shared/services/claim.service';
import { FileUploadService } from 'src/app/components/shared/services/file-upload.service';
import { OverlayPanel } from 'primeng/overlaypanel';



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
  public claim: Claim = new Claim();
  @ViewChild('overlayPanel') overlayPanel!: OverlayPanel;
  public showModal = false;




  constructor(private claimService: ClaimService, private route: ActivatedRoute, private fileUploadService: FileUploadService, private router:Router, private messageService:MessageService) {}



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

  public openModal(): void {
    this.showModal = true;
  }




  /*onSubmit() :void{

    this.claimService.retourner(this.claim).subscribe(
      (res) => {
        if (res.success) {
          this.showModal = false;
          this.messageService.add({severity: 'success', summary: res.message, detail: res.detail});
          this.getReclamations();
        } else {
          this.messageService.add({severity: 'warn', summary: res.message, detail: res.detail});
        }
      },
      (error: HttpErrorResponse) => {
        this.messageService.add({severity: 'error', summary: 'Erreur', detail: 'Opération non effectuée'});

        console.log(error);
      });



  }*/





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
  resolve(claim: Claim) {

    //if(confirm("Are you sure to resolve "+claim)) {

      this.claimService.resolve(claim).subscribe(res => {
        if (res.success||confirm("Are you sure to resolve ")) {

          console.log({severity: 'success', summary: res.message, detail: res.detail});
          this.router.navigate(['/claim-list-admin']);

          //this.getAllReclamations();
        } else {
          console.log({severity: 'warn', summary: res.message, detail: res.detail});

        }

      },
      (error: HttpErrorResponse) => {
        console.log({severity: 'error', summary: 'Erreur', detail: 'Opération non effectuée'});
        console.log(error);
      }
    );
    }
    retourner(claim: Claim) {

      this.claimService.retourner(claim).subscribe(
        (res) => {
          if (res.success) {
            this.messageService.add({severity: 'success', summary: res.message, detail: res.detail});
            //this.getReclamations();
            this.router.navigate(['/claim-list-admin']);
          } else {
            this.messageService.add({severity: 'warn', summary: res.message, detail: res.detail});
          }
        },
        (error: HttpErrorResponse) => {
          this.messageService.add({severity: 'error', summary: 'Erreur', detail: 'Opération non effectuée'});

          console.log(error);
        });
      }




    closeOverlay() {
      this.claim = new Claim();
    }

    clickRetour(reclamation: Claim) {
      this.claim = reclamation;
    }
    /*retourner() :void{

          this.claimService.retourner(this.claim).subscribe(
            (res) => {
              if (res.success) {
                this.overlayPanel.hide();
                this.messageService.add({severity: 'success', summary: res.message, detail: res.detail});
                this.getReclamations();
              } else {
                this.messageService.add({severity: 'warn', summary: res.message, detail: res.detail});
              }
            },
            (error: HttpErrorResponse) => {
              this.messageService.add({severity: 'error', summary: 'Erreur', detail: 'Opération non effectuée'});

              console.log(error);
            });



        }*/


        getAllAttachements(id:any): void {
          this.fileUploadService.getFleByClaim(id).subscribe(data => {
            this.attachements = data;
          }, ex => console.log(ex));
          }


}











