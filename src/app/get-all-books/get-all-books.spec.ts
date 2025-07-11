import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllBooks } from './get-all-books';

describe('GetAllBooks', () => {
  let component: GetAllBooks;
  let fixture: ComponentFixture<GetAllBooks>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetAllBooks]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetAllBooks);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
