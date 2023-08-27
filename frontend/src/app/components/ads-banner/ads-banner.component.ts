import { Component } from '@angular/core';
import { AdsService } from 'src/app/services/ads.service';
import { interval } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-ads-banner',
  templateUrl: './ads-banner.component.html',
  styleUrls: ['./ads-banner.component.css']
})
export class AdsBannerComponent {
  constructor(
    private adService: AdsService,
    private cookieService : CookieService
  ) { 
  }
  item = 0
  Ads: any;

  lengthInt = 0

  obs$ = interval(10000)
  obs2$ = interval(1000)


  ngOnInit( ) { 
    this.getAds();
    this.obs2$.subscribe((b)=>{
      this.show = !this.show
    })  } 
  show : boolean = false

 

  getAds() {
    this.adService.getAds().subscribe(res => {
      this.Ads = res;
      this.Ads = this.Ads.filter((i:any)=>{
        return i.typeads == "V"
      })
      this.Ads = this.Ads.sort((a:any,b:any)=>{
        return b.cost - a.cost
      })
      if(!localStorage.getItem('tag'))
      this.adService.addVue(this.Ads[this.item].id).subscribe(res=>{
      })
      

      this.obs$.subscribe((d)=>{
        

        if(this.item == this.Ads.length-1 ){
          this.item = 0
        }else{
          this.item = this.item + 1
          if(this.lengthInt < this.Ads.length-1 ){
            if(!localStorage.getItem('tag')){
              this.adService.addVue(this.Ads[this.item].id).subscribe(res=>{
                this.lengthInt = this.lengthInt +1
              })
            }
          }else{
            localStorage.setItem('tag' , 'tag')
          }

        }
      })

      
      
    },
      error => {

      }
    )
  }
}
