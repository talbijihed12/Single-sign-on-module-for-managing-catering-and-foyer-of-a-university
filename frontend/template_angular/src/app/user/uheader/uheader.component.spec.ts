import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UHeaderComponent } from './uheader.component';

describe('UHeaderComponent', () => {
  let component: UHeaderComponent;
  let fixture: ComponentFixture<UHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UHeaderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
