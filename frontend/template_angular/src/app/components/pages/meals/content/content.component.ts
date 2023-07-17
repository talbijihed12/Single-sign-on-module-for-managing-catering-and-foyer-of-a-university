import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Meal } from 'src/app/components/models/Meals';
import { Menu } from 'src/app/components/models/Menu';
import { MealService } from 'src/app/components/shared/services/meal/meal.service';
import Swal from 'sweetalert2';
import { MenuService } from './../../../shared/services/menu/menu.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
  closeResult: string = '';
  public meals: Meal[] = [];
  meal: Meal = new Meal();
  menuItems: Menu[]=[];
  id: any;
  menu: Menu = new Menu();



    constructor(private menuService :MenuService, private mealService: MealService,private _router:Router,private modalService: NgbModal,private route: ActivatedRoute) { }


   ngOnInit(): void {
    this.mealService.getmeal().subscribe({
      next: (data) => this.meals = data, //code : 200 ,204
      error: (error) => console.log(error),// code : 500 ,404
      complete: () => console.log("I m finshsing")
    })

     this.menuService.getmenus().subscribe({
      next: (data) => this.menuItems = data, //code : 200 ,204
      error: (error) => console.log(error),// code : 500 ,404
      complete: () => console.log("I m finshsing")
    })
   }
   deleteMeal(voteId: any) {
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
      cancelButtonText: 'No, cancel!',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        console.log('i m deleting', voteId)
        this.mealService.deleteMealById(voteId).subscribe({
          next: (data) => console.log(data), //code : 200 ,204
          error: (error) => console.log(error),// code : 500 ,404
          complete: () => console.log("I m finshsingggg")
        }
        )

         location.reload();

      } else if (
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

 editmeal(id: any,meal: Meal) {
   console.log('im editing',meal)
    this.mealService.updatemeal(id,meal).subscribe({
        next: ()=>this._router.navigateByUrl('/listofvote'),
        error: (error)=>console.log(error),
        complete:()=>console.log("I m finishing")
      })
    console.log('im done', id)
   location.reload();
 }

  addmeal() {
    console.log('im added');
    this.mealService.addmeal(this.meal).subscribe({
      next: ()=>this._router.navigateByUrl('/meals'),
      error: (error) => console.log(error),// code : 500 ,404
      complete: () => this.ngOnInit()
    })
    console.log('im added')
    location.reload();
  }
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



  affectMealToMenu(id: any, menu: Menu) {
    console.log('idmeal', id);
    console.log('menu', menu);
    this.mealService.affectMealToMenu(id,menu).subscribe(menu => {
      this._router.navigateByUrl('/meals')
      console.log('handle success')
    }, error => {
             console.log('handle error')

      // handle
    });
     location.reload();
  }



}
