import { Component, OnInit } from '@angular/core';
import { UserComponent } from '../user/user.component';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  constructor(private from: UserComponent, private content: string) { }

  ngOnInit() {
  }

}
