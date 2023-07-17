import { TestBed } from '@angular/core/testing';

import { SingleReservationFoyerCosumerService } from './SingleReservationFoyer-cosumer.service';

describe('ProductCosumerService', () => {
  let service: SingleReservationFoyerCosumerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SingleReservationFoyerCosumerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
