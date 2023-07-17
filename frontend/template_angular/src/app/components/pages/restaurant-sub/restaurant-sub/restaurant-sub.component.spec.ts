import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantSubComponent } from './restaurant-sub.component';

describe('RestaurantSubComponent', () => {
  let component: RestaurantSubComponent;
  let fixture: ComponentFixture<RestaurantSubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RestaurantSubComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RestaurantSubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
