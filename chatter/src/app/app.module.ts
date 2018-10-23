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
  ],
  imports: [
    BrowserModule,
    FormsModule,
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
  bootstrap: [AppComponent]
})
export class AppModule { }
