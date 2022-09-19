import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'springboot-angular-foodbox';

  isShow = false;
  loginCheck = false;


  constructor(private router: Router) { }

  ngOnInit(): void {
    this.toggleOption();
  }

  toggleOption() {
    if (window.localStorage != null) {
      var userData = JSON.parse(localStorage.getItem("login_data"));
      var userName = userData.user_profile_details.name;

      if (userName === "Admin") {
        this.isShow = true;
      }
      if (userData != null) {
        this.loginCheck = true;
      }
    }
  }

  logOut() {
    window.localStorage.clear();
    this.loginCheck = false;
    this.router.navigate(['login']);
  }


}
