import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Role } from 'src/app/models/role';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { RoleService } from 'src/app/services/role.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent {
  user!: User;
  role=this.authService.getRole();
  role1!:string;
  roleNames!: string[] ;
  mailUser!:string;
  mailConnected!:string;

  genders: string[] = ["Male", "Female"];
  bacSections: string[] = ["Math", "Science", "Technique", "Informatique"];
  educationLevels: string[] = ["Bac", "Bac+1", "Bac+2", "Bac+3", "Bac+4"];
  image!:File;
  image1!:File;
  imageSrc!: string;
  image0!:string;
  disableDelete=true;
  disableReset=true;
  real=false;

  birthDate!:string;
  hiringDate!:string;
  constructor(private userService: UserService, private cookieService:CookieService, private route: ActivatedRoute,private authService: AuthService,private http: HttpClient,private roleService:RoleService,private cookiesService:CookieService) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.mailConnected=this.authService.getSubject();
      const id = Number(params.get('id'));
      this.userService.getUserById(id).subscribe(user => {
        this.user = user;
        this.mailUser=user.mail;
        this.imageSrc = `data:image/jpeg;base64,${user.image}`;
        if(user.image0==""){
          this.real=true;
          this.disableDelete=false;
          }
          this.image0=user.image0;
          this.role1=user.role.role;

    });
  });
  if(this.role=='ADMIN')
this.roleService.getAllRoles().subscribe(
  (roles: Role[]) => {
    this.roleNames = roles.map(role => role.role);
  }
);  
}

  onIMageUpdate(event: any) {
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
    this.disableDelete=false;
    if(this.image0=="")
    this.disableReset=false;
    else
    this.disableReset=true;  
    this.user.image0='';
    this.real=true;

  }
  onGenderSelect(){

    if(!this.real)
    this.onIMageDelete(1);
  }

  onIMageReset(fileInput: any) {
        this.imageSrc = `data:image/jpeg;base64,${this.user.image}`;
        this.disableDelete=false;
        this.image = this.image1;
        this.user.image0="";
        fileInput.value = null;
        this.disableReset=true;
        this.real=true;
  }
  onIMageDelete(fileInput: any) {
    
    if(this.user.gender=='Male')
    this.user.image0='/assets/man.jpg';
    if(this.user.gender=='Female')
    this.user.image0='/assets/woman.jpg';
    if(this.user.image0)
        this.http.get(this.user.image0, { responseType: 'blob' }).subscribe((blob: Blob) => {
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
    
      if(fileInput.value)
      fileInput.value = null;
      if(this.image0=="")
      this.disableReset=false;
      else
      this.disableReset=true; 

      this.disableDelete=true;
      this.real=false;

  }

  updateUser(F:NgForm) {

    if(F.controls['cin'].value)
    this.user.cin = F.controls['cin'].value;
    if (F.controls['firstName'].value)
    this.user.firstName = F.controls['firstName'].value;
    if(F.controls['lastName'].value)
    this.user.lastName = F.controls['lastName'].value;
    if(F.controls['gender'].value)
    this.user.gender = F.controls['gender'].value;
    if(F.controls['phone'].value)
    this.user.phone = F.controls['phone'].value;
    if(F.controls['birthDate'].value)
    this.birthDate = F.controls['birthDate'].value;
    if(F.controls['address'].value)
    this.user.address = F.controls['address'].value;
    if(F.controls['mail'].value)
    this.user.mail = F.controls['mail'].value;

    if(F.controls['department'])
    this.user.department = F.controls['department'].value;

    if(F.controls['hiringDate'])
    this.hiringDate = F.controls['hiringDate'].value;

    if(F.controls['evaluator'])
    this.user.evaluator = F.controls['evaluator'].value;
    if(F.controls['jury'])
    this.user.jury = F.controls['jury'].value;
    if(F.controls['speaker'])
    this.user.speaker = F.controls['speaker'].value;

    if(F.controls['bacSection'].value)
    this.user.bacSection = F.controls['bacSection'].value;
    if(F.controls['educationLevel'].value)
    this.user.educationLevel = F.controls['educationLevel'].value;
    if(F.controls['speciality'].value)
    this.user.speciality = F.controls['speciality'].value;

    if(F.controls['classe'])
    this.user.classe = F.controls['classe'].value;

    if(F.controls['role2'])
    this.role1 = F.controls['role2'].value;

    if(this.real)
  this.user.image0="";
      this.userService.updateUser(this.user.id, this.user.cin, this.user.firstName, this.user.lastName, this.user.gender, this.image,this.user.image0, this.birthDate,this.user.address, this.user.phone, this.user.mail,this.user.department, this.hiringDate, this.user.evaluator, this.user.jury, this.user.speaker, this.user.bacSection, this.user.educationLevel, this.user.speciality,this.user.classe, this.role1)
        .subscribe(() => {
          if(this.mailConnected==this.mailUser){
            if(this.mailUser!=this.user.mail){
              this.cookieService.delete('token');
              this.cookieService.deleteAll();
              window.location.href = `http://localhost:4200/login`;
            }
            else{
              if(this.role=='ADMIN')
              window.location.href = `http://localhost:4200/admin/user/show/${this.user.id}`;
              else
              window.location.href = `http://localhost:4200/user/show/${this.user.id}`;    
            }
          }
          else
          window.location.href = `http://localhost:4200/admin/user/show/${this.user.id}`;
        });
  }

}
