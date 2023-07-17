import { Component, OnInit } from '@angular/core';
import { SingleReservationFoyerCosumerService } from 'src/app/Services/SingleReservationService/SingleReservationFoyer-cosumer.service';

import { ActivatedRoute, Router } from '@angular/router';
import { SingleReservationFoyer } from 'src/app/components/data/SingleReservationFoyer/SingleReservationFoyer';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
  singleReservationFoyer! : SingleReservationFoyer;
  srf! : SingleReservationFoyer;
  id!:number;
  newdd!:Date;
  newdf!:Date;
  imageUrl!:SafeUrl;
  newroomid!:number;
  constructor(private sanitizer :DomSanitizer,private http:HttpClient,private router: Router,private route: ActivatedRoute,private singleReservationFoyerCosumerService:SingleReservationFoyerCosumerService) { }
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.id = Number(params.get('id'));
       this.singleReservationFoyerCosumerService.getProductById(this.id).subscribe(
         data => {
           this.singleReservationFoyer = data;

       console.log(this.id); // do something with the ID
       return this.singleReservationFoyer;
     });

   })

}
GetSingleReservationFoyerById() {

}
cancelReservation() {
  console.log("before");
this.singleReservationFoyerCosumerService.cancelReservationFoyer(this.id).subscribe();
this.router.navigate(['/gallery']);
console.log("after");
}
deleteReservation() {
this.singleReservationFoyerCosumerService.deleteProduct(this.id).subscribe();
this.router.navigate(['/gallery']);
}
UpdateSingleReservationFoyer() {
  if (this.newdd) {this.singleReservationFoyer.dateStart=this.newdd}
  if (this.newdf) {this.singleReservationFoyer.dateStart=this.newdf}
  if (this.newroomid) {this.singleReservationFoyer.idRoom=this.newroomid}
  this.singleReservationFoyerCosumerService.updateProduct(this.singleReservationFoyer,this.id).subscribe();
}
onButtonClick(id: number) {
  const url = 'http://your-api-url.com/get-image';
  this.http.get('http://localhost:8095/srf/getQRCodeWithUsername/' + this.id, { responseType: 'blob' })
    .subscribe(response => {
      const objectURL = URL.createObjectURL(response);
      this.imageUrl = this.sanitizer.bypassSecurityTrustUrl(objectURL);
    });
    setTimeout(() => {
      this.imageUrl = '';
    }, 5000);
}
}
