import { Component, Input } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Achievement } from 'src/app/models/achievement';
import { AchievementService } from 'src/app/services/achievement.service';

@Component({
  selector: 'app-show-achievement',
  templateUrl: './show-achievement.component.html',
  styleUrls: ['./show-achievement.component.css']
})
export class ShowAchievementComponent {
  constructor(private achievementService:AchievementService,private route: ActivatedRoute,private sanitizer: DomSanitizer){}
  achievement=new Achievement();
  @Input() id!:number;
  showVideo: boolean = false;
  ngOnInit() {  
    if(this.id)
    this.getAchievementById(this.id);
    if(!this.id)
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.getAchievementById(id);
    });
  }
  

  getAchievementById(id: number) {
    this.achievementService.getAchievementById(id).subscribe(data => {this.achievement = data;

    });
  }


  toggleVideo() {
    this.showVideo = !this.showVideo;
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

}
