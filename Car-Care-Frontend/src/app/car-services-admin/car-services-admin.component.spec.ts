import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarServicesAdminComponent } from './car-services-admin.component';

describe('CarServicesAdminComponent', () => {
  let component: CarServicesAdminComponent;
  let fixture: ComponentFixture<CarServicesAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarServicesAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarServicesAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
