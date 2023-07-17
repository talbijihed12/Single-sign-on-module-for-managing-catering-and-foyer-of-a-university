import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UFooterComponent } from './ufooter.component';

describe('UFooterComponent', () => {
  let component: UFooterComponent;
  let fixture: ComponentFixture<UFooterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UFooterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
