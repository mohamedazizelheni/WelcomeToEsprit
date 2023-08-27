import { Component } from '@angular/core';
import { Router, provideRouter } from '@angular/router';
import { Offer } from 'src/app/models/offer';
import { OfferCandidacy } from 'src/app/models/offer-candidacy';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { OfferCandidacyService } from 'src/app/services/offer-candidacy.service';
import { OfferService } from 'src/app/services/offer.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-list-offers',
  templateUrl: './list-offers.component.html',
  styleUrls: ['./list-offers.component.css']
})
export class ListOffersComponent {

  offerList!:Offer[];
  user!:User;
  mail=this.authService.getSubject();
  constructor(private router:Router,private offerService:OfferService,private authService: AuthService,private offerCandidacyService:OfferCandidacyService,private userService:UserService){}
  role=this.authService.getRole();
  ngOnInit(): void {
    this.getOffers();
    if (this.mail)
    this.userService.getUserbyMail(this.mail)
    .subscribe((user) => {
      this.user = user;
    });
  }

  getOffers(){
    this.offerService.getAlloffers().subscribe(
      data =>  this.offerList=data 
      );
  }
  deleteOffer(id:number){
    this.offerService.deleteOffer(id).subscribe(
      ()=> {
        this.getOffers();
        alert('Deleted!');
      }
    );
  }

  apply(offerId:number){
    this.offerCandidacyService.getOfferCandidaciesByOfferIdAndUserId(offerId,this.user.id).subscribe(
      (candidacy)=> {
        if(candidacy)
        this.router.navigate([`/offerCandidacy/show/${candidacy.id}`]);
        if(!candidacy)
        this.router.navigate([`/offerCandidacy/add/${offerId}`]);
      }
    );
  }
}
