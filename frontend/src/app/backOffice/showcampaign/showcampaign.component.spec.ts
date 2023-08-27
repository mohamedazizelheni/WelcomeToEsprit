import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowcampaignComponent } from './showcampaign.component';

describe('ShowcampaignComponent', () => {
  let component: ShowcampaignComponent;
  let fixture: ComponentFixture<ShowcampaignComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowcampaignComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowcampaignComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
