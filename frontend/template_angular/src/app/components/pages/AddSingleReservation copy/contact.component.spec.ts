import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSingleReservationFoyerComponent } from './contact.component';

describe('ContactComponent', () => {
  let component: AddSingleReservationFoyerComponent;
  let fixture: ComponentFixture<AddSingleReservationFoyerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddSingleReservationFoyerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSingleReservationFoyerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
