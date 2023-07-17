import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AddReservationRestaurantComponent } from './AddReservationRestaurant.component';

describe('ContactComponent', () => {
  let component: AddReservationRestaurantComponent;
  let fixture: ComponentFixture<AddReservationRestaurantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddReservationRestaurantComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddReservationRestaurantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
