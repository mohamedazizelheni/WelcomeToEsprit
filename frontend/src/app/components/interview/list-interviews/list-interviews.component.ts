import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Interview } from 'src/app/models/interview';
import { AuthService } from 'src/app/services/auth.service';
import { InterviewService } from 'src/app/services/interview.service';

@Component({
  selector: 'app-list-interviews',
  templateUrl: './list-interviews.component.html',
  styleUrls: ['./list-interviews.component.css']
})
export class ListInterviewsComponent {
  interviewList!:Interview[];

  constructor(private router:Router,private route: ActivatedRoute,private interviewService:InterviewService,private authService: AuthService){}
  role=this.authService.getRole();
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.getInterviewsByUserId(id);
    });
  }
  getInterviewsByUserId(id:number){
    this.interviewService.getInterviewsByUserId(id).subscribe(
      data =>  this.interviewList=data 
      );
  }
}
