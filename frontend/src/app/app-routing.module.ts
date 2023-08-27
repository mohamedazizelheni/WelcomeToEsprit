import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllTemplateAdminComponent } from './backOffice/all-template-admin/all-template-admin.component';
import { BodyAdminComponent } from './backOffice/body-admin/body-admin.component';
import { AllTemplateUserComponent } from './frontOffice/all-template-user/all-template-user.component';
import { BodyUserComponent } from './frontOffice/body-user/body-user.component';
import { AddOfferComponent } from './components/offer/add-offer/add-offer.component';
import { UpdateOfferComponent } from './components/offer/update-offer/update-offer.component';
import { ShowOfferComponent } from './components/offer/show-offer/show-offer.component';
import { ListOffersComponent } from './components/offer/list-offers/list-offers.component';
import { LoginComponent } from './components/user/login/login.component';
import { AdsManagementComponent } from './backOffice/ads-management/ads-management.component';
import { CreateAdComponent } from './backOffice/create-ad/create-ad.component';
import { FAQComponent } from './backOffice/faq/faq.component';
import { FAQComponentFront } from './frontOffice/faq/faq.component';
import { CreateFAQComponent } from './backOffice/create-faq/create-faq.component';
import { CampainsComponent } from './backOffice/campains/campains.component';
import { CreateCampainComponent } from './backOffice/create-campain/create-campain.component';
import { ShowadComponent } from './backOffice/showad/showad.component';
import { ShowcampaignComponent } from './backOffice/showcampaign/showcampaign.component';
import { ListPostComponent } from './components/post/list-post/list-post.component';
import { PostadmComponent } from './components/post/postadm/postadm.component';
import { AddEventComponent } from './components/event/add-event/add-event.component';
import { ListEventComponent } from './components/event/list-event/list-event.component';
import { UpdateEventComponent } from './components/event/update-event/update-event.component';
import { ShowEventComponent } from './components/event/show-event/show-event.component';
import { InvitationSpeakerComponent } from './components/invitation/invitation-speaker/invitation-speaker.component';
import { AddOfferCandidacyComponent } from './components/offerCandidacy/add-offer-candidacy/add-offer-candidacy.component';
import { ShowOfferCandidacyComponent } from './components/offerCandidacy/show-offer-candidacy/show-offer-candidacy.component';
import { ShowInterviewComponent } from './components/interview/show-interview/show-interview.component';
import { AddUserComponent } from './components/user/add-user/add-user.component';
import { UpdateUserComponent } from './components/user/update-user/update-user.component';
import { AddQuestionComponent } from './components/question/add-question/add-question.component';
import { ListQuestionComponent } from './components/question/list-question/list-question.component';
import { ShowQuestionComponent } from './components/question/show-question/show-question.component';
import { AddTestComponent } from './components/test/addtest/addtest.component'; 
import { UpdatetestComponent } from './components/test/updatetest/updatetest.component'; 
import { ShowtestComponent } from './components/test/showtest/showtest.component'; 
import { ListtestComponent } from './components/test/listtest/listtest.component';
import { ListRoleComponent } from './components/role/list-role/list-role.component';
import { ShowUserComponent } from './components/user/show-user/show-user.component';
import { ShowInvitationByEventComponent } from './components/invitation/show-invitation-by-event/show-invitation-by-event.component';
import { AddAchievementComponent } from './components/achievement/add-achievement/add-achievement.component';
import { ListAchievementComponent } from './components/achievement/list-achievement/list-achievement.component';
import { ShowAchievementComponent } from './components/achievement/show-achievement/show-achievement.component';
import { ShowRoomComponent } from './components/room/show-room/show-room.component';
import { AddRoomComponent } from './components/room/add-room/add-room.component';
import { ListOfferCandidaciesComponent } from './components/offerCandidacy/list-offer-candidacies/list-offer-candidacies.component';
import { DashadminComponent } from './components/user/userAdmin/dashadmin/dashadmin.component';
import { AddRoleComponent } from './components/role/add-role/add-role.component';
import { AddAdmissionCandidacyComponent } from './components/admissioncandidacy/add-admission-candidacy/add-admission-candidacy.component';
import { ShowAdmissionCandidacyComponent } from './components/admissioncandidacy/show-admission-candidacy/show-admission-candidacy.component';
import { AdmindashComponent } from './components/admissioncandidacy/admindash/admindash.component';
import { ListInterviewsComponent } from './components/interview/list-interviews/list-interviews.component';
import { TakeTestComponent } from './components/test/take-test/take-test.component';
import { ListAdmissionCandidaciesComponent } from './components/admissioncandidacy/list-admission-candidacies/list-admission-candidacies.component';

