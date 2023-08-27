import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CampaignService } from 'src/app/services/campaign.service';

@Component({
  selector: 'app-create-campain',
  templateUrl: './create-campain.component.html',
  styleUrls: ['./create-campain.component.css']
})
export class CreateCampainComponent {
  campaign:any={
    id: 0,
    name:"",
  }
  
  
  constructor(
    private campaignService: CampaignService,
    private router: Router,
    private activeRoute: ActivatedRoute,
  ) {
    this.campaign.id = this.activeRoute.snapshot.paramMap.get("id");
  }

  ngOnInit() {
    if (this.campaign.id!=0 && this.campaign.id){
      this.getCampaignById();
    }
   
  }

Create(F: NgForm){

  console.log(this.campaign);

  this.campaignService.createCampaign(this.campaign).subscribe(res => {
    this.router.navigate(['/admin/campaigns']);
  }, error => {

  });
}

getCampaignById() {

  this.campaignService.getCampaignById(this.campaign.id).subscribe(res => {
    this.campaign = res;
    console.log(this.campaign);
    
  }, error => {

  });
}

Update(F: NgForm){
  this.campaign = {
    "id": this.activeRoute.snapshot.paramMap.get("id"),
    "logo": F.controls['logo'].value,
    "name": F.controls['name'].value,
    "numerotelephonique": F.controls['numerotelephonique'].value,
    "email": F.controls['email'].value,
    "urllinkedin": F.controls['urllinkedin'].value,
    "urlfacbook": F.controls['urlfacbook'].value,
  };
  this.campaignService.updateCampaign(this.campaign.id, this.campaign).subscribe(res => {
    this.router.navigate(['/admin/campaigns']);
  }, error => {
  
  });
}
}
