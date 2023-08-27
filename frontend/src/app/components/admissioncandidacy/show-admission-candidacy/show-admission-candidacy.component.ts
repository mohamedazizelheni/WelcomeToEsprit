import { Component,NgModule } from '@angular/core';
import { AdmissioncandidacyService } from 'src/app/services/admissioncandidacy.service';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import {AdmissionCandidacy  } from 'src/app/models/admission-candidacy';
import { Interview } from 'src/app/models/interview';
import { InterviewService } from 'src/app/services/interview.service';
import { ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-show-admission-candidacy',
  templateUrl: './show-admission-candidacy.component.html',
  styleUrls: ['./show-admission-candidacy.component.css']
})
export class ShowAdmissionCandidacyComponent {
  constructor(private admissioncandidacyService:AdmissioncandidacyService,private authService:AuthService, private route: ActivatedRoute,private userService:UserService,private interviewService:InterviewService){}
  role=this.authService.getRole();
  admissionCandidacy!:AdmissionCandidacy;
  mail=this.authService.getSubject();
  user!:User;
  interview!:Interview;
  id!: number;
  admissioncandidacies!:AdmissionCandidacy[];
  score!:number;

  ngOnInit() {  
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.id=id;
      this.getAdmissionCandidacyById(id);
      this.interviewService.getInterviewByAdmissionCandidacyId(id).subscribe(
        data => {
          this.interview = data;
      });
  });
  if (this.mail)
    this.userService.getUserbyMail(this.mail)
    .subscribe((user) => {
      this.user = user;
    });
  }
  getAdmissionCandidacy(){
    this.admissioncandidacyService.getAdmissionCandidacy().subscribe(
      data =>  this.admissioncandidacies=data 
      );
  }
  getAdmissionCandidacyById(id: number) {
    this.admissioncandidacyService. getAdmissionCandidacyById(id).subscribe(
      data => {
        this. admissionCandidacy   = data;
    });
  }
  deleteAdmissionCandidacy(id:number){
    this.admissioncandidacyService.deleteAdmissionCandidacy(id).subscribe(
      ()=> {
        window.location.href = `http://localhost:4200/post`;
        alert('Deleted!');
        
      }
    );
  }
  toggleFullScreen(event: MouseEvent) {
    const mediaContainer = event.currentTarget as HTMLElement;
    const media = mediaContainer.querySelector('.media') as HTMLElement;
    if (mediaContainer.classList.contains('fullscreen')) {
      mediaContainer.classList.remove('fullscreen');
    } else {
      mediaContainer.classList.add('fullscreen');
    }
  }

  updateAdmissionCandidacyScore(F:NgForm){
    if(F.controls['score'])
    this.score = F.controls['score'].value;
    this.admissioncandidacyService.updateAdmissionCandidacyScore(this.id, this.score).subscribe(() => {
      location.reload();
    });
  }
  getStatusColor(status: string): string {
    switch (status) {
      case 'being processed':
        return 'blue';
      case 'accepted':
        return 'green';
      case 'rejected':
        return 'red';
      default:
        return 'yellow'; // Set default color if none of the cases match
    }
  }
  
}

