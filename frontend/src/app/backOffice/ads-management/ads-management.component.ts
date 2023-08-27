import { Component } from '@angular/core';
import { AdsService } from 'src/app/services/ads.service';

@Component({
  selector: 'app-ads-management',
  templateUrl: './ads-management.component.html',
  styleUrls: ['./ads-management.component.css']
})
export class AdsManagementComponent {
  constructor(
    private adService: AdsService,
  ) {
  }

  ngOnInit() {
    this.getAds();
  }

  Ads: any;

  getAds() {
    this.adService.getAds().subscribe(res => {
      this.Ads = res;
      console.log(this.Ads[0]);

    },
      error => {

      }
    )
  }

  now = new Date();
  pdfname = "StatAds.pdf";
  EportPDF() {
    this.adService.getPDF(this.pdfname).subscribe(res => {


    },
      error => {

      }
    )
  }

  deleteAd(id: any) {
    this.adService.deleteAd(id).subscribe(res => {
      this.ngOnInit();

    },
      error => {

      }
    )
  }

}
