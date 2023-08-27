import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdsService } from 'src/app/services/ads.service';
import { CampaignService } from 'src/app/services/campaign.service';

@Component({
  selector: 'app-create-ad',
  templateUrl: './create-ad.component.html',
  styleUrls: ['./create-ad.component.css']
})
export class CreateAdComponent {
  F!: NgForm;
  ad: any = {
    "id": 0,
    "name": "",
    "categorieads": null,
    "content": "",
    "cost": "",
    "startDate": "",
    "endDate": "",
    "socity": "",
    "requestedNbViews": "",
    "typeads": null,
    "socityy":{
      "id": 0
    }
  };

  Campaigns: any;
  constructor(
    private adService: AdsService,
    private campaignService: CampaignService,
    private router: Router,
    private activeRoute: ActivatedRoute,
  ) {
    this.ad.id = this.activeRoute.snapshot.paramMap.get("id");
  }

  ngOnInit() {
    if (this.ad.id!=0 && this.ad.id){
      this.GetAdById(); 
    }
    this.getCampaigns();
  }

  CreateAd(F: NgForm) {

    console.log(this.ad);

    this.adService.createAd(this.ad).subscribe(res => {
      this.router.navigate(['/admin/ads']);
    }, error => {

    });
  }

  GetAdById() {
    this.adService.getAdById(this.ad.id).subscribe(res => {
      this.ad = res;
    }, error => {

    });
  }

  UpdateAd(F: NgForm) {
    this.ad = {
      "id": this.ad.id,
      "name": F.controls['name'].value,
      // "categorieads" : F.controls['category'].value,
      "categorieads": 1,
      "content": F.controls['urlpic'].value,
      "cost": F.controls['cost'].value,
      "startDate": F.controls['startdate'].value,
      "endDate": F.controls['enddate'].value,
     // "socity": F.controls['socity'].value,
      "requestedNbViews": F.controls['nbviews'].value,
      // "typeads" : F.controls['type'].value,
      "typeads": 1,
    };
    console.log(this.ad);

    this.adService.updateAd(this.ad.id, this.ad).subscribe(res => {
      this.router.navigate(['/admin/ads']);
    }, error => {

    });
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
}
