import { Component, ElementRef, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Chart } from 'chart.js';
import { OfferCandidacy } from 'src/app/models/offer-candidacy';
import { AuthService } from 'src/app/services/auth.service';
import { OfferCandidacyService } from 'src/app/services/offer-candidacy.service';

@Component({
  selector: 'app-list-offer-candidacies',
  templateUrl: './list-offer-candidacies.component.html',
  styleUrls: ['./list-offer-candidacies.component.css']
})
export class ListOfferCandidaciesComponent {
  offerCandidaciesList!:OfferCandidacy[];
  id!:number;
  @ViewChild('myCanvas') canvasRef!: ElementRef;
  private ctx!: CanvasRenderingContext2D;


  constructor(private router:Router,private offerCandidacyService:OfferCandidacyService,private authService: AuthService, private route: ActivatedRoute,){}
  role=this.authService.getRole();
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.id=id;
      this.getOfferCandidaciesByOfferId(id);
     
    });
  }
  getOfferCandidaciesByOfferId(id: number){
    this.offerCandidacyService.getOfferCandidaciesByOfferId(id).subscribe(
      data =>  this.offerCandidaciesList=data 
      );
  }
  onAccept(id:number){
    this.offerCandidacyService.updateOfferCandidacyStatus(id,"accepted").subscribe(() => {
      this.offerCandidacyService.getOfferCandidaciesByOfferId(this.id).subscribe(
        data =>  this.offerCandidaciesList=data 
        );

      });
  }
  onReject(id:number){
    this.offerCandidacyService.updateOfferCandidacyStatus(id,"rejected").subscribe(() => {
    this.offerCandidacyService.getOfferCandidaciesByOfferId(this.id).subscribe(
      data =>  this.offerCandidaciesList=data 
      );

    });

  }



  getGraphData() {
    this.offerCandidacyService.getOfferCandidacyStatisticsByDate(this.id).subscribe(data => {
      let labels = Object.keys(data);
      let values = Object.values(data);
      this.createGraph(labels, values);
    });
  }

  ngAfterViewInit() {
    this.ctx = this.canvasRef.nativeElement.getContext('2d');
    this.getGraphData();
  }
  createGraph(labels: any, values: any) {
    var myChart = new Chart(this.ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Graph',
                data: values,
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
  }
}
