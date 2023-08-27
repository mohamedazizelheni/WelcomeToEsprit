import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-dashadmin',
  templateUrl: './dashadmin.component.html',
  styleUrls: ['./dashadmin.component.css']
})
export class DashadminComponent implements OnInit {
  users!:User[];
  constructor(private userService: UserService) { }
  ngOnInit(): void {
    this.getUser();
  }

getUser(){
  this.userService.getUsers().subscribe(users=>{
    this.users= users;
    console.log(users)
  })
}



deleteUser(id:number){
  this.userService.deleteUserById(id).subscribe(
    ()=> {
      this.getUser();
      alert('Deleted!');
    }
  );
}



}
