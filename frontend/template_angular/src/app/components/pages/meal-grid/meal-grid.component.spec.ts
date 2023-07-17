import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MealGridComponent } from './meal-grid.component';

describe('MealGridComponent', () => {
  let component: MealGridComponent;
  let fixture: ComponentFixture<MealGridComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MealGridComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MealGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
