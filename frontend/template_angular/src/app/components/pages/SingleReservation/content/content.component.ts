import { Component, EventEmitter, OnChanges, OnDestroy, OnInit, Output, SimpleChanges } from '@angular/core';
import gallery from '../../../data/gallery/gallery.json';
import category from '../../../data/gallery/category.json';
import { Category } from '../../../helper/filter/category';
import { Item } from '../../../helper/filter/item';
import { SingleReservationFoyer } from 'src/app/components/data/SingleReservationFoyer/SingleReservationFoyer';
import { ProductService } from 'src/app/Services/SingleReservationService/product.service';
import { CalculService } from 'src/app/Services/SingleReservationService/calcul.service';
import { ProductCosumerService } from 'src/app/Services/SingleReservationService/product-cosumer.service';
import { Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { SingleReservationFoyerCosumerService } from 'src/app/Services/SingleReservationService/SingleReservationFoyer-cosumer.service';



@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ProductsComponent implements OnInit {
  @Output() reloadComponent: EventEmitter<any> = new EventEmitter();
  page: number = 1;
  singleReservationFoyer! : SingleReservationFoyer;
  products2: SingleReservationFoyer[]=[this.singleReservationFoyer];
  products!:SingleReservationFoyer[];

  c!:number;
  constructor(private route: ActivatedRoute,private singleReservationConsumerService:SingleReservationFoyerCosumerService,private _productService: ProductService, private calcul:CalculService,private productConsumer:ProductCosumerService) { }

  ngOnInit(): void {
    //this.products = this._productService.productsList;
    this.reloadComponent.subscribe(singleReservationFoyer => {
      this.products=singleReservationFoyer;
      // do whatever you need to do to reload the component
      console.log('Reloading Component A');
    });
    console.log(this.products);
    if (!this.products) {
    this.productConsumer.getProducts().subscribe(
      singleReservationFoyer => {
        this.products = singleReservationFoyer;
        console.log(this.products);
      //next: (data)=>this.products=data, //code : 200 ,204
      //error : (error)=>console.log(error),// code : 500 ,404
      //complete : ()=>console.log("I m finshsing")
      }

    );
  }
  }


  Buy(id:string){
    // this.products.map((product)=>{
    //   if(product.id.match(id)){
    //     product.quantity=product.quantity-1;
    //   }
    // })

    this.products.map((product)=>product.id.toString()==id)
  }
  reloadPage() {
    if (this.productConsumer.getSearchResults()!=null) {
      this.productConsumer.getSearchResults().subscribe(singleReservationFoyer => {
        this.products2 = singleReservationFoyer;
        // update gallery component HTML with search results
      });
    window.location.reload();
  }
}
  message () {

  this.c=this.calcul.getNumberOf(this.products,"quantity", 0);

  }

  Delete(id:any){
    this.productConsumer.deleteProduct(id).subscribe({
      next: ()=>this.products = this.products.filter((p)=>p.id != id)
    });

  }
  updateValues(newValues: any) {
    this.products=newValues;
  }
}
