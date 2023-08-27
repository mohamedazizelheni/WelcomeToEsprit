import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOfferCandidaciesComponent } from './list-offer-candidacies.component';

describe('ListOfferCandidaciesComponent', () => {
  let component: ListOfferCandidaciesComponent;
  let fixture: ComponentFixture<ListOfferCandidaciesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListOfferCandidaciesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListOfferCandidaciesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
