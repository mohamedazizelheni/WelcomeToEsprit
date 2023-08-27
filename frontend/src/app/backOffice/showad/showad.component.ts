import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AdsService } from 'src/app/services/ads.service';
import { CampaignService } from 'src/app/services/campaign.service';

@Component({
  selector: 'app-showad',
  templateUrl: './showad.component.html',
  styleUrls: ['./showad.component.css']
})
export class ShowadComponent {
  ad: any={
    id:0
  };
  constructor(
    private adService: AdsService,
    private router: Router,
    private activeRoute: ActivatedRoute,
  ) {
    this.ad.id = this.activeRoute.snapshot.paramMap.get("id");
  }

  ngOnInit() {
    this.GetAdById();
  }

  GetAdById() {
    this.adService.getAdById(this.ad.id).subscribe(res => {
      this.ad = res;
    }, error => {

    });
  }
}




