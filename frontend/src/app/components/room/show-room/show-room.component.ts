import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Room } from 'src/app/models/room';
import { AuthService } from 'src/app/services/auth.service';
import { RoomService } from 'src/app/services/room.service';

@Component({
  selector: 'app-show-room',
  templateUrl: './show-room.component.html',
  styleUrls: ['./show-room.component.css']
})
export class ShowRoomComponent {
  @Input() room?: Room;
  imageSrc!: string;
  role!:string;


  constructor(private roomService: RoomService, private route: ActivatedRoute,private authService: AuthService) {}

  ngOnInit() {
    if(!this.room)
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
    this.roomService.getRoomById(id).subscribe(room => {
      if(room){
      this.room = room;
      this.imageSrc = `data:image/jpeg;base64,${this.room.map}`;
      }
    });
  });
  if(this.room)
  this.imageSrc = `data:image/jpeg;base64,${this.room.map}`;

  this.role=this.authService.getRole(); 

}
}
