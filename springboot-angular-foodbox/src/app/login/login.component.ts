import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginServiceService } from '../services/login-service.service';
import { User } from '../User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email = "";
  password = "";
  user:any;
  constructor(private loginService:LoginServiceService, private router:Router) { }

  ngOnInit(): void {
  }

  loginUserCheck(){
    this.user={
      'email':this.email,
      'password':this.password
    }
    console.log(this.user);
    this.loginService.loginUserCheck(this.user).subscribe(data=>{
      if(data!=null){
        localStorage.setItem("login_data", JSON.stringify(data));
        this.router.navigate(['dashboard']);
      }
    })
  }

}
