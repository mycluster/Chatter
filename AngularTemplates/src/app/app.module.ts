import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

//services
import { RegisterService } from './services/register.service';
import { LoginService } from './services/login.service';
    

//components
import { AppComponent }  from './app.component';
import { AppRoutingModule } from './app-routing.module';

import { CamelToTitlePipe } from './shared/camel-to-title.pipe';
import { UserClassesComponent } from './user-classes/user-classes.component';

@NgModule({
  imports:      [ BrowserModule, FormsModule, ReactiveFormsModule, AppRoutingModule ],
  declarations: [ AppComponent, AppRoutingModule.components,  CamelToTitlePipe ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
