//modules
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

//components
import { ChatComponent } from './client/chat/chat/chat.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { UserClassesComponent } from './user-classes/user-classes.component';
import { NoteFormComponent } from './note-form/note-form.component';
import { ClassFormComponent } from './class-form/class-form.component';

const routes: Routes = [
    {path: '', component: LoginComponent},
    {path: 'Login', component: LoginComponent},
    {path: 'Register', component: RegisterComponent},
    {path: 'ClassForm', component: ClassFormComponent},
    {path: 'EditUser', component: EditUserComponent},
    {path: 'UserClasses', component: UserClassesComponent},
    {path: 'NoteForm', component: NoteFormComponent},
    {path: 'Chat', component: ChatComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
export const routingComponents=[
  LoginComponent,
  ChatComponent,
  RegisterComponent,
  ClassFormComponent,
  EditUserComponent,
  UserClassesComponent,
  NoteFormComponent
];