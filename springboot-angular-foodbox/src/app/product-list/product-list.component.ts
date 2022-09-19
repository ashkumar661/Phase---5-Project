import { Component, OnInit } from '@angular/core';
import { Cart } from '../Cart';
import { Products } from '../Products';
import { CartServiceService } from '../services/cart-service.service';
import { ProductServiceService } from '../services/product-service.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products:Products[];
  cart:any;
  constructor(private productService:ProductServiceService,
    private cartService:CartServiceService
    ) { }

  ngOnInit(): void {
    this.getProductList();
  }

  getProductList(){
    this.productService.getProductList().subscribe(data=>{
      this.products = data;
    })
  }

  addToCart(id:number){
    let userData = JSON.parse(localStorage.getItem("login_data"));
    this.cart={
      'quantity': 1,
      'userId': userData.user_profile_details.user_id
    }
    console.log("User ID: ",userData.user_profile_details.user_id);
    console.log("Cart: ",this.cart);
    this.cartService.addToCart(id, this.cart).subscribe(data=>{
      console.log('Product is added', data);
    })
  }
}
