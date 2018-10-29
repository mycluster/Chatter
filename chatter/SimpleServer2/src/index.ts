import { ChatServer } from './chat-server';


//creates a new chatterbox server
let app = new ChatServer().getApp();
//exports app for injection into the page
export { app };  