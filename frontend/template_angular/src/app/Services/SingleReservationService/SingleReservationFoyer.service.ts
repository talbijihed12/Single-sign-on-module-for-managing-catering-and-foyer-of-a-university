import { Injectable } from '@angular/core';
import { SingleReservationFoyer } from '../../components/data/SingleReservationFoyer/SingleReservationFoyer';

@Injectable({
  providedIn: 'root'
})
export class SingleReservationFoyerService {
  page: number = 1;
  productsList:SingleReservationFoyer[] =[];
  constructor() { }

  addProduct(product:SingleReservationFoyer){
    this.productsList.push(product)
  }
}
