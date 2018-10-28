import { NoteFormComponent } from './note-form/note-form.component';
import { ClassFormComponent } from './class-form/class-form.component';
import { UserHomeComponent } from './user-home/user-home.component';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { UserClassesComponent } from './user-classes/user-classes.component';

const routes: Routes = [
    {path: '', component: LoginComponent},
    {path: 'Login', component: LoginComponent},
    {path: 'Register', component: RegisterComponent},
    {path: 'UserHome', component: UserHomeComponent},
    {path: 'ClassForm', component: ClassFormComponent},
    {path: 'EditUser', component: EditUserComponent},
    {path: 'UserClasses', component: UserClassesComponent},
    {path: 'NoteForm', component: NoteFormComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule{}
export const routingComponents=[
    LoginComponent,
    RegisterComponent,
    UserHomeComponent,
    ClassFormComponent,
    EditUserComponent,
    UserClassesComponent,
    NoteFormComponent
];