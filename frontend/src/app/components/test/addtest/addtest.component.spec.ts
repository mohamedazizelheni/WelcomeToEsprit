import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTestComponent } from './addtest.component';

describe('AddtestComponent', () => {
  let component: AddTestComponent ;
  let fixture: ComponentFixture<AddTestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddTestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
