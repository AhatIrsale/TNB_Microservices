import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserTerainsComponent } from './user-terains.component';

describe('UserTerainsComponent', () => {
  let component: UserTerainsComponent;
  let fixture: ComponentFixture<UserTerainsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserTerainsComponent]
    });
    fixture = TestBed.createComponent(UserTerainsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
