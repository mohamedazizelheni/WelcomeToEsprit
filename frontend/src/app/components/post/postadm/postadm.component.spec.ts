import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostadmComponent } from './postadm.component';

describe('PostadmComponent', () => {
  let component: PostadmComponent;
  let fixture: ComponentFixture<PostadmComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostadmComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostadmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
