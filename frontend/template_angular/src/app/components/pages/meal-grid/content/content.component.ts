import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MealService } from 'src/app/components/shared/services/meal/meal.service';
import { Meal } from 'src/app/components/models/Meals';
import { VoteService } from 'src/app/components/shared/services/vote/vote.service';
import { ToastrService } from 'ngx-toastr';
import { MenuService } from 'src/app/components/shared/services/menu/menu.service';
import { Menu } from 'src/app/components/models/Menu';
import { LoginService } from 'src/app/components/shared/services/login.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css'],
})
export class ContentComponent implements OnInit {
  public meals: Meal[] = [];
  menuItems: Menu[]=[];
  voteUpCount: number = 0;
  voteDownCount: number = 0;
  voteCount: number = 0;
  username!: string;
  id: number = 0;
  allVotesUser: any = [];
  xx: any = [];

  constructor(
    private mealService: MealService,
    private voteservice: VoteService,
    private toastrService: ToastrService,
    private menuService:MenuService,
    private loginService:LoginService
  ) {}

  ngOnInit(): void {
    this.mealService.getmeal().subscribe({
      next: (data) => (this.meals = data), //code : 200 ,204
      error: (error) => console.log(error), // code : 500 ,404
      complete: () => console.log('I m finshsing'),
    });

     this.menuService.getmenus().subscribe({
      next: (data) => this.menuItems = data, //code : 200 ,204
      error: (error) => console.log(error),// code : 500 ,404
      complete: () => console.log("I m finshsing")
    })

    // function that get all vote of this connected user
    this.voteservice.retrieveVoteByUserName(this.username).subscribe({
      next: (data) => {
        this.allVotesUser = data;

        const mealsWithVoteTypes = this.meals.map((meal) => {
          // console.log('meal',meal)
          const vote = this.allVotesUser.find(
            (vote: any) =>
              vote.meal.id === meal.id && vote.username === this.username
          );
          return {
            menu: meal.name,
            dateStart: meal.datestart,
            id: meal.id,
            name: meal.name,
            typeCategory: meal.typeCategory,
            voteType: vote ? vote.voteType : null,
          };
        });
        this.xx = mealsWithVoteTypes;
        console.log('checking the xx', this.xx)
      }, //code : 200 ,204
      error: (error) => console.log(error), // code : 500 ,404
      complete: () => console.log('I m finshsing'),
    });

    // match between the vote meal and meals exist
    console.log(this.meals, this.allVotesUser);
    // if the meal is voted then we change the style of the button of downvote or upvote
  }
  testFunction() {
    console.log(this.meals, this.allVotesUser);
  }

  upVote(mealId: number) {
    console.log('mealID', mealId);
    // if status 200 bech nincrimti el vote b b 1
    // else nkharj popup t9ol ali ntii deja votit

    this.voteUpCount += 1;
    let payload = {
      voteType: 'UPVOTE',
      username: 'chiha',
      id: mealId,
    };
    this.voteservice.vote(payload).subscribe(
      (response) => {
        this.toastrService.success(
          'We are pleased to inform you that your vote has been confirmed !',
          'VOTE FOR MEAL!'
        );
      },
      (error) => {
        this.toastrService.error('You already VOTED !', 'VOTE FOR MEAL!');
      }
    );
    console.log('iam in upVote', this.voteUpCount);
  }

  downVote(mealId: number) {
    let payload = {
      voteType: 'DOWNVOTE',
      username: 'chiha',
      id: mealId,
    };
    this.voteservice.vote(payload).subscribe(
      (response) => {
        this.toastrService.success(
          'We are pleased to inform you that your vote has been confirmed !',
          'VOTE FOR MEAL!'
        );
      },
      (error) => {
        this.toastrService.error('You already VOTED !', 'VOTE FOR MEAL!');
      }
    );
    this.voteDownCount += 1;
    console.log('iam in downVote', this.voteDownCount);
  }

  desaffecterVote(mealId: number) {
    let payload = {
      voteType: 'DOWNVOTE',
      username: 'chiha',
      id: mealId,
    };
    console.log('mealID', mealId);
    this.voteservice.desaffecterVotee(payload).subscribe((response) => {
      this.voteCount -= 1;
    });
    console.log('iam in desaffecterVote', this.voteCount);
  }

  settings = {
    infinite: true,
    slidesToShow: 3,
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
          slidesToShow: 3,
        },
      },
      {
        breakpoint: 992,
        settings: {
          arrows: true,
          slidesToShow: 2,
        },
      },
      {
        breakpoint: 768,
        settings: {
          arrows: false,
          dots: true,
          slidesToShow: 2,
        },
      },
      {
        breakpoint: 576,
        settings: {
          arrows: false,
          dots: true,
          slidesToShow: 1,
        },
      },
    ],
  };
}
