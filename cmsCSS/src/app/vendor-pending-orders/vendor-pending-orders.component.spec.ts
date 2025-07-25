import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VendorPendingOrdersComponent } from './vendor-pending-orders.component';

describe('VendorPendingOrdersComponent', () => {
  let component: VendorPendingOrdersComponent;
  let fixture: ComponentFixture<VendorPendingOrdersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VendorPendingOrdersComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VendorPendingOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
