import { TestBed } from '@angular/core/testing';

import { AdmissioncandidacyService } from './admissioncandidacy.service';

describe('AdmissioncandidacyService', () => {
  let service: AdmissioncandidacyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdmissioncandidacyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
