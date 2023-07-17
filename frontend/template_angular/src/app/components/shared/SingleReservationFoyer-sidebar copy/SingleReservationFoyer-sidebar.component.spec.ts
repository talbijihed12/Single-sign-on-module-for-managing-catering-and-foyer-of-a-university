import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleReservationFoyersidebarComponent } from './SingleReservationFoyer-sidebar.component';

describe('FlightSidebarComponent', () => {
  let component: SingleReservationFoyersidebarComponent;
  let fixture: ComponentFixture<SingleReservationFoyersidebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SingleReservationFoyersidebarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SingleReservationFoyersidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