const routes: Routes = [
  {
    path:'admin',
    component:AllTemplateAdminComponent,
    children:[
      {
        path:'',
        component:BodyAdminComponent
      },
      {
        path:'user',
        component:DashadminComponent
      },
      {
        path:'addRole',
        component:AddRoleComponent
      },
      
      {
        path:'post',
        component:PostadmComponent
      },
      {
        path:'offer/list',
        component:ListOffersComponent
      },
      {
        path:'offer/add',
        component:AddOfferComponent
      },
      {
        path:'offer/update/:id',
        component:UpdateOfferComponent
      },
      {
        path:'offer/show/:id',
        component:ShowOfferComponent
      },
      {
        path:'getEvents',
        component:ListEventComponent
      },
      {
        path:'addEvent',
        component:AddEventComponent
      },
      {
        path:'addQuestion',
        component:AddQuestionComponent
      },
      {
        path:'addTest',
        component:AddTestComponent
      },
      {
        path:'updateTest',
        component: UpdatetestComponent
      },
      {
        path:'showTest',
        component: ShowtestComponent
      },
      
      {
        path:'listQuestion',
        component:ListQuestionComponent
      },
      {
        path:'listTest',
        component:ListtestComponent
      },
      {
        path:'showQuestion/:id',
        component:ShowQuestionComponent
      },
      {
        path:'updateEvent/:id',
        component:UpdateEventComponent
      },
      {
        path:'showEvent/:id',
        component:ShowEventComponent
      },
      {
        path:'invitationSpeaker/:id',
        component:InvitationSpeakerComponent
        
      },
      {
        path:'showInvitations/:id',
        component:ShowInvitationByEventComponent
        
      },
      {
        path:'addAchievement/:id',
        component:AddAchievementComponent
      },
      {
        path:'getAchievements',
        component:ListAchievementComponent
      },
      {
        path:'showAchievement/:id',
        component:ShowAchievementComponent
      },
  
      {
        path:'offerCandidacy/show/:id',
        component:ShowOfferCandidacyComponent
      },
      {
        path:'candidacy',
        component:AdmindashComponent
      },
      {
        path:'user/update/:id',
        component:UpdateUserComponent
      },
      {
        path:'interview/show/:id',
        component:ShowInterviewComponent
      },
      {
        path:'role/list',
        component:ListRoleComponent
      },
      {
        path:'user/show/:id',
        component:ShowUserComponent
      },
      {
        path:'room/show/:id',
        component:ShowRoomComponent
      },
      {
        path:'room/add',
        component:AddRoomComponent
      },
      {
        path:'offerCandidacy/list/:id',
        component:ListOfferCandidaciesComponent
      },
      {
        path:'ads',
        component:AdsManagementComponent,
      }, 
      {
        path:'ads/updatead/:id',
        component:CreateAdComponent,
      },  
      {
        path:'ads/show/:id',
        component:ShowadComponent,
      },  
      {
        path:'ads/createad',
        component:CreateAdComponent
      },
      {
        path:'faq',
        component:FAQComponent,
      }, 
      {
        path:'faq/createfaq',
        component:CreateFAQComponent
      },
      {
        path:'faq/updatefaq/:id',
        component:CreateFAQComponent
      },
      {
        path:'interview/list/:id',
        component:ListInterviewsComponent
      },
      {
        path:'showAdmissionCandidacy/:id',
        component:ShowAdmissionCandidacyComponent
      },
      {
        path:'admissionCandidacy/list',
        component:ListAdmissionCandidaciesComponent
      }
    ]
  },





  
  {
    path:'',
    component:AllTemplateUserComponent,
    children:[
      {
        path:'',
        component:LoginComponent
      },
     
      {
        path:'login',
        component:LoginComponent
      },
     
     
      
      {
        path:'post',
        component:ListPostComponent
        
      },
      
      
      {
        path:'offer/list',
        component:ListOffersComponent
      },
      {
        path:'showAdmissionCandidacy/:id',
        component:ShowAdmissionCandidacyComponent
      },
      {
        path:'login',
        component:LoginComponent
      },
      
      {
        path:'campaigns',
        component:CampainsComponent,
      }, 
      {
        path:'campaigns/createcampaign',
        component:CreateCampainComponent
      },
      {
        path:'campaigns/updatecampaign/:id',
        component:CreateCampainComponent
      },
      {
        path:'campaigns/show/:id',
        component:ShowcampaignComponent
      },
    
      {
        path:'user/add',
        component:AddUserComponent
      },
      {
        path:'offerCandidacy/add/:id',
        component:AddOfferCandidacyComponent
      },
      {
       path:'offer',
       component:ListOffersComponent
      },
      
      
      {
        path:'faq',
        component:FAQComponentFront,
      }, 
      {
        path:'offerCandidacy/show/:id',
        component:ShowOfferCandidacyComponent
      },
      {
        path:'interview/show/:id',
        component:ShowInterviewComponent
      },
      {
        path:'user/update/:id',
        component:UpdateUserComponent
      },
      {
        path:'user/show/:id',
        component:ShowUserComponent
      },
      {
        path:'admissionCandidacy/add',
        component:AddAdmissionCandidacyComponent
      },
      {
        path:'interview/list/:id',
        component:ListInterviewsComponent
      },
      {
        path:'takeTest/:id',
        component:TakeTestComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
