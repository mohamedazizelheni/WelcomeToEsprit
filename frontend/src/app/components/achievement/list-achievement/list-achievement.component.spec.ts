import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAchievementComponent } from './list-achievement.component';

describe('ListAchievementComponent', () => {
  let component: ListAchievementComponent;
  let fixture: ComponentFixture<ListAchievementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListAchievementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListAchievementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
