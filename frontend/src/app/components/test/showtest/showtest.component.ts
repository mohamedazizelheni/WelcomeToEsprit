import { Component } from '@angular/core';
import { TestService } from 'src/app/services/test.service';
import { ActivatedRoute } from '@angular/router';
import { Test } from 'src/app/models/test';

@Component({
  selector: 'app-showtest',
  templateUrl: './showtest.component.html',
  styleUrls: ['./showtest.component.css']
})
export class ShowtestComponent {
  constructor(private testService:TestService,private route: ActivatedRoute){}
test=new Test();
  ngOnInit() {  
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.getTestById(id);
      
    });

}
getTestById(id: number) {
  this.testService.getTestById(id).subscribe(data => {this.test = data;});
}
}
