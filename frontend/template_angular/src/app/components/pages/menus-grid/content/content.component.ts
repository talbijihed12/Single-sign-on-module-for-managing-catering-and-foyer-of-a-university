import { Component, OnInit } from '@angular/core';
import { Menu } from 'src/app/components/models/Menu';
import { MenuService } from 'src/app/components/shared/services/menu/menu.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
public menus: Menu[]=[];
constructor(private menuService: MenuService) {}


   ngOnInit(): void {
    this.menuService.getmenus().subscribe({
      next: (data)=>this.menus=data, //code : 200 ,204
      error : (error)=>console.log(error),// code : 500 ,404
      complete : ()=>console.log("I m finshsing")
    })
  }

}
