import { TestBed } from '@angular/core/testing';

import { ClassFormService } from './class-form.service';

describe('ClassFormService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ClassFormService = TestBed.get(ClassFormService);
    expect(service).toBeTruthy();
  });
});
