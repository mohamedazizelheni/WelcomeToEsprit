import { TestBed } from '@angular/core/testing';

import { OfferCandidacyService } from './offer-candidacy.service';

describe('OfferCandidacyService', () => {
  let service: OfferCandidacyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OfferCandidacyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
