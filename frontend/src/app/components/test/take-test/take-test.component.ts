import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { data } from 'jquery';
import { AdmissionCandidacy } from 'src/app/models/admission-candidacy';
import { AdmissioncandidacyService } from 'src/app/services/admissioncandidacy.service';
import { TestService } from 'src/app/services/test.service';

@Component({
  selector: 'app-take-test',
  templateUrl: './take-test.component.html',
  styleUrls: ['./take-test.component.css']
})
export class TakeTestComponent {
  admissionCandidacy!:AdmissionCandidacy;

  candidacyId!:number;
  done=false;
  confirmed=false;
  answer: string[]=['','','',''];
  constructor(private testService:TestService,private route: ActivatedRoute,private admissioncandidacyService:AdmissioncandidacyService){}
  ngOnInit() {  
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.candidacyId=id;
      this.getAdmissionCandidacyById(id);
    });
  }
  getAdmissionCandidacyById(id: number) {
    this.admissioncandidacyService. getAdmissionCandidacyById(id).subscribe(
      data => {
        this. admissionCandidacy   = data;
    });
  }
  getResultTest(F:NgForm){
    if(this.confirmed==true){
  this.done=true;
  this.testService.getResultTest(this.admissionCandidacy.tests[0].id, this.candidacyId, this.answer[0], this.answer[1], this.answer[2], this.answer[3])
  .subscribe(result => {
    this.getAdmissionCandidacyById(this.candidacyId);
    console.log(result);
  });
  }
}
}
