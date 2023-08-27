import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdmissionCandidacy } from 'src/app/models/admission-candidacy';
import { AdmissioncandidacyService } from 'src/app/services/admissioncandidacy.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-list-admission-candidacies',
  templateUrl: './list-admission-candidacies.component.html',
  styleUrls: ['./list-admission-candidacies.component.css']
})
export class ListAdmissionCandidaciesComponent {
  admissionCandidaciesList!:AdmissionCandidacy[];
  id!:number;



  constructor(private router:Router,private admissionCandidacyService:AdmissioncandidacyService,private authService: AuthService, private route: ActivatedRoute,){}
  role=this.authService.getRole();
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.id=id;
      this.getAdmissionCandidacy();
     
    });
  }
  getAdmissionCandidacy(){
    this.admissionCandidacyService.getAdmissionCandidacy().subscribe(
      data =>  this.admissionCandidaciesList=data 
      );
  }
  onAccept(id:number){
    this.admissionCandidacyService.updateAdmissionCandidacyStatus(id,"accepted").subscribe(() => {
      this.admissionCandidacyService.getAdmissionCandidacy().subscribe(
        data =>  this.admissionCandidaciesList=data 
        );

      });
  }
  onReject(id:number){
    this.admissionCandidacyService.updateAdmissionCandidacyStatus(id,"rejected").subscribe(() => {
      this.admissionCandidacyService.getAdmissionCandidacy().subscribe(
        data =>  this.admissionCandidaciesList=data 
        );
    });

  }

}
