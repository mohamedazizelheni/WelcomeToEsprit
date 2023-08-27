import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { RoomService } from 'src/app/services/room.service';

@Component({
  selector: 'app-add-room',
  templateUrl: './add-room.component.html',
  styleUrls: ['./add-room.component.css']
})
export class AddRoomComponent {
  constructor(private roomService:RoomService,private router: Router,private http: HttpClient){}
  name!:string;
  block!:string;
  image!:File;
  imageSrc!: string;
  imageValid=false;

  onImageSelect(event: any) {
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.onload = () => {
      if (reader.result !== null) {
        const base64String = reader.result.toString().split(',')[1];
        this.imageSrc = `data:image/jpeg;base64,${base64String}`;
      }
    };
    if(file){
      reader.readAsDataURL(file);
      this.image=file;
    }
    this.imageValid = event.target.files != null && event.target.files.length > 0;
  }



  addRoom(F:NgForm) {
    this.name = F.controls['name'].value;
    this.block = F.controls['block'].value;

    if (this.image instanceof File && this.imageValid) {
      this.roomService.addRoom(this.name, this.block, this.image)
        .subscribe(
          (data)=>{
            window.location.href = `http://localhost:4200/admin/room/show/${data.id}`;
          }
        );
        }
  
    }
}
