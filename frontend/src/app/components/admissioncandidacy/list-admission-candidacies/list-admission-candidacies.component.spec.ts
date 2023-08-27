import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAdmissionCandidaciesComponent } from './list-admission-candidacies.component';

describe('ListAdmissionCandidaciesComponent', () => {
  let component: ListAdmissionCandidaciesComponent;
  let fixture: ComponentFixture<ListAdmissionCandidaciesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListAdmissionCandidaciesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListAdmissionCandidaciesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
