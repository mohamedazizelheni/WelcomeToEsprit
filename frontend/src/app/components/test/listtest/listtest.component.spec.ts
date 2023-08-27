import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListtestComponent } from './listtest.component';

describe('ListtestComponent', () => {
  let component: ListtestComponent;
  let fixture: ComponentFixture<ListtestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListtestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListtestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
