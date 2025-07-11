import { TestBed } from '@angular/core/testing';

import { LoginRegister } from './login-register';

describe('LoginRegister', () => {
  let service: LoginRegister;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoginRegister);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
