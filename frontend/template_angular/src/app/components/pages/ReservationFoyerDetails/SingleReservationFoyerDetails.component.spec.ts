import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationFoyerDetailsilsComponent } from './SingleReservationFoyerDetails.component';

describe('ReservationFoyerDetailsComponent', () => {
  let component: ReservationFoyerDetailsilsComponent;
  let fixture: ComponentFixture<ReservationFoyerDetailsilsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationFoyerDetailsilsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationFoyerDetailsilsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
