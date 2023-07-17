import { HeaderComponent } from './../../components/shared/header/header.component';
import { OAuthService } from 'angular-oauth2-oidc';
import { User } from 'src/app/components/models/User';
import { UserService } from './../../components/shared/services/user.service';
import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/components/shared/services/login.service';


@Component({
  selector: 'app-liste',
  templateUrl: './liste.component.html',
  styleUrls: ['./liste.component.css']
})
export class ListeComponent implements OnInit {
  users:User[]=[];
  page: number = 1;
  searchText!: string;





  constructor(private userService:UserService,private loginService:LoginService) { }

  ngOnInit(): void {
    this.loadUsers();
  }
  loadUsers():void{
    this.userService.list().subscribe(
      data =>{
        this.users=data;
      },
      error => console.log(error)
    );

  }
  onDelete(id:number):void{
    if (confirm('this will delete this profile?')) {
      if (confirm('Are you sure you want to delete this profile?')){
    this.userService.delete(id).subscribe(
      data=>{
        console.log(data);
        this.loadUsers();
      },
      error =>console.log(error)
    )
  }
}
}

}
