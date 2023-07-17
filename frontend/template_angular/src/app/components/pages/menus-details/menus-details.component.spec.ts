import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenusDetailsComponent } from './menus-details.component';

describe('FlightsDetailsComponent', () => {
  let component: MenusDetailsComponent;
  let fixture: ComponentFixture<MenusDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenusDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MenusDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
