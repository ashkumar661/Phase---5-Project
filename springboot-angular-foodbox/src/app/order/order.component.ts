import { Component, OnInit } from '@angular/core';
import { OrderServiceService } from '../services/order-service.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  orders: any;
  cart:any;
  userData = JSON.parse(localStorage.getItem("login_data"));
  userId:number = this.userData.user_profile_details.user_id;
  isHidden: boolean;
  
  constructor(private orderService:OrderServiceService) { }

  ngOnInit(): void {
    this.userOrders();
  }

  oderDetail(id:number){
    this.orderService.oderDetail(id).subscribe(data=>{
      this.cart=data;
      console.log(this.cart);
    })
  }

  userOrders(){
    this.orderService.userOrders(this.userId).subscribe(data=>{
      this.orders=data
      console.log(this.orders);
    })
  }

}
