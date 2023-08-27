import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { InvitationsService } from 'src/app/services/invitations.service';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { Invitation } from 'src/app/models/invitation';



@Component({
  selector: 'app-invitation-speaker',
  templateUrl: './invitation-speaker.component.html',
  styleUrls: ['./invitation-speaker.component.css']
})
export class InvitationSpeakerComponent {
  constructor(private router:Router,private invitationsService:InvitationsService,private authService: AuthService,private route: ActivatedRoute){}
  speakersList!:User[];
  role=this.authService.getRole();
  idEvent!:number;
  studentsList!:User[];


  ngOnInit(): void {
    this.getSpeakers();
    this.route.paramMap.subscribe(params => {
      const idEvent = Number(params.get('id'));
      this.idEvent=idEvent;
    });
    this.getStudents();
    this.route.paramMap.subscribe(params => {
      const idEvent = Number(params.get('id'));
      this.idEvent=idEvent;
    });

  }
  getSpeakers(){
    this.invitationsService.getSpeakers().subscribe(
      data =>  this.speakersList=data
      );
  }
  getStudents(){
    this.invitationsService.getStudents().subscribe(
      data =>  this.studentsList=data
      );
  }
  inviterSpeaker(id:number) {
    this.invitationsService.inviterSpeakers(this.idEvent, id)
      .subscribe((invitation: Invitation) => {
        console.log(invitation);
        alert('speaker invited!');
      });
  }
  inviterstudents(niveau:string) {
    this.invitationsService.inviterStudents(this.idEvent, niveau)
      .subscribe((invitations: Invitation[]) => {
        alert('students invited!');

        console.log(invitations);
      });
  }
}
