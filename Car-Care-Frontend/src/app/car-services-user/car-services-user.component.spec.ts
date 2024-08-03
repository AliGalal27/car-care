import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarServicesUserComponent } from './car-services-user.component';

describe('CarServicesUserComponent', () => {
  let component: CarServicesUserComponent;
  let fixture: ComponentFixture<CarServicesUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarServicesUserComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarServicesUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
