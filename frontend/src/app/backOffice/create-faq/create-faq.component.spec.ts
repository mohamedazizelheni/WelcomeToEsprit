import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateFAQComponent } from './create-faq.component';

describe('CreateFAQComponent', () => {
  let component: CreateFAQComponent;
  let fixture: ComponentFixture<CreateFAQComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateFAQComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateFAQComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
