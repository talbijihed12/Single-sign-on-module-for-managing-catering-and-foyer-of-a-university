import { Component, OnInit } from '@angular/core';
import { AcademicHomeSubService } from '../../academic-home-sub.service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import moment from 'moment';
import { Abonnement_F } from 'src/app/components/models/Abonnement_F';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {

  liste: any[] | undefined;
  roomId:any
  foyerType:any
  block: Abonnement_F = new Abonnement_F();
  constructor(private monService: AcademicHomeSubService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getsubs()

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
    console.log('roooom', this.roomId)

    console.log('fottt', this.foyerType)
    const body:Abonnement_F= {
id:0,
  dateStart: "2023-05-08T17:51:10.516Z",
  dateEnd: "2023-05-08T17:51:10.516Z",
  expired: false,
  amount: 0,
  username: "jihed",
      idR : parseInt(this.roomId),
      typeFoyer: this.foyerType
    }
    console.log(body)
    this.monService.addSubscription(this.block).subscribe((res:any)=> {
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





	private getDismissReason(reason: any): string {
		if (reason === ModalDismissReasons.ESC) {
			return 'by pressing ESC';
		} else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
			return 'by clicking on a backdrop';
		} else {
			return `with: ${reason}`;
		}
	}
}
