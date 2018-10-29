import { TestBed } from '@angular/core/testing';

import { NoteFormService } from './note-form.service';

describe('NoteFormService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NoteFormService = TestBed.get(NoteFormService);
    expect(service).toBeTruthy();
  });
});
