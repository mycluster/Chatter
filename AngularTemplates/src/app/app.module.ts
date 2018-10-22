import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent }  from './app.component';
import { AppRoutingModule } from './app-routing.module';

import { CamelToTitlePipe } from './shared/camel-to-title.pipe';

@NgModule({
  imports:      [ BrowserModule, FormsModule, ReactiveFormsModule, AppRoutingModule ],
  declarations: [ AppComponent, AppRoutingModule.components,  CamelToTitlePipe ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
