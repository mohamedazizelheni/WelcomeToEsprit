import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Achievement } from 'src/app/models/achievement';
import { AchievementService } from 'src/app/services/achievement.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-add-achievement',
  templateUrl: './add-achievement.component.html',
  styleUrls: ['./add-achievement.component.css']
})
export class AddAchievementComponent {
  constructor(private achievementService:AchievementService, private router: Router,private authService: AuthService,private route: ActivatedRoute){
    
  }
  role=this.authService.getRole();
evenId!:number;
  ngOnInit() {  
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.evenId=id;
    });
  }
  achievement!:Achievement;
  name: string = '';
  video!: File;
  loading = false;
  onVideoSelect(achievement: any) {
    this.video = achievement.target.files[0];
}
  
addAchievement(form: NgForm): void {
    this.name = form.controls['name'].value;
  

    this.achievementService.addAchievement(this.evenId,this.name,this.video).subscribe(data=> {
      
      this.router.navigate([`/admin/showEvent/${this.evenId}`]);


  })
  }

}
