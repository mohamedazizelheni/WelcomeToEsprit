import { Component } from '@angular/core';
import { FAQService } from 'src/app/services/faq.service';
import { interval } from 'rxjs';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FAQComponentFront {
  constructor(
    private faqService: FAQService,
  ) {
  }
  panelOpenState = false;
  obs$ = interval(1000)
  show : boolean = false

  ngOnInit() {
    this.getFAQs();
    this.getSubjetcs();
    this.obs$.subscribe((b)=>{
      console.log('5')
      this.show = !this.show
    })
  }

  Faq: any=[];
  getFAQs() {
    this.faqService.getFAQs().subscribe(res => {
      this.Faq = res;
      for(let i=0;i<this.Faq.length; i++){
        
        this.getups(this.Faq[i].id)

      }

    },
      error => {

      }
    )
  }

  up(id:any) {
    this.faqService.up(id).subscribe(res => {

      this.getups(id);

    },
      error => {

      }
    )
  }

  down(id:any) {
    this.faqService.down(id).subscribe(res => {
      this.getups(id);
      this.Faq.sort();
    },
      error => {

      }
    )
  }

  getups(id:any) {
    this.faqService.getups(id).subscribe(res => {
      for(let i=0; i<this.Faq.length;i++){
        if (this.Faq[i].id==id){
          this.Faq[i].nbUps = res;
        }
      }

      console.log(this.Faq.sort((a:any,b:any) => b.nbUps - a.nbUps));

      
    },
      error => {

      }
    )
  }

  subjects:any
  selectedSub:any;
  getSubjetcs() {

    this.faqService.getSubjects().subscribe(res => {
      console.log(res);
      this.subjects = res;
    },
      error => {

      }
    )
  }
}
