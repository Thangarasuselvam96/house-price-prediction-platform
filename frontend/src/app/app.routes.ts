import { Routes } from '@angular/router';
import {Home} from './features/home/home';
import {PropertyDetails} from './features/properties/property-details/property-details';
import {Favorites} from './features/favorites/favorites';
import {Login} from './features/auth/login/login';
import {authGuard} from './core/guards/auth-guard';
import {Register} from './features/auth/register/register';
import {PropertyList} from './features/properties/property-list/property-list';

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
    component: Favorites,
    canActivate: [authGuard]
  },
  {
    path: "login",
    component: Login
  },
  {
    path: "register",
    component: Register
  },
  {
    path: "properties",
    component: PropertyList
  }
];
