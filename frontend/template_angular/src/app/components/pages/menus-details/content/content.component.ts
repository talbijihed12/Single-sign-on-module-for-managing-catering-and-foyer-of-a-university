import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FlightHelperService } from 'src/app/components/helper/flight/flight-helper.service';
import { Menu } from 'src/app/components/models/Menu';
import { MenuService } from 'src/app/components/shared/services/menu/menu.service';
import { MealService } from 'src/app/components/shared/services/meal/meal.service';
import { Meal } from 'src/app/components/models/Meals';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent  implements OnInit {
  id!: string
  idmeal!:string
  menus!: Menu;
  meals!: Meal;
  constructor(private menuService: MenuService, private route: ActivatedRoute,private mealService:MealService) {
  }
 settings = {
    infinite: true,
    slidesToShow: 7,
    slidesToScroll: 1,
    arrows: true,
    dots: false,
    autoplay: true,
    autoplaySpeed: 2000,
    speed: 500,
    cssEase: 'linear',
    responsive: [
      {
        breakpoint: 1200,
        settings: {
          arrows: true,
          slidesToShow: 5
        }
      }, {
        breakpoint: 768,
        settings: {
          arrows: false,
          dots: true,
          slidesToShow: 4
        }
      }, {
        breakpoint: 576,
        settings: {
          arrows: false,
          dots: true,
          slidesToShow: 3
        }
      }, {
        breakpoint: 400,
        settings: {
          arrows: false,
          dots: true,
          slidesToShow: 2
        }
      }]
  };

  ngOnInit(): void {
      this.route.params.subscribe(params => {
      this.id = params['id'];
      console.log(this.id); // This will log the id to the console
    });
      this.menuService.getMenuById(this.id).subscribe({
      next: (data)=>this.menus=data, //code : 200 ,204
      error : (error)=>console.log(error),// code : 500 ,404
      complete : ()=>console.log("I m finshsing")
    })

  }
   getmealbyid(): void {
      this.route.params.subscribe(params => {
      this.id = params['idmeal'];
      console.log(this.idmeal); // This will log the id to the console
    });
      this.mealService.getMealById(this.idmeal).subscribe({
      next: (data)=>this.meals=data, //code : 200 ,204
      error : (error)=>console.log(error),// code : 500 ,404
      complete : ()=>console.log("I m finshsing")
    })

  }







  settingsTesti = {
    infinite: true,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 2000,
    speed: 300,
    arrows: false,
    dots: false,
    cssEase: 'linear',
  };
}
