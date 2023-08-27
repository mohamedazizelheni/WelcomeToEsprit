import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowOfferCandidacyComponent } from './show-offer-candidacy.component';

describe('ShowOfferCandidacyComponent', () => {
  let component: ShowOfferCandidacyComponent;
  let fixture: ComponentFixture<ShowOfferCandidacyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowOfferCandidacyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowOfferCandidacyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
