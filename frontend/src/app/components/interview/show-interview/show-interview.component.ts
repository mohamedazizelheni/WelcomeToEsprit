import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Interview } from 'src/app/models/interview';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { InterviewService } from 'src/app/services/interview.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-show-interview',
  templateUrl: './show-interview.component.html',
  styleUrls: ['./show-interview.component.css']
})
export class ShowInterviewComponent {
   interview!: Interview;
   mail=this.authService.getSubject();
   user!:User;
   role=this.authService.getRole();
  constructor(private interviewService: InterviewService,private route: ActivatedRoute, private authService:AuthService,private userService:UserService){}
  
  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.getInterviewById(id);
    });
    if (this.mail)
    this.userService.getUserbyMail(this.mail)
    .subscribe((data) => {
      if(data)
      this.user = data;
    });
  }
  getInterviewById(id: number) {
    this.interviewService.getInterviewById(id).subscribe(
      data => {
        this.interview = data;
    });
  }
}
