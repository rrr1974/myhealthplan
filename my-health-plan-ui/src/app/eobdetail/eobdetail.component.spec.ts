import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EobdetailComponent } from './eobdetail.component';

describe('EobdetailComponent', () => {
  let component: EobdetailComponent;
  let fixture: ComponentFixture<EobdetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EobdetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EobdetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
