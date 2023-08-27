import { Component } from '@angular/core';
import { FAQService } from 'src/app/services/faq.service';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FAQComponent {
  constructor(
    private faqService: FAQService,
  ) {
  }

  ngOnInit() {
    this.getFAQs();
  }

  faq: any;

  getFAQs() {
    this.faqService.getFAQs().subscribe(res => {
      this.faq = res;
      // console.log(this.faq);

    },
      error => {

      }
    )
  }

  deleteAd(id: any) {
    this.faqService.deleteFAQ(id).subscribe(res => {
      this.ngOnInit();

    },
      error => {

      }
    )
  }

  
}
