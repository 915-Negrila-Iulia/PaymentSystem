import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToUsersHomePage(){
    this.router.navigate(['/users-home']);
  }

  goToPersonsHomePage(){
    this.router.navigate(['/persons-home']);
  }

  goToAccountsHomePage(){

  }

  goToBalancesHomePage(){

  }

  goToTransactionsHomePage(){
    
  }

  goToAuditListPage(){
    this.router.navigate(['audit-list']);
  }

  logout(){
    sessionStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

}
