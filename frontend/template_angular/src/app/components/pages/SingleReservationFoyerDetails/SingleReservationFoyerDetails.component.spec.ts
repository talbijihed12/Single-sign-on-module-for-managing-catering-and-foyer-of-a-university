import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleReservationFoyerDetailsilsComponent } from './SingleReservationFoyerDetails.component';

describe('SingleReservationFoyerDetailsComponent', () => {
  let component: SingleReservationFoyerDetailsilsComponent;
  let fixture: ComponentFixture<SingleReservationFoyerDetailsilsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SingleReservationFoyerDetailsilsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SingleReservationFoyerDetailsilsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
