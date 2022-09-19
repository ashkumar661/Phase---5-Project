import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddProductComponent } from './add-product/add-product.component';
import { AdminComponent } from './admin/admin.component';
import { CartComponent } from './cart/cart.component';
import { LoginComponent } from './login/login.component';
import { OrderComponent } from './order/order.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ProductListComponent } from './product-list/product-list.component';
import { UpdateProductComponent } from './update-product/update-product.component';

const routes: Routes = [
  {path:'dashboard',component:ProductListComponent},
  {path:'login', component: LoginComponent},
  {path:'cart',component:CartComponent},
  {path:'order',component:OrderComponent},
  {path:'admin', component:AdminComponent},
  {path:'update-product/:id',component:UpdateProductComponent},
  {path:'add-product',component:AddProductComponent},
  { path: '', redirectTo:'login', pathMatch:'full'},
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
