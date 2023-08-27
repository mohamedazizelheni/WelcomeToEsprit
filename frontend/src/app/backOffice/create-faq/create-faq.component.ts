import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { FAQService } from 'src/app/services/faq.service';

@Component({
  selector: 'app-create-faq',
  templateUrl: './create-faq.component.html',
  styleUrls: ['./create-faq.component.css']
})
export class CreateFAQComponent {

  constructor(
    private faqService: FAQService,
    private router: Router,
    private activeRoute: ActivatedRoute,
  ) {
    this.faq.id = this.activeRoute.snapshot.paramMap.get("id");
  }
  sub:any

  ngOnInit() {
    if (this.faq.id!=0 && this.faq.id){
      this.getFAQById();
    }
   
  }

faq:any={
  id: 0,
  question:"",
  answer: ""
}

Create(F: NgForm){
  this.faq = {
    "id": 0,
    "subject": this.sub,
    "question": F.controls['question'].value,
    "answer": F.controls['answer'].value,
  };
  console.log(this.faq);

  this.faqService.createFAQ(this.faq).subscribe(res => {
    this.router.navigate(['/admin/faq']);
  }, error => {

  });
}

getFAQById() {
  // this.F.controls['name'].setValue = this.ad.name
  // this.F.controls['category'].setValue = this.ad.categorieads
  // this.F.controls['urlpic'].setValue = this.ad.content
  // this.F.controls['cost'].setValue = this.ad.cost
  // this.F.controls['startdate'].setValue = this.ad.startDate
  // this.F.controls['enddate'].setValue = this.ad.endDate
  // this.F.controls['socity'].setValue = this.ad.socity
  // this.F.controls['nbviews'].setValue = this.ad.requestedNbViews
  // this.F.controls['type'].setValue = this.ad.typeads


  this.faqService.getFAQById(this.faq.id).subscribe(res => {
    this.faq = res;
    console.log(this.faq);
    
  }, error => {

  });
}

Update(F: NgForm){
  this.faq = {
    "id": this.activeRoute.snapshot.paramMap.get("id"),
    "subject": 1,
    "question": F.controls['question'].value,
    "answer": F.controls['answer'].value,
  };
  this.faqService.updateFAQ(this.faq.id, this.faq).subscribe(res => {
    this.router.navigate(['/admin/faq']);
  }, error => {
  
  });
}

}
