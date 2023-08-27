import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AllTemplateAdminComponent } from './backOffice/all-template-admin/all-template-admin.component';
import { BodyAdminComponent } from './backOffice/body-admin/body-admin.component';
import { FooterAdminComponent } from './backOffice/footer-admin/footer-admin.component';
import { HeaderAdminComponent } from './backOffice/header-admin/header-admin.component';
import { SideAdminComponent } from './backOffice/side-admin/side-admin.component';
import { AllTemplateUserComponent } from './frontOffice/all-template-user/all-template-user.component';
import { BodyUserComponent } from './frontOffice/body-user/body-user.component';
import { FooterUserComponent } from './frontOffice/footer-user/footer-user.component';
import { HeaderUserComponent } from './frontOffice/header-user/header-user.component';
import { HttpClientModule, HTTP_INTERCEPTORS  } from '@angular/common/http';
import { AddOfferComponent } from './components/offer/add-offer/add-offer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UpdateOfferComponent } from './components/offer/update-offer/update-offer.component';
import { ShowOfferComponent } from './components/offer/show-offer/show-offer.component';
import { ListOffersComponent } from './components/offer/list-offers/list-offers.component';
import { LoginComponent } from './components/user/login/login.component';
import { TokenInterceptor } from './services/tokenInterceptor';
import { AdsComponent } from './components/ads/ads.component';
import { AdsBannerComponent } from './components/ads-banner/ads-banner.component';
import { AdsManagementComponent } from './backOffice/ads-management/ads-management.component';
import { CreateAdComponent } from './backOffice/create-ad/create-ad.component';
import { FAQComponentFront } from './frontOffice/faq/faq.component';
import {MatExpansionModule} from '@angular/material/expansion';
import { CreateFAQComponent } from './backOffice/create-faq/create-faq.component';
import { CampainsComponent } from './backOffice/campains/campains.component';
import { CreateCampainComponent } from './backOffice/create-campain/create-campain.component';
import { CommonModule } from '@angular/common';
import { FAQComponent } from './backOffice/faq/faq.component';
import { ShowadComponent } from './backOffice/showad/showad.component';
import { ShowcampaignComponent } from './backOffice/showcampaign/showcampaign.component';
import { PostComponent } from './components/post/post.component';
import { ListPostComponent } from './components/post/list-post/list-post.component';
import { AddPostComponent } from './components/post/add-post/add-post.component';
import { PostCommentComponent } from './components/post/post-comment/post-comment.component';
import { PostRatingComponent } from './components/post/post-rating/post-rating.component';
import { StarRatingModule } from 'angular-star-rating';
import { NgxStarsModule } from 'ngx-stars';
import { ToastrModule } from 'ngx-toastr';
import { PostadmComponent } from './components/post/postadm/postadm.component';
import { ChartsModule } from 'angular-bootstrap-md';
import { NgChartsModule } from 'ng2-charts';
import { ChartModule } from 'primeng/chart';
import { MessagesModule } from 'primeng/messages';
import { RatingModule } from 'primeng/rating';
import { SpeedDialModule } from 'primeng/speeddial';
import { AddEventComponent } from './components/event/add-event/add-event.component';
import { ShowEventComponent } from './components/event/show-event/show-event.component';
import { UpdateEventComponent } from './components/event/update-event/update-event.component';
import { ListEventComponent } from './components/event/list-event/list-event.component';
import { DateFormatPipe } from './date-format.pipe';
import { InvitationSpeakerComponent } from './components/invitation/invitation-speaker/invitation-speaker.component';
import { AddOfferCandidacyComponent } from './components/offerCandidacy/add-offer-candidacy/add-offer-candidacy.component';
import { ShowOfferCandidacyComponent } from './components/offerCandidacy/show-offer-candidacy/show-offer-candidacy.component';
import { NgxExtendedPdfViewerModule } from 'ngx-extended-pdf-viewer';
import { ShowInterviewComponent } from './components/interview/show-interview/show-interview.component';
import { AddUserComponent } from './components/user/add-user/add-user.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
import { UpdateUserComponent } from './components/user/update-user/update-user.component';
import {AddQuestionComponent } from './components/question/add-question/add-question.component';
import { ListQuestionComponent } from './components/question/list-question/list-question.component';
import { ShowQuestionComponent } from './components/question/show-question/show-question.component';
import { AddTestComponent } from './components/test/addtest/addtest.component';
import { UpdatetestComponent } from './components/test/updatetest/updatetest.component';
import { ShowtestComponent } from './components/test/showtest/showtest.component';
import { ListtestComponent } from './components/test/listtest/listtest.component';

