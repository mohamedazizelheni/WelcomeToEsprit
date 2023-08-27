import { Component } from '@angular/core';
import { TestService } from 'src/app/services/test.service';
import { Test} from 'src/app/models/test';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Observable } from 'rxjs'; 






@Component({
  selector: 'app-addtest',
  templateUrl: './addtest.component.html',
  styleUrls: ['./addtest.component.css']
})
export class AddTestComponent {

  constructor(
    private testService: TestService
  ) {}

  public test :Test = {
    id: 0,
    title: '',
    type: '',
    questions:[],
    admissionCandidacies:[]

  }

  addTest(){
    
    this.testService.createNewTest(this.test).subscribe(
      (Response :Test) =>{
        window.location.reload();
      }
    );
  }

}
