import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Invitation } from 'src/app/models/invitation';
import { AuthService } from 'src/app/services/auth.service';
import { EventService } from 'src/app/services/event.service';
import { InvitationsService } from 'src/app/services/invitations.service';

@Component({
  selector: 'app-show-invitation-by-event',
  templateUrl: './show-invitation-by-event.component.html',
  styleUrls: ['./show-invitation-by-event.component.css']
})
export class ShowInvitationByEventComponent {

  invitations!:Invitation[];
  name!:string;
  
  constructor(private router:Router,private invitationsService:InvitationsService,private authService: AuthService,private sanitizer: DomSanitizer,private route: ActivatedRoute,private eventService:EventService){}
  role=this.authService.getRole();
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.eventService.getEventById(id).subscribe(event => {
        this.name = event.name;
        this.getInvitationsByEvent(event.name);

      });
    });
  }

  onPresenceChanged(id: number,present:boolean) {
    this.invitationsService.onPresenceChanged(id, present).subscribe(
      () => {
        console.log('Invitation mise à jour avec succès :');
     
      }
      
    );
  }

  getInvitationsByEvent(name:string){
    this.invitationsService.getInvitationsByEvent(name).subscribe(
      data =>  this.invitations=data 
      );
  }
  

}
