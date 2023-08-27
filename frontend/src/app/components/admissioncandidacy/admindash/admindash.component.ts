import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { AdmissionCandidacy } from 'src/app/models/admission-candidacy';
import { AdmissioncandidacyService } from 'src/app/services/admissioncandidacy.service';

@Component({
  selector: 'app-admindash',
  templateUrl: './admindash.component.html',
  styleUrls: ['./admindash.component.css']
})
export class AdmindashComponent implements OnInit {
  averages!: Map<string, number>;
    admissioncandidacy!:AdmissionCandidacy;

  constructor(private admissioncandidacyService: AdmissioncandidacyService){}
  ngOnInit(): void {
    this.getaveragespeciality();
    
  }

  getaveragespeciality(){
    this.admissioncandidacyService.getSpecialityTestAverages()
    .subscribe(data => {
      this.averages = new Map(Object.entries(data));      console.log(this.averages)
      this.updateChartData();    });
  }
  data: any;
  options: any;

  updateChartData(): void {
    const documentStyle = getComputedStyle(document.documentElement);
    const textColor = documentStyle.getPropertyValue('--text-color');

    this.data = {
      labels: Array.from(this.averages.keys()),
      datasets: [
        {
          label: 'Average test score',
          data: Array.from(this.averages.values()),
          backgroundColor: [documentStyle.getPropertyValue('--blue-500'), documentStyle.getPropertyValue('--yellow-500'), documentStyle.getPropertyValue('--green-500')],
          hoverBackgroundColor: [documentStyle.getPropertyValue('--blue-400'), documentStyle.getPropertyValue('--yellow-400'), documentStyle.getPropertyValue('--green-400')]
        }
      ]
    };
    this.options = {
      cutout: '60%',
      plugins: {
          legend: {
              labels: {
                  color: textColor
              }
          }
      }
  };
  }




}
