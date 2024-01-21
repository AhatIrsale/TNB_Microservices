import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TerrainTaxesComponent } from './terrain-taxes.component';

describe('TerrainTaxesComponent', () => {
  let component: TerrainTaxesComponent;
  let fixture: ComponentFixture<TerrainTaxesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TerrainTaxesComponent]
    });
    fixture = TestBed.createComponent(TerrainTaxesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
