import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { EventService } from 'src/app/services/event.service';
import { Event } from 'src/app/models/event';
import { DomSanitizer } from '@angular/platform-browser';


@Component({
  selector: 'app-list-event',
  templateUrl: './list-event.component.html',
  styleUrls: ['./list-event.component.css']
})
export class ListEventComponent {
  eventList!:Event[];

  constructor(private router:Router,private eventService:EventService,private authService: AuthService,private sanitizer: DomSanitizer){}
  role=this.authService.getRole();
  event=new Event();

  showImage: boolean = false; // add this line
  showVideo: boolean = false;
  showPdf: boolean = false;
  ngOnInit(): void {
    this.getEvents();
    

  }
  getEvents(){
    this.eventService.getEvents().subscribe(
      data =>  this.eventList=data 
      );
  }
  deleteEvent(id:number){
    this.eventService.deleteEvents(id).subscribe(
      ()=> {
        this.getEvents();
        alert('Deleted!');
      }
    );
  }
  getDocLetterUrl() {
    return this.sanitizer.bypassSecurityTrustResourceUrl('data:application/pdf;base64,' + this.event.planning);
  }

  toggleImage() {
    this.showImage = !this.showImage;
    this.showVideo = false;
    this.showPdf = false;
  }
  toggleVideo() {
    this.showVideo = !this.showVideo;
    this.showImage = false;
    this.showPdf = false;
  }
  
  togglePdf() {
    this.showPdf = !this.showPdf;
    this.showImage = false;
    this.showVideo = false;
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
  getFileFormat(data: string): string {
    const signature = data.substring(0, 10);
    switch (signature) {
      case 'iVBORw0KGg': // PNG
        return 'png';
      case 'JVBERi0xLj': // PDF
        return 'pdf';
        case '/9j/4AAQSk': // JPEG
        return 'jpeg';
      default:
        return '';
    }
  }

  
  }
