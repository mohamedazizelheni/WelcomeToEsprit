import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Event } from 'src/app/models/event';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.css']
})
export class AddEventComponent {
  event!:Event;
  dateValue: string;
  name: string = '';
  space: string = '';
  date: string = '';
  planning!: File;
  loading = false;
  
  constructor(private eventService:EventService, private router: Router){
    this.dateValue = new Date().toISOString().slice(0, 10);
  }
  ngOnInit() {  
  
  }
  onPlanningSelect(event: any) {
    this.planning = event.target.files[0];
}
  
  addEvent(form: NgForm): void {
    this.name = form.controls['name'].value;
    this.date = form.controls['date'].value;
    this.space = form.controls['space'].value;
  

    this.eventService.addEvent(this.name,this.space,this.dateValue,this.planning).subscribe(data=> {
      const  id=data.id;
      this.router.navigate([`/admin/invitationSpeaker/${id}`]);
      console.log(data)

  })
  }
}
