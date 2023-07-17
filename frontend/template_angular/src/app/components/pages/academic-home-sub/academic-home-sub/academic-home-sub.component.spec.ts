import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcademicHomeSubComponent } from './academic-home-sub.component';

describe('AcademicHomeSubComponent', () => {
  let component: AcademicHomeSubComponent;
  let fixture: ComponentFixture<AcademicHomeSubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcademicHomeSubComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AcademicHomeSubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
