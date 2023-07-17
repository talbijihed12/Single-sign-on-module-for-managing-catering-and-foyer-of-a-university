import { HttpErrorResponse, HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SnotifyService } from 'ng-snotify';
import { Claim } from 'src/app/components/models/Claim';
import { ClaimService } from 'src/app/components/shared/services/claim.service';
import { FileUploadService } from 'src/app/components/shared/services/file-upload.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
  claim= new  Claim();
  selectedFiles!: FileList;
  fileTxt = 'Choose file';
  constructor(
    private activatedRoute: ActivatedRoute,
    private claimService: ClaimService,
    private router: Router,
    private snotifyService: SnotifyService,
    private fileUploadService: FileUploadService) { }

  ngOnInit(): void {
    const idRec = this.activatedRoute.snapshot.paramMap.get('id');

    if (idRec) {
      this.claimService.getById(idRec).subscribe(res => {
        this.claim = res;
        console.log(res)
      }, ex => console.log(ex));
    }
  }

  public onEditClaim(): void {

    this.claimService.modifier(this.claim).subscribe(res => {
        if (res) {
          if(this.selectedFiles){
            this.upload(this.claim.id);
          } else  {
            this.snotifyService.success('Your action was successful', 'Success')
            //this.messageService.add({severity: 'success', summary: res.message, detail: res.detail});

            this.router.navigate(['/claim-list']);
          }

        }


      },
      (error: HttpErrorResponse) => {
        this.snotifyService.error('Your action was incceptable', 'Error')

        //this.messageService.add({severity: 'error', summary: 'Erreur', detail: 'Opération non effectuée'});
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


  upload(idReclamation:any) {

    const formData = new FormData();
    Array.from(this.selectedFiles).forEach(file => {
      formData.append('files', file);
    });
    this.fileUploadService.upload(formData, idReclamation).subscribe(
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

  }


}
