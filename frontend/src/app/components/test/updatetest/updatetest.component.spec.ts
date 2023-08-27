import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatetestComponent } from './updatetest.component';

describe('UpdatetestComponent', () => {
  let component: UpdatetestComponent;
  let fixture: ComponentFixture<UpdatetestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatetestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdatetestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
