import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from '../Cart';

@Injectable({
  providedIn: 'root'
})
export class CartServiceService {

  cart:any;
  private baseURL= "http://localhost:8080";
  
  constructor(private httpClient: HttpClient) { }

  addToCart(id:number,cart:Cart):Observable<object>{
    return this.httpClient.post(`${this.baseURL}/api/v1/cart/add-to-cart/${id}`, cart);
  }

  userCart(id:number):Observable<Cart>{
    return this.httpClient.get<Cart>(`${this.baseURL}/api/v1/cart/user-cart/${id}`);
  }

  // updateCartItem(cart:Cart):Observable<Cart>{
  //   return this.httpClient.put<Cart>(`${this.baseURL}/api/v1/cart/update-cart-item`,cart);
  // }

  increaseQty(cart:Cart):Observable<Cart>{
    return this.httpClient.put<Cart>(`${this.baseURL}/api/v1/cart/increase-cart-item`,cart);
  }

  decreaseQty(cart:Cart):Observable<Cart>{
    return this.httpClient.put<Cart>(`${this.baseURL}/api/v1/cart/decrease-cart-item`,cart);
  }

  deleteCartItem(id:number){
    return this.httpClient.delete<Cart>(`${this.baseURL}/api/v1/cart/delete-cart-item/${id}`);
  }
}
