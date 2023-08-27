import { Component } from '@angular/core';
import { CampaignService } from 'src/app/services/campaign.service';

@Component({
  selector: 'app-campains',
  templateUrl: './campains.component.html',
  styleUrls: ['./campains.component.css']
})
export class CampainsComponent {

  Campaigns:any;

  constructor(
    private campaignService: CampaignService,
  ) {
  }

  ngOnInit() {
    this.getCampaigns();
  }


  getCampaigns() {
    this.campaignService.getCampaigns().subscribe(res => {
      this.Campaigns = res;
      console.log(this.Campaigns);

    },
      error => {

      }
    )
  }

  deleteCampaign(id: any) {
    this.campaignService.deleteCampaign(id).subscribe(res => {
      this.ngOnInit();

    },
      error => {

      }
    )
  }
}
