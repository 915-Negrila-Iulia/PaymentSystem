import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-accounts-home',
  templateUrl: './accounts-home.component.html',
  styleUrls: ['./accounts-home.component.scss']
})
export class AccountsHomeComponent implements OnInit, OnChanges {

  show: string = "accountsList";
  eventsSubject: Subject<void> = new Subject<void>();
  
  constructor() { }

  ngOnInit(): void {
  }

  ngOnChanges(): void{
  }

  viewAccounts(){
    this.show = "accountsList";
    window.location.reload();
  }

  addAccount(){
    this.show = "addAccount";
  }

  viewHistory(){
    this.show = "accountsHistory";
    this.eventsSubject.next();
  }

}
