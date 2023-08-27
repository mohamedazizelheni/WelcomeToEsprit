import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowInvitationByEventComponent } from './show-invitation-by-event.component';

describe('ShowInvitationByEventComponent', () => {
  let component: ShowInvitationByEventComponent;
  let fixture: ComponentFixture<ShowInvitationByEventComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowInvitationByEventComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowInvitationByEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
