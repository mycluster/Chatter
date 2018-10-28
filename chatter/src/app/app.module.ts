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
import { LoginComponent } from './Login/login.component';
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
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    MatSidenavModule,
    MatButtonModule,
    MatCardModule
  ],
  providers: [RegisterService, LoginService, ValidationService, User_Classes_Service,  Class_Form_Service, EditUser_Serv,NoteFormService],
  bootstrap: [AppComponent]
})
export class AppModule { }
