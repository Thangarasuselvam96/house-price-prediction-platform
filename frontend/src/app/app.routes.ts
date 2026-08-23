import { Routes } from '@angular/router';
import {Home} from './features/home/home';
import {PropertyDetails} from './features/properties/property-details/property-details';
import {Favorites} from './features/favorites/favorites';
import {Login} from './features/auth/login/login';

export const routes: Routes = [
  {
    path: "",
    component: Home
  },
  {
    path: "properties/:id",
    component: PropertyDetails
  },
  {
    path: "favorites",
    component: Favorites
  },
  {
    path: "login",
    component: Login
  }
];
