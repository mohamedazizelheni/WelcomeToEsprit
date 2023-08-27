import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAchievementComponent } from './show-achievement.component';

describe('ShowAchievementComponent', () => {
  let component: ShowAchievementComponent;
  let fixture: ComponentFixture<ShowAchievementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowAchievementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowAchievementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
