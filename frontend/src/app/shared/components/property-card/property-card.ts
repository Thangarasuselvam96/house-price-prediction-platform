import {Component, Input} from '@angular/core';
import {Property} from '../../../core/services/property';
import {DecimalPipe} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-property-card',
  imports: [DecimalPipe, RouterLink],
  templateUrl: './property-card.html',
  styleUrl: './property-card.scss',
})
export class PropertyCard {
  @Input({ required: true })
  property !: Property;
}