import { MatDialogModule } from '@angular/material/dialog';
import { ListRoleComponent } from './components/role/list-role/list-role.component';
import { ShowUserComponent } from './components/user/show-user/show-user.component';
import { ShowInvitationByEventComponent } from './components/invitation/show-invitation-by-event/show-invitation-by-event.component';
import { AddAchievementComponent } from './components/achievement/add-achievement/add-achievement.component';
import { ListAchievementComponent } from './components/achievement/list-achievement/list-achievement.component';
import { ShowAchievementComponent } from './components/achievement/show-achievement/show-achievement.component';
import { AddRoomComponent } from './components/room/add-room/add-room.component';
import { ShowRoomComponent } from './components/room/show-room/show-room.component';
import { ListRoomsComponent } from './components/room/list-rooms/list-rooms.component';
import { UpdateRoomComponent } from './components/room/update-room/update-room.component';
import { ListOfferCandidaciesComponent } from './components/offerCandidacy/list-offer-candidacies/list-offer-candidacies.component';
import { DashadminComponent } from './components/user/userAdmin/dashadmin/dashadmin.component';
import { AddRoleComponent } from './components/role/add-role/add-role.component';
import { AddAdmissionCandidacyComponent } from './components/admissioncandidacy/add-admission-candidacy/add-admission-candidacy.component';
import { ShowAdmissionCandidacyComponent } from './components/admissioncandidacy/show-admission-candidacy/show-admission-candidacy.component';
import { AdmindashComponent } from './components/admissioncandidacy/admindash/admindash.component';
import { SidebarComponent } from './frontOffice/sidebar/sidebar.component';
import { ListInterviewsComponent } from './components/interview/list-interviews/list-interviews.component';
import { TakeTestComponent } from './components/test/take-test/take-test.component';
import { ListAdmissionCandidaciesComponent } from './components/admissioncandidacy/list-admission-candidacies/list-admission-candidacies.component';



@NgModule({
  declarations: [
    AppComponent,
    AllTemplateAdminComponent,
    BodyAdminComponent,
    FooterAdminComponent,
    HeaderAdminComponent,
    SideAdminComponent,
    AllTemplateUserComponent,
    BodyUserComponent,
    FooterUserComponent,
    HeaderUserComponent,
    AddOfferComponent,
    UpdateOfferComponent,
    ShowOfferComponent,
    ListOffersComponent,
    LoginComponent,
    AdsComponent,
    AdsBannerComponent,
    AdsManagementComponent,
    CreateAdComponent,
    FAQComponentFront,
    CreateFAQComponent,
    CampainsComponent,
    CreateCampainComponent,
    FAQComponent,
    ShowadComponent,
    ShowcampaignComponent,
  
    PostComponent,
    ListPostComponent,
    AddPostComponent,
    PostCommentComponent,
    PostRatingComponent,
    PostadmComponent,
    AddEventComponent,
    ShowEventComponent,
    UpdateEventComponent,
    ListEventComponent,
    DateFormatPipe,
    InvitationSpeakerComponent,
    AddOfferCandidacyComponent,
    ShowOfferCandidacyComponent,
    ShowInterviewComponent,
    AddUserComponent,
    UpdateUserComponent,
    AddQuestionComponent,
    ListQuestionComponent,
    ShowQuestionComponent,
    AddTestComponent,
    UpdatetestComponent,
    ShowtestComponent,
    ListtestComponent,
    ListRoleComponent,
    ShowUserComponent,
    ShowInvitationByEventComponent,
    AddAchievementComponent,
    ListAchievementComponent,
    ShowAchievementComponent,  
    AddRoomComponent,
    ShowRoomComponent,
    ListRoomsComponent,
    UpdateRoomComponent,
    AddAdmissionCandidacyComponent,
    ShowAdmissionCandidacyComponent,
    AdmindashComponent,
    ListOfferCandidaciesComponent,
    AddRoleComponent,
    AddAdmissionCandidacyComponent,
    DashadminComponent,
    AddRoleComponent,
    SidebarComponent,
    ListInterviewsComponent,
    TakeTestComponent,
    ListAdmissionCandidaciesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatExpansionModule,
    
    StarRatingModule.forRoot(),
    NgxStarsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    ChartsModule,
    NgChartsModule,
    ChartModule,
    MessagesModule,
    RatingModule,
    SpeedDialModule,
    NgxExtendedPdfViewerModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  exports: [
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
