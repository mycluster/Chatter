//modules
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatSidenavModule, MatCardModule, MatButtonModule } from '@angular/material';
import { SharedModule } from './client/shared/shared.module';
import { ChatModule } from '../app/client/chat/chat/chat.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

//components
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ClassFormComponent } from './class-form/class-form.component';

//services
import { RegisterService } from './services/register.service';
import { LoginService } from './services/login.service';
import { ValidationService } from './services/validation.service';
import { UserClassesService } from './services/user-classes.service';
import { ClassFormService } from './services/class-form.service';
import { EditUserService } from './services/edit-user.service';
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

//other
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    routingComponents,
    ClassFormComponent,
    EditUserComponent,
    NavbarComponent,
    CamelToTitlePipe,
    UserClassesComponent,
    NoteFormComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    SharedModule,
    ChatModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    MatSidenavModule,
    MatButtonModule,
    MatCardModule
    
  ],
  providers: [
    RegisterService,
    LoginService,
    ValidationService,
    UserClassesService,
    ClassFormService,
    EditUserService,
    NoteFormService
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent]
})
export class AppModule { }
