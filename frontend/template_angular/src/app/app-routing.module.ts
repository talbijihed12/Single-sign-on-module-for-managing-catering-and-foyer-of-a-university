import { RestaurantSubModule } from './components/pages/restaurant-sub/restaurant-sub.module';
import { UpdateComponent } from './user/update/update.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './auth/signup/signup.component';
import { ListeComponent } from './user/liste/liste.component';
import { DetailsComponent } from './user/details/details.component';
import { UpdateByAdminComponent } from './user/update-by-admin/update-by-admin.component';
import { UserGuard } from './guards/user.guard';
import { SignupGuard } from './guards/signup.guard';
import { RestaurantSubComponent } from './components/pages/restaurant-sub/restaurant-sub/restaurant-sub.component';
import { ReservationFoyerDetailsilsComponent } from './components/pages/ReservationFoyerDetails/SingleReservationFoyerDetails.component';
import { ReservationRestaurantDetailsilsComponent } from './components/pages/ReservationRestaurantDetails/SingleReservationFoyerDetails.component';
import { SingleReservationFoyerDetailsilsComponent } from './components/pages/SingleReservationFoyerDetails/SingleReservationFoyerDetails.component';

const routes: Routes = [
  {path :'sign-up',component: SignupComponent,canActivate:[SignupGuard]},
  {path :'liste',component: ListeComponent,canActivate:[UserGuard],data:{requiredRoles:['ROLE_ADMIN']}},
  {path :'details/:id',component: DetailsComponent,canActivate:[UserGuard],data:{requiredRoles:['ROLE_ADMIN']}},
  {path :'update',component: UpdateComponent},
  {path :'update-by-admin/:id',component: UpdateByAdminComponent,canActivate:[UserGuard],data:{requiredRoles:['ROLE_ADMIN']} },


  // Homepage
  //{ path: '', loadChildren: () => import('./components/pages/home/home.module').then(m => m.HomeModule), data: { breadcrumb: "Homepage" } },
  { path: 'home-v2', loadChildren: () => import('./components/pages/home-two/home-two.module').then(m => m.HomeTwoModule), data: { breadcrumb: "Homepage" } },
  { path: 'historics', loadChildren: () => import('./components/pages/historics/home-two.module').then(m => m.HomeTwoModule), data: { breadcrumb: "Historical Claims" } },
  // About
  { path: 'about', loadChildren: () => import('./components/pages/about/about.module').then(m => m.AboutModule), data: { breadcrumb: "About Us" } },
  // Blog
  { path: 'blog/cat/:catId', loadChildren: () => import('./components/pages/blog-grid/blog-grid.module').then(m => m.BlogGridModule), data: { breadcrumb: "Blog Grid" } },
  { path: 'blog/tag/:tagId', loadChildren: () => import('./components/pages/blog-grid/blog-grid.module').then(m => m.BlogGridModule), data: { breadcrumb: "Blog Grid" } },
  { path: 'blog/author/:authorId', loadChildren: () => import('./components/pages/blog-grid/blog-grid.module').then(m => m.BlogGridModule), data: { breadcrumb: "Blog Grid" } },
  { path: 'blog/search/:query', loadChildren: () => import('./components/pages/blog-grid/blog-grid.module').then(m => m.BlogGridModule), data: { breadcrumb: "Blog Grid" } },
  { path: 'blog-grid', loadChildren: () => import('./components/pages/blog-grid/blog-grid.module').then(m => m.BlogGridModule), data: { breadcrumb: "Blog Grid" } },
  { path: 'blog-grid-left', loadChildren: () => import('./components/pages/blog-grid-left/blog-grid-left.module').then(m => m.BlogGridLeftModule), data: { breadcrumb: "Blog Grid" } },
  { path: 'blog-grid-right', loadChildren: () => import('./components/pages/blog-grid-right/blog-grid-right.module').then(m => m.BlogGridRightModule), data: { breadcrumb: "Blog Grid" } },
  { path: 'blog-details/:id', loadChildren: () => import('./components/pages/blog-details/blog-details.module').then(m => m.BlogDetailsModule), data: { breadcrumb: "Blog Details" } },
  { path: 'blog-details-left/:id', loadChildren: () => import('./components/pages/blog-details-left/blog-details-left.module').then(m => m.BlogDetailsLeftModule), data: { breadcrumb: "Blog Details" } },
  { path: 'blog-details-right/:id', loadChildren: () => import('./components/pages/blog-details-right/blog-details-right.module').then(m => m.BlogDetailsRightModule), data: { breadcrumb: "Blog Details" } },
  // Hotels
  { path: 'hotel/:minPrice/:maxPrice', loadChildren: () => import('./components/pages/hotels-grid/hotels-grid.module').then(m => m.HotelsGridModule), data: { breadcrumb: "Hotels Grid" } },
  { path: 'hotel-grid', loadChildren: () => import('./components/pages/hotels-grid/hotels-grid.module').then(m => m.HotelsGridModule), data: { breadcrumb: "Hotels Grid" } },
  { path: 'hotel-grid-left', loadChildren: () => import('./components/pages/hotels-grid-left/hotels-grid-left.module').then(m => m.HotelsGridLeftModule), data: { breadcrumb: "Hotels Grid" } },
  { path: 'hotel-grid-right', loadChildren: () => import('./components/pages/hotels-grid-right/hotels-grid-right.module').then(m => m.HotelsGridRightModule), data: { breadcrumb: "Hotels Grid" } },
  { path: 'hotel-details/:id', loadChildren: () => import('./components/pages/hotels-details/hotels-details.module').then(m => m.HotelsDetailsModule), data: { breadcrumb: "Hotels Details" } },
  // Flights
  { path: 'flight/:minPrice/:maxPrice', loadChildren: () => import('./components/pages/flights-grid/flights-grid.module').then(m => m.FlightsGridModule), data: { breadcrumb: "Flights Grid" } },
  { path: 'flight-grid', loadChildren: () => import('./components/pages/flights-grid/flights-grid.module').then(m => m.FlightsGridModule), data: { breadcrumb: "Flights Grid" } },
  { path: 'flight-grid-left', loadChildren: () => import('./components/pages/flights-grid-left/flights-grid-left.module').then(m => m.FlightsGridLeftModule), data: { breadcrumb: "Flights Grid" } },
  { path: 'flight-grid-right', loadChildren: () => import('./components/pages/flights-grid-right/flights-grid-right.module').then(m => m.FlightsGridRightModule), data: { breadcrumb: "Flights Grid" } },
  { path: 'flight-details/:id', loadChildren: () => import('./components/pages/flights-details/flights-details.module').then(m => m.FlightsDetailsModule), data: { breadcrumb: "Flights Details" } },
  // Cars
  { path: 'car/:minPrice/:maxPrice', loadChildren: () => import('./components/pages/cars-grid/cars-grid.module').then(m => m.CarsGridModule), data: { breadcrumb: "Cars Grid" } },
  { path: 'car-grid', loadChildren: () => import('./components/pages/cars-grid/cars-grid.module').then(m => m.CarsGridModule), data: { breadcrumb: "Cars Grid" } },
  { path: 'car-grid-left', loadChildren: () => import('./components/pages/cars-grid-left/cars-grid-left.module').then(m => m.CarsGridLeftModule), data: { breadcrumb: "Cars Grid" } },
  { path: 'car-grid-left/:id', loadChildren: () => import('./components/pages/cars-grid-left/cars-grid-left.module').then(m => m.CarsGridLeftModule), data: { breadcrumb: "Cars Grid" } },
  { path: 'car-grid-right', loadChildren: () => import('./components/pages/cars-grid-right/cars-grid-right.module').then(m => m.CarsGridRightModule), data: { breadcrumb: "Cars Grid" } },
  { path: 'car-grid-right/:id', loadChildren: () => import('./components/pages/cars-grid-right/cars-grid-right.module').then(m => m.CarsGridRightModule), data: { breadcrumb: "Cars Grid" } },
  { path: 'car-details/:id', loadChildren: () => import('./components/pages/cars-details/cars-details.module').then(m => m.CarsDetailsModule), data: { breadcrumb: "Cars Details" } },
  // Cruise
  { path: 'cruise/:minPrice/:maxPrice', loadChildren: () => import('./components/pages/cruise-grid/cruise-grid.module').then(m => m.CruiseGridModule), data: { breadcrumb: "Cruise Grid" } },
  { path: 'cruise/:minNight/:maxNight', loadChildren: () => import('./components/pages/cruise-grid/cruise-grid.module').then(m => m.CruiseGridModule), data: { breadcrumb: "Cruise Grid" } },
  { path: 'cruise-grid', loadChildren: () => import('./components/pages/cruise-grid/cruise-grid.module').then(m => m.CruiseGridModule), data: { breadcrumb: "Cruise Grid" } },
  { path: 'cruise-grid-left', loadChildren: () => import('./components/pages/cruise-grid-left/cruise-grid-left.module').then(m => m.CruiseGridLeftModule), data: { breadcrumb: "Cruise Grid" } },
  { path: 'cruise-grid-right', loadChildren: () => import('./components/pages/cruise-grid-right/cruise-grid-right.module').then(m => m.CruiseGridRightModule), data: { breadcrumb: "Cruise Grid" } },
  { path: 'cruise-details/:id', loadChildren: () => import('./components/pages/cruise-details/cruise-details.module').then(m => m.CruiseDetailsModule), data: { breadcrumb: "Cruise Details" } },
  // Contact
  { path: 'contact', loadChildren: () => import('./components/pages/contact/contact.module').then(m => m.ContactModule), data: { breadcrumb: "Contact Us" } },
  // Faqs
  { path: 'faqs', loadChildren: () => import('./components/pages/faqs/faqs.module').then(m => m.FaqsModule), data: { breadcrumb: "FAQ's" } },
  // Booking
  { path: 'booking', loadChildren: () => import('./components/pages/booking/booking.module').then(m => m.BookingModule), data: { breadcrumb: "Booking" } },
  // Gallery
  { path: 'gallery', loadChildren: () => import('./components/pages/gallery/gallery.module').then(m => m.GalleryModule), data: { breadcrumb: "Gallery" } },

   // Subscription
  { path: 'academic-home-subscription', loadChildren: () => import('./components/pages/academic-home-sub/academic-home-sub.module').then(m => m.AcademicHomeSubModule), data: { breadcrumb: "Academic home subscription" } },
 // { path: 'restaurant-subscription', loadChildren: () => import('./components/pages/restaurant-sub/restaurant-sub.module').then(m => m.RestaurantSubModule), data: { breadcrumb: "restaurant subscription" } },
  {path :'restaurant-subscription',component: RestaurantSubComponent},
 // {path: 'restaurant-subscription', loadChildren: () => import('./components/pages/restaurant-sub/restaurant-sub.module').then(m => m.RestaurantSubModule)},
  //ReservationFoyer
  {path:'ReservationFoyerDetails/:id',component:ReservationFoyerDetailsilsComponent, loadChildren: () => import('./components/pages/ReservationFoyerDetails/SingleReservationFoyerDetailsModule').then(m => m.ReservationFoyerModule), data: { breadcrumb: "ReservationFoyerDetails" }},
  {path:'ReservationFoyerDetailsClient/:id',component:ReservationFoyerDetailsilsComponent, loadChildren: () => import('./components/pages/ReservationFoyerDetails copy/SingleReservationFoyerDetailsModule').then(m => m.ReservationFoyerModule), data: { breadcrumb: "ReservationFoyerDetails" }},
  { path: 'ReservationFoyers', loadChildren: () => import('./components/pages/reservationFoyer/gallery.module').then(m => m.ReservationFoyerModule), data: { breadcrumb: "ReservationFoyers" } },
  { path: 'ReservationFoyersClient', loadChildren: () => import('./components/pages/reservationFoyer copy/gallery.module').then(m => m.ReservationFoyerModule), data: { breadcrumb: "ReservationFoyers" } },


  //ReservationRestaurant
  {path:'ReservationRestaurantDetails/:id',component:ReservationRestaurantDetailsilsComponent, loadChildren: () => import('./components/pages/ReservationRestaurantDetails/SingleReservationFoyerDetailsModule').then(m => m.ReservationRestaurantModule), data: { breadcrumb: "ReservationRestaurantDetails" }},
  {path: 'ReservationRestaurants', loadChildren: () => import('./components/pages/reservationRestaurant/gallery.module').then(m => m.ReservationRestaurantModule), data: { breadcrumb: "ReservationRestaurants" } },
  {path: 'ReservationRestaurantsClient', loadChildren: () => import('./components/pages/reservationRestaurant copy/gallery.module').then(m => m.ReservationRestaurantModule), data: { breadcrumb: "ReservationRestaurants" } },
  {path: 'AddReservationRestaurants', loadChildren: () => import('./components/pages/AddReservationRestaurant/AddReservationRestaurant.module').then(m => m.AddReservationRestaurantModule), data: { breadcrumb: "ReservationRestaurants" } },
  {path: 'AddReservationRestaurantsClient', loadChildren: () => import('./components/pages/AddReservationRestaurant copy/AddReservationRestaurant.module').then(m => m.AddReservationRestaurantModule), data: { breadcrumb: "ReservationRestaurants" } },
  {path: 'test', loadChildren: () => import('./components/pages/reservationRestaurant/gallery.module').then(m => m.ReservationRestaurantModule), data: { breadcrumb: "test" } },
  //SingleReservationFoyer
  { path: 'AddReservationFoyer', loadChildren: () => import('./components/pages/AddSingleReservation/contact.module').then(m => m.ContactModule), data: { breadcrumb: "AddReservationFoyer" } },
  { path: 'AddReservationFoyerClient', loadChildren: () => import('./components/pages/AddSingleReservation copy/contact.module').then(m => m.ContactModule), data: { breadcrumb: "AddReservationFoyer" } },
  { path: 'SingleReservationFoyer', loadChildren: () => import('./components/pages/SingleReservation/gallery.module').then(m => m.GalleryModule), data: { breadcrumb: "Gallery" } },
  { path: 'SingleReservationFoyerClient', loadChildren: () => import('./components/pages/SingleReservation copy/gallery.module').then(m => m.GalleryModule), data: { breadcrumb: "Liste Single Reservation Foyer Client " } },
  {path:'SingleReservationFoyerDetails/:id',component:SingleReservationFoyerDetailsilsComponent, loadChildren: () => import('./components/pages/SingleReservationFoyerDetails/SingleReservationFoyerDetailsModule').then(m => m.SingleReservationFoyerModule), data: { breadcrumb: "SingleReservationFoyerDetails" }},


    //MALEK
    { path: 'meal-grid', loadChildren: () => import('./components/pages/meal-grid/meal-grid.module').then(m => m.MealGridModule), data: { breadcrumb: "Meal Grid" } },
    { path: 'Menus', loadChildren: () => import('./components/pages/menu/menu.module').then(m => m.MenuModule), data: { breadcrumb: "Menupage" } },    { path: 'listofvote', loadChildren: () => import('./components/pages/vote/vote.module').then(m => m.VoteModule), data: { breadcrumb: "list of votes " } },
    { path: 'menu-details/:id', loadChildren: () => import('./components/pages/menus-details/menus-details.module').then(m => m.FlightsDetailsModule), data: { breadcrumb: "Menus Details" } },
    { path: 'menu-grid', loadChildren: () => import('./components/pages/menus-grid/menus-grid.module').then(m => m.MenusGridModule), data: { breadcrumb: "Menus" } },
    { path: 'listofvote', loadChildren: () => import('./components/pages/vote/vote.module').then(m => m.VoteModule), data: { breadcrumb: "list of votes " } },
    { path: 'meals', loadChildren: () => import('./components/pages/meals/meals-grid.module').then(m => m.MealsGridModule), data: { breadcrumb: "Hotels Grid" } },
    //MARYEM
    { path: 'add-claim', loadChildren: () => import('./components/pages/booking/booking.module').then(m => m.BookingModule), data: { breadcrumb: "Claim" } },
    { path: 'claim-list', loadChildren: () => import('./components/pages/claims-list/claims-list.module').then(m => m.ClaimsGridModule), data: { breadcrumb: "List of claims" } },
    { path: 'claim-list-admin', loadChildren: () => import('./components/pages/claims-list-admin/claims-list-admin.module').then(m => m.ClaimsListAdminModule), data: { breadcrumb: "Claims List" } },
    { path: 'claim-details/:id', loadChildren: () => import('./components/pages/claims-details/claims-details.module').then(m => m.ClaimsDetailsModule), data: { breadcrumb: "Claims Details" } },
    { path: 'details-claims-admin/:id', loadChildren: () => import('./components/pages/details-claim-admin/details-claim-admin.module').then(m => m.CarsDetailsModule), data: { breadcrumb: "Claims Details" } },
    { path: 'editRec/:id', loadChildren: () => import('./components/pages/edit-claim/edit-claim.module').then(m => m.CarsGridModule), data: { breadcrumb: "Edit Claim" } },
    { path: '', loadChildren: () => import('./components/pages/dashboard-claim/home.module').then(m => m.HomeModule), data: { breadcrumb: "Dashboard" } },

    // Additional
  { path: 'error-page', loadChildren: () => import('./components/pages/error-page/error-page.module').then(m => m.ErrorPageModule), data: { breadcrumb: "Error 404" } },
  { path: 'coming-soon', loadChildren: () => import('./components/pages/coming-soon/coming-soon.module').then(m => m.ComingSoonModule), data: { breadcrumb: "Coming Soon" } },
  { path: '**', loadChildren: () => import('./components/pages/error-page/error-page.module').then(m => m.ErrorPageModule), data: { breadcrumb: "Error 404" } },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
