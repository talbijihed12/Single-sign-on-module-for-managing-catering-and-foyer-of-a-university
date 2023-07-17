import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationRestaurantidebarComponent } from './ReservationRestaurant-sidebar.component';

describe('FlightSidebarComponent', () => {
  let component: ReservationRestaurantidebarComponent;
  let fixture: ComponentFixture<ReservationRestaurantidebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationRestaurantidebarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationRestaurantidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
