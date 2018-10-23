import { Component, OnInit } from '@angular/core';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { UserComponent } from '../user/user.component';

@Component({
  selector: 'app-chatterbox',
  templateUrl: './chatterbox.component.html',
  styleUrls: ['./chatterbox.component.css']
})
export class ChatterboxComponent extends Message {

  constructor(from: UserComponent, content: string) { }
  super(from, content);

  ngOnInit() {
  }

}
