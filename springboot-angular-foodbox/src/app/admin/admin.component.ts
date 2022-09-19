import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Products } from '../Products';
import { AdminServiceService } from '../services/admin-service.service';
import { ProductServiceService } from '../services/product-service.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  products: Products[];
  constructor(private productService: ProductServiceService,
    private router: Router,
    private adminService:AdminServiceService
  ) { }

  ngOnInit(): void {
    this.getProductList();
  }

  getProductList() {
    this.productService.getProductList().subscribe(data => {
      this.products = data;
    })
  }

  updateProduct(id: number) {
    this.router.navigate(['update-product', id]);
  }

  deleteProduct(id: number) {
    this.adminService.deleteProduct(id).subscribe(data=>{
      console.log(data);
      this.getProductList();
    })
  }

  addProduct(){
    this.router.navigate(['add-product']);
  }

}
