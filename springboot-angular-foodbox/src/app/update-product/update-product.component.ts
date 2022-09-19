import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Products } from '../Products';
import { AdminServiceService } from '../services/admin-service.service';
import { ProductServiceService } from '../services/product-service.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  product:Products = new Products();
  id:number;

  constructor(private route:ActivatedRoute,private router:Router, private productService:ProductServiceService,
    private adminService: AdminServiceService
    ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.productService.getProductById(this.id).subscribe(data=>{
      this.product = data;
      console.log(this.product);
    })
  }

  onSubmit(){
    this.adminService.updateProduct(this.id,this.product).subscribe(data=>{
      this.router.navigate(['dashboard']);
    })
  }
}
