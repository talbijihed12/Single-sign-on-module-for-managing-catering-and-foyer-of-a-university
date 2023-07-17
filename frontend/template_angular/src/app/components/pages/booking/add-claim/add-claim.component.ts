import { HttpErrorResponse, HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Claim } from 'src/app/components/models/Claim';
import { ClaimService } from 'src/app/components/shared/services/claim.service';
//import { FileUploadService } from 'src/app/components/shared/services/file-upload.service';
import { FileUploadService } from 'src/app/components/shared/services/file-upload.service';


@Component({
  selector: 'app-add-claim',
  templateUrl: './add-claim.component.html',
  styleUrls: ['./add-claim.component.css']
})
export class AddClaimComponent implements OnInit {
  claims: Claim[] = [];
  claim: Claim = new Claim();
  selectedFiles!: FileList;
  fileTxt = 'Choose file';

  constructor(private reclamationService: ClaimService,
              private router: Router,
              private fileUploadService: FileUploadService) {
  }

  ngOnInit(): void {


  }

  public onAddClaim(): void {
    this.reclamationService.addReclamation(this.claim).subscribe(res => {
        if (res) {

          if(this.selectedFiles){
            this.upload(res.id);
          } else  {
            //this.toastr.success('Successful, world!', 'Toastr fun!');

            console.log({severity: 'success', summary: 'Succès', detail: 'Opération éffectuée'});

            this.router.navigate(['/claim-list']);
          }
        }


      },
      (error: HttpErrorResponse) => {
        console.log({severity: 'error', summary: 'Erreur', detail: 'Opération non effectuée'});
        console.log(error);
      }
    );

  }

  selectFile(event:any) {
    this.selectedFiles = event.target.files;
    this.fileTxt = '';
    Array.from(this.selectedFiles).forEach(file => {
      this.fileTxt += (file.name) + ',';
    });

  }


  upload(idClaim:any) {

    const formData = new FormData();
    Array.from(this.selectedFiles).forEach(file => {
      formData.append('files', file);
    });
    this.fileUploadService.upload(formData, idClaim).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {

        } else if (event instanceof HttpResponse) {
          console.log(event.body);
              if (event.body.success) {
                console.log({severity: 'success', summary: event.body.message, detail: event.body.detail});
                this.router.navigate(['/claim-list']);
              } else {
                console.log({severity: 'warn', summary: event.body.message, detail: event.body.detail});
              }
        }
      },
      err => {
        console.log(err);
      });

    //this.selectedFiles = undefined;
  }
  /*claims: Claim[] = [];
  claim: Claim = new Claim();
    
  
    constructor(private reclamationService: ClaimService,
                private router: Router,private messageService: MessageService) {
    }
  
    ngOnInit(): void {
    }
  
    public onAddClaim(): void {
      this.reclamationService.addReclamation(this.claim).subscribe(res => {
          if (res) {
              this.messageService.add({severity: 'success', summary: 'Succès', detail: this.msg});
  
              this.router.navigate(['/claim-list']);
            }
          
  
  
        },
        (error: HttpErrorResponse) => {
          this.messageService.add({severity: 'error', summary: 'Erreur', detail: 'Opération non effectuée'});
          console.log(error);
        }
      );
  
    }*/
}
  




    

              
            
  
    
  
    
  
  


 