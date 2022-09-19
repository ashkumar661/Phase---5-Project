import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart } from '../Cart';
import { CartServiceService } from '../services/cart-service.service';
import { OrderServiceService } from '../services/order-service.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  itemQty: any;
  cart:any
  userData = JSON.parse(localStorage.getItem("login_data"));
  userId:number = this.userData.user_profile_details.user_id;
  constructor(private cartService:CartServiceService, private router:Router, private orderService:OrderServiceService) { }

  ngOnInit(): void {
    this.userCart(this.userId);
  }

  userCart(id:number){
    this.cartService.userCart(id).subscribe(data=>{
      this.cart = data;
      console.log(this.cart);
    })
  }

  decreaseQty(cartItem:Cart){
      this.cartService.decreaseQty(cartItem).subscribe(data=>{
        console.log(data);
        this.userCart(this.userId);
      })
  }

  increaseQty(cartItem:Cart){
      console.log(cartItem);
      this.cartService.increaseQty(cartItem).subscribe(data=>{
        console.log(data);
        this.userCart(this.userId);
      })
  }

  deleteCartItem(id:number){
    this.cartService.deleteCartItem(id).subscribe(()=>{
      console.log('Item deleted.');
      this.userCart(this.userId);
    })
  }

  checkOut(){
    console.log('Inside Checkout. User Id: ', this.userId)
    this.orderService.placeOrder(this.userId).subscribe(data=>{
      console.log(data);
      this.router.navigate(['order']);
    })
  }

}
