import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetBookByIsbn } from './get-book-by-isbn';

describe('GetBookByIsbn', () => {
  let component: GetBookByIsbn;
  let fixture: ComponentFixture<GetBookByIsbn>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetBookByIsbn]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetBookByIsbn);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
