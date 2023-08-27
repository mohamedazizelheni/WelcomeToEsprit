import { Component, OnInit  } from '@angular/core';
import { Post } from 'src/app/models/post';
import { PostService } from 'src/app/services/postservice/post.service';

@Component({
  selector: 'app-postadm',
  templateUrl: './postadm.component.html',
  styleUrls: ['./postadm.component.css']
})
export class PostadmComponent implements OnInit {
  constructor(private postService: PostService) {}
  numPosts!: number;
  postCountByDay!: number;
  postCountByWeek!: number;
  postCountByMonth!: number;
  postCountByMonth1!: number;
  postCountByPeriod: any = {};
  topRatedPosts: Post[]=[];
  
  
  data: any;
    options: any;
  ngOnInit(): void {
    this.getTopRatedPosts();
    this.predictNumPosts();
    this.updateStatistics();

  
   // });
    
    
    

  }
   


  predictNumPosts() {
    this.postService.postPredict().subscribe(data => {
      this.numPosts = data['number of post'];
    });
  }
  
  updateStatistics() {
    this.postService.postStat().subscribe(data => {
      this.postCountByDay = data['today'];
      this.postCountByWeek = data['last_day'];
      this.postCountByMonth = data['last_week'];
      this.postCountByMonth1 = data['last_month'];
      this.char();
    });
  }
  getTopRatedPosts(): void {
    this.postService.topRated().subscribe(posts => {
      this.topRatedPosts = posts;
      console.log(posts);
    });
  }

  getClassForSentiment(sentiment: string) {
    switch(sentiment) {
      case 'positive':
        return 'bg-success';
      case 'neutre':
        return 'bg-primary';
      case 'negative':
        return 'bg-danger';
      default:
        return '';
    }
  }

  
  


  char(){
    const documentStyle = getComputedStyle(document.documentElement);
        const textColor = documentStyle.getPropertyValue('--text-color');

        this.data = {
            labels: ['today', 'last_day', 'last_week','last_month'],
            datasets: [
                {
                    data: [this.postCountByDay, this.postCountByWeek, this.postCountByMonth,this.postCountByMonth1],
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
  

