import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute,Router } from '@angular/router';
import { EventService } from 'src/app/services/event.service';
import { Event } from 'src/app/models/event';


@Component({
  selector: 'app-update-event',
  templateUrl: './update-event.component.html',
  styleUrls: ['./update-event.component.css']
})
export class UpdateEventComponent {
  event!:Event;
  defaultName = 'Default Name';
  defaultDate = 'Default Date';
  defaultSpace = 'Default Space';
  defaultPlanning = 'Default Planning';

  
  constructor(private eventService:EventService,private route: ActivatedRoute, private router: Router){}
  
  ngOnInit() {

  this.route.paramMap.subscribe(params => {
    const id = Number(params.get('id'));
    this.eventService.getEventById(id).subscribe(event => {
      this.event = event;
    });
  });

  }

  updateEvent( F: NgForm){
    this.eventService.updateEvent(this.event).subscribe(() => {
      this.router.navigate(['/admin/showEvent', this.event.id]);
    });
  }
}
