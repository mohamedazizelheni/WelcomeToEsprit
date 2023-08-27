import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAdmissionCandidacyComponent } from './add-admission-candidacy.component';

describe('AddAdmissionCandidacyComponent', () => {
  let component: AddAdmissionCandidacyComponent;
  let fixture: ComponentFixture<AddAdmissionCandidacyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddAdmissionCandidacyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddAdmissionCandidacyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
