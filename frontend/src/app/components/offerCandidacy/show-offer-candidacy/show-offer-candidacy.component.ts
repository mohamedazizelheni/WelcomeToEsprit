import { Component, Input, NgModule } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OfferCandidacy } from 'src/app/models/offer-candidacy';
import { OfferCandidacyService } from 'src/app/services/offer-candidacy.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { AuthService } from 'src/app/services/auth.service';
import { ShowUserComponent } from '../../user/show-user/show-user.component';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import { Interview } from 'src/app/models/interview';
import { InterviewService } from 'src/app/services/interview.service';
import { NgForm } from '@angular/forms';



@Component({
  selector: 'app-show-offer-candidacy',
  templateUrl: './show-offer-candidacy.component.html',
  styleUrls: ['./show-offer-candidacy.component.css']
})

export class ShowOfferCandidacyComponent {
  constructor(private offerCandidacyService:OfferCandidacyService,private authService:AuthService, private route: ActivatedRoute,private userService:UserService,private interviewService:InterviewService){}
  role=this.authService.getRole();
  offerCandidacy!:OfferCandidacy;
  mail=this.authService.getSubject();
  user!:User;
  interview!:Interview;
  score!:number;
  id!:number;

  ngOnInit() {  
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.id=id;
      this.getOfferCandidacyById(id);
     this.interviewService.getInterviewByOfferCandidacyId(id).subscribe(
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
  getOfferCandidacyById(id: number) {
    this.offerCandidacyService.getOfferCandidacyById(id).subscribe(
      data => {
        this.offerCandidacy = data;
        if(data.score)
        this.score=data.score;

    });
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
  
  updateOfferCandidacyScore(F:NgForm){
    if(F.controls['score'])
    this.score = F.controls['score'].value;
    this.offerCandidacyService.updateOfferCandidacyScore(this.id, this.score).subscribe(() => {
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
