import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Event } from 'src/app/models/event';
import { EventService } from 'src/app/services/event.service';
import { DomSanitizer } from '@angular/platform-browser';
import { AchievementService } from 'src/app/services/achievement.service';
import { Achievement } from 'src/app/models/achievement';


@Component({
  selector: 'app-show-event',
  templateUrl: './show-event.component.html',
  styleUrls: ['./show-event.component.css']
})
export class ShowEventComponent {
  constructor(private eventService:EventService,private route: ActivatedRoute,private sanitizer: DomSanitizer, private achievementService:AchievementService){}
  event=new Event();
 
  showImage: boolean = false; // add this line
  showVideo: boolean = false;
  showPdf: boolean = false;
  achievements!:Achievement[];
  ngOnInit() {  
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.getEventById(id);
      this.achievementService.findAchievemntsByEventId(id).subscribe(data => {
        this.achievements = data;
      });;

    });
  }
  

  getEventById(id: number) {
    this.eventService.getEventById(id).subscribe(data => {this.event = data;});
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
