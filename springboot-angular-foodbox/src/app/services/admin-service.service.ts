import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Products } from '../Products';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {

  private baseURL = "http://localhost:8080";

  constructor(private httpClient:HttpClient) { }

  deleteProduct(id: number): Observable<object> {
    return this.httpClient.delete(`${this.baseURL}/api/v1/admin/delete-product/${id}`);
  }

  addProduct(product:Products):Observable<object>{
    return this.httpClient.post(`${this.baseURL}/api/v1/admin/add-product`,product);
  }

  getProductById(id:number):Observable<Products>{
    return this.httpClient.get<Products>(`${this.baseURL}/api/v1/products/${id}`);
  }

  updateProduct(id:number,product:Products):Observable<object>{
    return this.httpClient.put(`${this.baseURL}/api/v1/admin/update-product/${id}`, product);
  }
}
