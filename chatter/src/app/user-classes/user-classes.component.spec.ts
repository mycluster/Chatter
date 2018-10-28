import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserClassesComponent } from './user-classes.component';

describe('UserClassesComponent', () => {
  let component: UserClassesComponent;
  let fixture: ComponentFixture<UserClassesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserClassesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserClassesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
