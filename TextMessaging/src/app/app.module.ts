import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
<<<<<<< HEAD
    AppRoutingModule
=======
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule
   
>>>>>>> 78e6d76f3ded2a0287329aee6f0db53f4bdf8c10
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
