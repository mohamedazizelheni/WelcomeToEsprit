import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Offer } from 'src/app/models/offer';
import { OfferCandidacy } from 'src/app/models/offer-candidacy';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { InterviewService } from 'src/app/services/interview.service';
import { OfferCandidacyService } from 'src/app/services/offer-candidacy.service';
import { OfferService } from 'src/app/services/offer.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-offer-candidacy',
  templateUrl: './add-offer-candidacy.component.html',
  styleUrls: ['./add-offer-candidacy.component.css']
})
export class AddOfferCandidacyComponent {
  loading = false;

  establishment: string = '';
  docDiploma?: File;
  docCV?: File;



  
  docLetter?: File;
  days!: string[];
  hours!: string[];
  day: string = '';
  day1: string = '';
  hour: string = '';

  docCvValid=false;
  docDiplomaValid=false;
  docLetterValid=false;

  cvSrc!:string;
  diplomaSrc!:string;
  letterSrc!:string;


  constructor(private authService: AuthService,private offerCandidacyService:OfferCandidacyService, private offerService:OfferService, private interviewService:InterviewService ,private router: Router,private route: ActivatedRoute){}
  idOffer!:number;
  
  ngOnInit() {  
    this.route.paramMap.subscribe(params => {
      const idOffer = Number(params.get('id'));
      this.idOffer=idOffer;
    });
    this.interviewService.getDays(1).subscribe(days => {
      this.days = days;
    });
  }

/****** */
onDocCVSelect(event: any) {
  const file = event.target.files[0];
  const reader = new FileReader();

  reader.onload = () => {
    if (reader.result !== null) {
      const base64String = reader.result.toString().split(',')[1];
      this.cvSrc = `data:image/jpeg;base64,${base64String}`;
    }
  };
  if(file){
    reader.readAsDataURL(file);
    this.docCV = event.target.files[0];
  }
  this.docCvValid = event.target.files != null && event.target.files.length > 0;
}

onDocDiplomaSelect(event: any) {
  const file = event.target.files[0];
    const reader = new FileReader();
    reader.onload = () => {
      if (reader.result !== null) {
        const base64String = reader.result.toString().split(',')[1];
        this.diplomaSrc = `data:image/jpeg;base64,${base64String}`;
    };
}
if(file){
  reader.readAsDataURL(file);
  this.docDiploma = event.target.files[0];
}
this.docDiplomaValid = event.target.files != null && event.target.files.length > 0;
}
onDocLetterSelect(event: any) {
  const file = event.target.files[0];
  const reader = new FileReader();
  reader.onload = () => {
    if (reader.result !== null) {
      const base64String = reader.result.toString().split(',')[1];
      this.letterSrc = `data:image/jpeg;base64,${base64String}`;
  };
  }
  if(file){
    reader.readAsDataURL(file);
    this.docLetter = event.target.files[0];
  }
    this.docLetterValid = event.target.files != null && event.target.files.length > 0;
}


    
addOfferCandidacy(F:NgForm) {

  this.establishment = F.controls['establishment'].value;
  this.day = F.controls['day'].value;
  this.hour = F.controls['hour'].value;

  if (this.docDiploma instanceof File && this.docCV instanceof File && this.docLetter instanceof File && this.docCvValid && this.docDiplomaValid && this.docLetterValid) {
    
    this.loading = true;
    this.offerCandidacyService.addOfferCandidacy(this.idOffer, this.establishment, this.docDiploma, this.docCV, this.docLetter, this.day, this.hour)
      .subscribe(
        data => {
          const idOfferCandidacyCreated=data.id;
          this.router.navigate([`/offerCandidacy/show/${idOfferCandidacyCreated}`]);
          this.loading = false;
      }
      );
  }

}

updateHours() {
  this.interviewService.getHours(this.day1, 1).subscribe(hours => {
    this.hours = hours;
  });
}
getFileFormat(data: string): string {
  const signature = data.substring(0, 10);
  switch (signature) {
    case 'iVBORw0KGg': // PNG
      return 'png';
    case 'JVBERi0xLj': // PDF
      return 'pdf';
      case '/9j/4AAQSk': // JPEG
      return 'jpeg';
    default:
      return '';
  }
}
getFileFormat2(fileName: string) {
  if (fileName.endsWith('.jpeg') || fileName.endsWith('.jpg') || fileName.endsWith('.JPG') || fileName.endsWith('.JPEG')) {
    return 'jpg';
  } else if (fileName.endsWith('.png') || fileName.endsWith('.PNG')) {
    return 'png';
  } else if (fileName.endsWith('.pdf') || fileName.endsWith('.PDF')) {
    return 'pdf';
  } else {
    return 'Unknown';
  }
}

}

