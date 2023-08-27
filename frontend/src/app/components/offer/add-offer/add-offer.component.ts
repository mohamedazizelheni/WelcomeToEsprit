import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Offer } from 'src/app/models/offer';
import { OfferService } from 'src/app/services/offer.service';

@Component({
  selector: 'app-add-offer',
  templateUrl: './add-offer.component.html',
  styleUrls: ['./add-offer.component.css']
})
export class AddOfferComponent {
  offer!:Offer;
  constructor(private offerService:OfferService, private router: Router){}
  addOffer( F: NgForm){
    this.offer = new Offer();
    this.offer.speciality = F.controls['speciality'].value;
    this.offer.description= F.controls['description'].value;    
    this.offerService.addOffer(this.offer).subscribe(() => {
      this.router.navigate(['/admin/offer/list']);
    });
  }
}
