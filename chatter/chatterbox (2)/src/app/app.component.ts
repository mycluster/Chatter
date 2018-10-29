import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'tcc-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
    fullImagePath: string;

    constructor(){
      this.fullImagePath = 'C:\Users\Associate\Documents\Jedi_Training\Building\Chatter\chatter\chatterbox\chatterbox\src\assets\chatter.png'
    }
  ngOnInit(): void {
  }

  private initModel(): void {
  }
}
