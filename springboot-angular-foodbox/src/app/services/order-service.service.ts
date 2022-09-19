import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OrderServiceService {

  private baseURL= "http://localhost:8080";
  constructor(private httpClient:HttpClient) { }

  placeOrder(id:number){
    return this.httpClient.get(`${this.baseURL}/api/v1/order/place-order/${id}`);
  }

  userOrders(id:number){
    return this.httpClient.get(`${this.baseURL}/api/v1/order/view-all-order/${id}`);
  }

  oderDetail(id:number){
    return this.httpClient.get(`${this.baseURL}/api/v1/order/view-order/${id}`);
  }

}
