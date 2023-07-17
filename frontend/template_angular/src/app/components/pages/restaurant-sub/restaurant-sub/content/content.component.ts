import { Component, OnInit } from '@angular/core';
import { Abonnement_R } from 'src/app/components/models/Abonnement_R';
import { RestaurantServiceService } from '../../restaurant-service.service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import moment from 'moment';
import { PointMerci } from 'src/app/components/models/pointMerci';
import { HttpClient } from '@angular/common/http';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
  liste!: any[] ;
  typeRestaurant:any
  block: Abonnement_R = new Abonnement_R();
  Jeton: PointMerci = new PointMerci();
  nbrJeton!:number;
  imageUrl!:SafeUrl;
  nbJeton!:number;
  aa!:number;
  constructor(private sanitizer:DomSanitizer,private http:HttpClient,private monService: RestaurantServiceService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getsubs();
  }
  getsubs() {
    this.monService.getListe().subscribe((liste : any) => {

      liste.forEach((e:any )=> {
        e.dateStart = moment(new Date(e.dateStart)).format('DD/MM/YYYY')
        e.dateEnd = moment(new Date(e.dateEnd)).format('DD/MM/YYYY')
      })
      this.liste = liste;
    });
  }
  saveSub() {


    console.log('fottt', this.typeRestaurant)
    const body:Abonnement_R= {
id:0,
  dateStart: "2023-05-08T17:51:10.516Z",
  dateEnd: "2023-05-08T17:51:10.516Z",
  expired: false,
  amount: 0,
  username: "jihed",
      typeRestaurant: this.typeRestaurant,
      nbJeton:0,
    }
    console.log(body)
    this.monService.addSubRestaurant(this.block).subscribe((res:any)=> {
      //console.log('xxxxxxxxxxxxxxxxxx', res)
      alert("Subscription added successfully")
      this.getsubs()
    },(err)=> {
      console.log(err)
    })
  }
  remove(id:any) {
    this.monService.deleteSubscription(id).subscribe((res)=> {
      alert("Abonnement a été supprimé avec succès")
    },(err)=> {
      console.log(err)
    })
  }
  addSubscription(content:any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then(
			(result) => {
				this.closeResult = `Closed with: ${result}`;
			},
			(reason) => {
				this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
			},
		);
  }
  closeResult = '';

  addJeton() {
    this.monService.updatejeton(this.aa).subscribe((res)=> {
      alert("Abonnement a été supprimé avec succès")
    },(err)=> {
      console.log(err)
    })

  }


  private getDismissReason(reason: any): string {
		if (reason === ModalDismissReasons.ESC) {
			return 'by pressing ESC';
		} else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
			return 'by clicking on a backdrop';
		} else {
			return `with: ${reason}`;
		}
	}
  onButtonClick(username:string,nbJeton: number) {
    const url = 'http://your-api-url.com/get-image';
    this.http.get('http://localhost:8097/abonn/getQRCodeWithUsername/' +username+'/'+this.nbrJeton, { responseType: 'blob' })
      .subscribe(response => {
        const objectURL = URL.createObjectURL(response);
        this.imageUrl = this.sanitizer.bypassSecurityTrustUrl(objectURL);
      });
      setTimeout(() => {
        this.imageUrl = '';
      }, 5000);
  }
}
