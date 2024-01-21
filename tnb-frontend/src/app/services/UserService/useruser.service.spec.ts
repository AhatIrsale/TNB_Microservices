import { TestBed } from '@angular/core/testing';

import { UseruserService } from './useruser.service';

describe('UseruserService', () => {
  let service: UseruserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UseruserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
