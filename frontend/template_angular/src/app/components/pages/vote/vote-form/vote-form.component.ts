import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgxConfirmBoxService } from 'ngx-confirm-box';
import { Vote } from 'src/app/components/models/Vote';
import { VoteService } from 'src/app/components/shared/services/vote/vote.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-vote-form',
  templateUrl: './vote-form.component.html',
  styleUrls: ['./vote-form.component.css']
})
export class VoteFormComponent implements OnInit {
   closeResult: string = '';
  public votes: Vote[] = [];
  //vote = new Vote();

  //vote!: Vote;
  //formBuilder: any;
  //formData: FormData = new FormData();
  constructor(private voteservice:VoteService, private route: ActivatedRoute,private confirmBox: NgxConfirmBoxService,private modalService: NgbModal,private _router:Router) { }
  //editvot = new FormGroup({
   // voteType: new FormControl('')
  //})

 ngOnInit(): void {
    this.voteservice.getVotes().subscribe({
      next: (data)=>this.votes=data, //code : 200 ,204
      error : (error)=>console.log(error),// code : 500 ,404
      complete : ()=>console.log("I m finshsing")
    })
 }

  deleteVote(voteId: any) {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, cancel it !',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        console.log('i m deleting', voteId)
        this.voteservice.removevote(voteId).subscribe({
          next: (data) => console.log(data), //code : 200 ,204
          error: (error) => console.log(error),// code : 500 ,404
          complete: () => console.log("I m finshsingggg")
        })

        location.reload();

      }

      else if (
        /* Read more about handling dismissals below */
        result.dismiss === Swal.DismissReason.cancel
      ) {
        swalWithBootstrapButtons.fire(
          'Cancelled',
          'Your vote is safe :)',
          'error'
        )
      }
    })

  }
  editvote(voteId: any,vote: Vote) {
   console.log('im editing',vote)
    this.voteservice.updatevote(vote).subscribe({
        next: ()=>this._router.navigateByUrl('/listofvote'),
        error: (error)=>console.log(error),
        complete:()=>console.log("I m finishing")
      })
    console.log('im done', voteId)
  location.reload();}

  open(content:any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }
   private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
   }

  voteCount:number = 0;
    desaffecterVote(voteId: number) {
     let payload = {
      voteType: "DOWNVOTE",
      username: 'chiha',
      id: voteId
    }
    console.log('mealID', voteId)
    this.voteservice.desaffecterVotee(payload).subscribe((response) => { this.voteCount -= 1 })
    console.log('iam in desaffecterVote', this.voteCount)
    }

  nbrofvote(votetype:any) {
    this.voteservice.nbrofvote(votetype)
  }







}
