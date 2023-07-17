import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MealsGridComponent } from './meals-grid.component';

describe('HotelsGridComponent', () => {
  let component: MealsGridComponent;
  let fixture: ComponentFixture<MealsGridComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MealsGridComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MealsGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
