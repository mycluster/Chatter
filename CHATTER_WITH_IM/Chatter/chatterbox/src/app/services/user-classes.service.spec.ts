import { TestBed } from '@angular/core/testing';

import { UserClassesService } from './user-classes.service';

describe('UserClassesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserClassesService = TestBed.get(UserClassesService);
    expect(service).toBeTruthy();
  });
});
