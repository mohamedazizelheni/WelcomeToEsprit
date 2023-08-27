import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvitationSpeakerComponent } from './invitation-speaker.component';

describe('InvitationSpeakerComponent', () => {
  let component: InvitationSpeakerComponent;
  let fixture: ComponentFixture<InvitationSpeakerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InvitationSpeakerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InvitationSpeakerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
