
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Offer } from 'src/app/models/offer';
import { OfferService } from 'src/app/services/offer.service';

@Component({
  selector: 'app-update-offer',
  templateUrl: './update-offer.component.html',
  styleUrls: ['./update-offer.component.css']
})
export class UpdateOfferComponent {
  offer!:Offer;

  
  constructor(private offerService:OfferService,private route: ActivatedRoute, private router: Router){}
  
  ngOnInit() {

  this.route.paramMap.subscribe(params => {
    const id = Number(params.get('id'));
    this.offerService.getOfferById(id).subscribe(offer => {
      this.offer = offer;
    });
  });

  }

  updateOffer( F: NgForm){
    this.offerService.updateOffer(this.offer).subscribe(() => {
      this.router.navigate(['/admin/offer/show', this.offer.id]);
    });
  }
}
