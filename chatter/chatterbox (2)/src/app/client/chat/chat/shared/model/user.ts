//The user interface takes in two parameters id 
//and name. The name is populated with the name 
//the user provides when prompted via a pop-up
//upon opening the chatter box.The id is randomly 
//generated in the socket.service.ts file.
export interface User {
   id?: number;
   name?: string;
}