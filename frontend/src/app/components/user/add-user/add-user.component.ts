import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
}
)
export class AddUserComponent {
  image!:File;
  imageSrc!: string;
  disabled=true;
  real=false;

  constructor(private cookieService: CookieService, private userService:UserService,private router: Router, private authService:AuthService,private http: HttpClient){}
  ngOnInit(): void {  
    if(this.authService.isLoggedIn()){
    this.logout();
    this.cookieService.delete('token');
    this.cookieService.deleteAll();
    }
  }
  
  cin!:string;
  firstName!:string;
  lastName!:string;
  gender!:string;
  address!:string;
  birthDate!:string;
  phone!:string;
  mail!:string;
  image0!:string;
  password!:string;
  bacSection!:string;
  educationLevel!:string;
  speciality!:string;
  genders: string[] = ["Male", "Female"];
  bacSections: string[] = ["Math", "Science", "Technique", "Informatique"];
  educationLevels: string[] = ["Bac", "Bac+1", "Bac+2", "Bac+3", "Bac+4"];

  onImageSelect(event: any) {
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.onload = () => {
      if (reader.result !== null) {
        const base64String = reader.result.toString().split(',')[1];
        this.imageSrc = `data:image/jpeg;base64,${base64String}`;
      }
    };
    reader.readAsDataURL(file);
    this.image=file;
    this.disabled=false;
    this.real=true;
    this.image0="";
  }
onGenderSelect(gender:string){
  if(gender=='Male')
  this.image0='/assets/man.jpg';
  if(gender=='Female')
  this.image0='/assets/woman.jpg';
  if(!this.real)
  this.onIMageDelete(1);
}
  onIMageDelete(fileInput: any) {

    if(this.image0!=""){
    this.http.get(this.image0, { responseType: 'blob' }).subscribe((blob: Blob) => {
      const reader = new FileReader();
      reader.onload = () => {
        if (reader.result !== null) {
          const base64String = reader.result.toString().split(',')[1];
          this.imageSrc = `data:image/jpeg;base64,${base64String}`;
        }
      };
      reader.readAsDataURL(blob);
      this.image = new File([blob], 'picture.jpg', { type: 'image/jpg' });
    });
  }
    else{
    this.imageSrc="";
    }
    if(fileInput.value)
    fileInput.value = null;
    this.disabled=true;
    this.real=false;

  }

  addUser(F:NgForm) {
  this.cin = F.controls['cin'].value;
  this.firstName = F.controls['firstName'].value;
  this.lastName = F.controls['lastName'].value;
  this.gender = F.controls['gender'].value;
  this.phone = F.controls['phone'].value;
  this.address = F.controls['address'].value;
  this.mail = F.controls['mail'].value;
  this.password = F.controls['password'].value;
  this.bacSection = F.controls['bacSection'].value;
  this.educationLevel = F.controls['educationLevel'].value;
  this.speciality = F.controls['speciality'].value;
  if(this.real)
  this.image0="";
  console.log(this.real);
  console.log(this.image0);
  if (this.image instanceof File) {
    this.userService.addUser(this.cin, this.firstName, this.lastName, this.gender, this.image,this.image0, this.birthDate, this.address, this.phone, this.mail, this.password, this.bacSection, this.educationLevel, this.speciality)
      .subscribe(
        ()=>{
          this.cookieService.delete('token');
          this.cookieService.deleteAll();
          window.location.href = 'http://localhost:4200/login';
        }
      );
      }

  }
  logout(){
    this.authService.logout().subscribe(() => {
      this.cookieService.delete('token');
      this.cookieService.deleteAll();
      window.location.href = 'http://localhost:4200/user/add';
    });
  }

}
