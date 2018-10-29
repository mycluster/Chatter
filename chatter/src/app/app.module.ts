<<<<<<< HEAD
import { IndexComponent } from './index/index.component';
import { UserService } from './services/user.service';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
//components
import { AppComponent } from './app.component';
<<<<<<< HEAD
<<<<<<< HEAD
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
=======
import { LoginComponent } from './Login/login.component';
import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';
import{AppRoutingModule,routingComponents} from './app-routing.module';
>>>>>>> 3adc630d7918d3d082ae67f55879c16bfbfdf006
=======
import { ChatterboxComponent } from './chatterbox/chatterbox.component';
import { UserComponent } from './user/user.component';
import { MessageComponent } from './message/message.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
>>>>>>> c2e845d3fabe0667683b77b0ee1740c25ebfdd86

//components
@NgModule({
  declarations: [
    AppComponent,
<<<<<<< HEAD
    LoginComponent,
<<<<<<< HEAD
    RegisterComponent
=======
    routingComponents,
    IndexComponent
>>>>>>> 3adc630d7918d3d082ae67f55879c16bfbfdf006
=======
//modules
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
// import { BrowserAnimationsModule } from '@angular/animations'
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatSidenavModule, MatCardModule, MatButtonModule } from '@angular/material';

//components
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { UserHomeComponent } from './user-home/user-home.component';
import { ClassFormComponent } from './class-form/class-form.component';

//services
import { RegisterService } from './services/register.service';
import { LoginService } from './services/login.service';
import { ValidationService } from './services/validation.service';
import { User_Classes_Service } from './services/user-classes.service';
import { Class_Form_Service } from './class-form/class-form.service';
import { EditUser_Serv } from './services/edit-user.service';
import { NoteFormService } from './services/note-form.service';

//routing
import { AppRoutingModule } from './app-routing.module';
import { routingComponents } from './app-routing.module';
import { EditUserComponent } from './edit-user/edit-user.component';
import { NavbarComponent } from './navbar/navbar.component';

//pipes
import { CamelToTitlePipe } from './pipes/camel-to-title.pipe';
import { UserClassesComponent } from './user-classes/user-classes.component';
import { NoteFormComponent } from './note-form/note-form.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    routingComponents,
    SidebarComponent,
    UserHomeComponent,
    ClassFormComponent,
    EditUserComponent,
    NavbarComponent,
    CamelToTitlePipe,
    UserClassesComponent,
    NoteFormComponent
>>>>>>> 78e6d76f3ded2a0287329aee6f0db53f4bdf8c10
  ],
  imports: [
    BrowserModule,
    FormsModule,
<<<<<<< HEAD
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule
   
=======
    ChatterboxComponent,
    UserComponent,
    MessageComponent
  ],
  imports: [
    BrowserModule,
    NgbModule
>>>>>>> c2e845d3fabe0667683b77b0ee1740c25ebfdd86
  ],
  providers: [UserService],
=======
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    MatSidenavModule,
    MatButtonModule,
    MatCardModule
  ],
  providers: [RegisterService, LoginService, ValidationService, User_Classes_Service,  Class_Form_Service, EditUser_Serv,NoteFormService],
>>>>>>> 78e6d76f3ded2a0287329aee6f0db53f4bdf8c10
  bootstrap: [AppComponent]
})
export class AppModule { }
