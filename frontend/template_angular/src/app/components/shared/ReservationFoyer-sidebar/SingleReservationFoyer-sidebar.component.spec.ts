import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationFoyeridebarComponent } from './SingleReservationFoyer-sidebar.component';

describe('FlightSidebarComponent', () => {
  let component: ReservationFoyeridebarComponent;
  let fixture: ComponentFixture<ReservationFoyeridebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationFoyeridebarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationFoyeridebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
