import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ChatComponent } from './client/chat/chat/chat.component';
import { DialogUserComponent} from './client/chat/chat/dialog-user/dialog-user.component'

const routes: Routes = [
  {
    path: '', component: ChatComponent
  },
  {
    path: '', component: DialogUserComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
