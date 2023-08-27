import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CampaignService } from 'src/app/services/campaign.service';

@Component({
  selector: 'app-showcampaign',
  templateUrl: './showcampaign.component.html',
  styleUrls: ['./showcampaign.component.css']
})
export class ShowcampaignComponent {
  campaign: any = {
    id: 0,
    name: "",
  }


  constructor(
    private campaignService: CampaignService,
    private router: Router,
    private activeRoute: ActivatedRoute,
  ) {
    this.campaign.id = this.activeRoute.snapshot.paramMap.get("id");
  }

  ngOnInit() {
    this.getCampaignById();

  }



  getCampaignById() {

    this.campaignService.getCampaignById(this.campaign.id).subscribe(res => {
      this.campaign = res;
      console.log(this.campaign);

    }, error => {

    });
  }

}
