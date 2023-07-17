import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductComponent } from './content/ProductComponent';
@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css']
})
export class GalleryComponent implements OnInit {
  productsComponent!:ProductComponent;
  constructor(private router: Router,private productssComponent :ProductComponent) { 
    this.productsComponent=productssComponent;
    
  }

  ngOnInit(): void {
  }
  navigateToPage2(id: string) {
    this.router.navigate(['/SingleReservationFoyerDetails', id]);
  }

}
