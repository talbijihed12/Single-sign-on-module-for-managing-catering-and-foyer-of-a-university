import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductsComponent } from './content/content.component';
@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css']
})
export class GalleryComponent implements OnInit {
  productsComponent!:ProductsComponent;
  constructor(private router: Router,productsComponent :ProductsComponent) { 
    this.productsComponent=productsComponent;
  }

  ngOnInit(): void {
  }
  navigateToPage2(id: string) {
    this.router.navigate(['/SingleReservationFoyerDetails', id]);
  }

}
