import {Component, OnInit} from '@angular/core';
import {PropertyCard} from '../../shared/components/property-card/property-card';
import {inject, ChangeDetectorRef} from '@angular/core';
import {Property, PropertyService} from '../../core/services/property';

@Component({
  selector: 'app-home',
  imports: [PropertyCard],
  templateUrl: './home.html',
  styleUrl: './home.scss',
})
export class Home implements OnInit {
    private propertyService = inject(PropertyService)
    private cdr = inject(ChangeDetectorRef);


  properties: Property[] = []
    ngOnInit(): void {
       this.propertyService.getProperties()
         .subscribe({
           next: response => {
             console.log(response.content);
             this.properties = response.content;
             this.cdr.detectChanges();
           },
           error: error => {
             console.error("Failed to load properties "+ error);
           }
         })
    }
}
