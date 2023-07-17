import { Component, OnInit } from '@angular/core';
import { MenuService } from '../../../shared/services/menu/menu.service';
import { Menu } from 'src/app/components/models/Menu';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

enum Options {
  Personnalisé=1,Standard, Petit_déjeuner,Déjeuner,Diner
}
@Component({
  selector: 'app-menus',
  templateUrl: './menus.component.html',
  styleUrls: ['./menus.component.css'],
})

export class MenusComponent implements OnInit {
  public menus: Menu[] = [];
  closeResult: string = '';
  menu: Menu = new Menu();

  constructor(
    private menuService: MenuService,
    private modalService: NgbModal,
    private _router: Router
  ) {}

  ngOnInit(): void {
    this.menuService.getmenus().subscribe({
      next: (data) => (this.menus = data), //code : 200 ,204
      error: (error) => console.log(error), // code : 500 ,404
      complete: () => console.log('I m finshsing'),
    });
  }


 editmenu(id: any,menu: Menu) {
   console.log('im editing',menu)
    this.menuService.updatemenu(id,menu).subscribe({
        next: ()=>this._router.navigateByUrl('/listofvote'),
        error: (error)=>console.log(error),
        complete:()=>console.log("I m finishing")
      })
    console.log('im done', id)
  location.reload();}


  open(content: any) {
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (result) => {
          this.closeResult = `Closed with: ${result}`;
        },
        (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        }
      );
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

  addmenu(menus: any) {
    console.log('im added', menus);
    this.menuService.addmenu(this.menu).subscribe({
      next: () => this._router.navigateByUrl('/Menus'),
      error: (error) => console.log(error), // code : 500 ,404
      complete: () => this.ngOnInit(),
    });
    console.log('im added');
  }

  deleteMenu(voteId: any) {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger',
      },
      buttonsStyling: false,
    });

    swalWithBootstrapButtons
      .fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, delete it!',
        cancelButtonText: 'No, cancel!',
        reverseButtons: true,
      })
      .then((result) => {
        if (result.isConfirmed) {
          console.log('i m deleting', voteId);
          this.menuService.deleteMenuById(voteId).subscribe({
            next: (data) => console.log(data), //code : 200 ,204
            error: (error) => console.log(error), // code : 500 ,404
            complete: () => console.log('I m finshsingggg'),
          });

          location.reload();
        } else if (
          /* Read more about handling dismissals below */
          result.dismiss === Swal.DismissReason.cancel
        ) {
          swalWithBootstrapButtons.fire(
            'Cancelled',
            'Your vote is safe :)',
            'error'
          );
        }
      });
  }


}
