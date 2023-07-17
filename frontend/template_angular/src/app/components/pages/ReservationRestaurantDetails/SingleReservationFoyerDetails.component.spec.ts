import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationRestaurantDetailsilsComponent } from './SingleReservationFoyerDetails.component';

describe('ReservationFoyerDetailsComponent', () => {
  let component: ReservationRestaurantDetailsilsComponent;
  let fixture: ComponentFixture<ReservationRestaurantDetailsilsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationRestaurantDetailsilsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationRestaurantDetailsilsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
