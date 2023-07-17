import { TestBed } from '@angular/core/testing';

import { ProductCosumerService } from './product-cosumer.service';

describe('ProductCosumerService', () => {
  let service: ProductCosumerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductCosumerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
