import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerWalletsComponent } from './customer-wallets.component';

describe('CustomerWalletsComponent', () => {
  let component: CustomerWalletsComponent;
  let fixture: ComponentFixture<CustomerWalletsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CustomerWalletsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerWalletsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
