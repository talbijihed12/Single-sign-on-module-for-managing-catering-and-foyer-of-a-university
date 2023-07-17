import { Injectable } from '@angular/core';
import { SingleReservationFoyer } from '../../components/data/SingleReservationFoyer/SingleReservationFoyer';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  productsList:SingleReservationFoyer[] = [
  ]
  constructor() { }

  addProduct(product:SingleReservationFoyer){
    this.productsList.push(product)
  }
}
