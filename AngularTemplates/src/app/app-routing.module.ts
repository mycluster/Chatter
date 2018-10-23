import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ClassFormComponent } from './classForm/classForm.component';
import { noteFormComponent } from './noteForm/noteForm.component';
import { UserClassesComponent } from './user-classes/user-classes.component';
import { EditUserFormComponent } from './editUserForm/editUserForm.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: '',  component: LoginComponent },
  { path: 'noteform', component: noteFormComponent    },
  { path: 'classform',  component: ClassFormComponent },
  { path: 'userclasses', component: UserClassesComponent},
  { path: 'edituserform', component: EditUserFormComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {
  static components = [
    noteFormComponent,
    ClassFormComponent,  EditUserFormComponent, UserClassesComponent,
    LoginComponent, RegisterComponent
  ];
}
