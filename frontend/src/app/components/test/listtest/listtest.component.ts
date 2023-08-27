import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Test } from 'src/app/models/test';
import { TestService } from 'src/app/services/test.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-listtest',
  templateUrl: './listtest.component.html',
  styleUrls: ['./listtest.component.css']
})
export class ListtestComponent {
  testList!:Test[];
  constructor(private router:Router,private testService:TestService,private authService: AuthService){}
  role=this.authService.getRole();
  ngOnInit(): void {
    this.getTest();
  }
  getTest(){
    this.testService.getTest().subscribe(
      data =>  this.testList=data 
      );


}
removeTestById(id:number){
  this.testService.removeTestById(id).subscribe(
    ()=> {
      this.getTest();
      alert('Deleted!');
    }
  );
}

}
