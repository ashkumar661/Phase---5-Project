import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../User';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  private baseURL = "http://localhost:8080";
  constructor(private httpClient:HttpClient) { }

  loginUserCheck(user:User):Observable<object>{
    return this.httpClient.post(`${this.baseURL}/api/v1/login`,user);
  }
}
