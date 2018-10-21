import { IndexComponent } from './index/index.component';
import { LoginComponent } from './Login/login.component';
import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';


const routes: Routes = [
   {path: 'Index/Login', component: LoginComponent}, 
   {path: 'Index', component: IndexComponent} 
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule{}
export const routingComponents =[LoginComponent, IndexComponent];