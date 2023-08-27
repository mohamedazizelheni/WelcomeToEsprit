import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAdmissionCandidacyComponent } from './show-admission-candidacy.component';

describe('ShowAdmissionCandidacyComponent', () => {
  let component: ShowAdmissionCandidacyComponent;
  let fixture: ComponentFixture<ShowAdmissionCandidacyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowAdmissionCandidacyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowAdmissionCandidacyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
