import { TestBed } from '@angular/core/testing';

import { SingleReservationFoyerService } from './SingleReservationFoyer.service';

describe('SingleReservationFoyerService', () => {
  let service: SingleReservationFoyerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SingleReservationFoyerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
