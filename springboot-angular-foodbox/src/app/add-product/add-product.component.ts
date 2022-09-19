import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Products } from '../Products';
import { AdminServiceService } from '../services/admin-service.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  product: Products = new Products();

  constructor(private adminService: AdminServiceService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.adminService.addProduct(this.product).subscribe(data => {
      console.log(data);
      this.router.navigate(['dashboard']);
    })
  }

}
