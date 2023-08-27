import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddOfferCandidacyComponent } from './add-offer-candidacy.component';

describe('AddOfferCandidacyComponent', () => {
  let component: AddOfferCandidacyComponent;
  let fixture: ComponentFixture<AddOfferCandidacyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddOfferCandidacyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddOfferCandidacyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
