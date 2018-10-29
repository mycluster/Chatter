import { UserListComponent } from './list/list.component';
import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

const routes: Routes = [
   {path: 'list', component: UserListComponent} 
 ];
 
 @NgModule({
     imports: [RouterModule.forRoot(routes)],
     exports: [RouterModule]
 })
export class AppRoutingModule{}
export const routingComponents =[UserListComponent];