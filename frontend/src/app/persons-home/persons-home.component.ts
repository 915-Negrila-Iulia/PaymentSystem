import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-persons-home',
  templateUrl: './persons-home.component.html',
  styleUrls: ['./persons-home.component.scss']
})
export class PersonsHomeComponent implements OnInit {

  show: string = "personsList";

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  viewPersons(){
    this.show = 'personsList';
    window.location.reload();
  }

  addPerson(){
    this.show = 'addPerson';
  }

  viewHistory(){
    this.show = 'personsHistory';
  }
}
