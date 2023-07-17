import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClaimsListAdminComponent } from './claims-list-admin.component';

describe('ClaimsListAdminComponent', () => {
  let component: ClaimsListAdminComponent;
  let fixture: ComponentFixture<ClaimsListAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClaimsListAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClaimsListAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
