import { ChatterboxServer } from './chatterBox-server';
//creates a new chatterbox server
let app = new ChatterboxServer().getApp();
//exports app for injection into the page
export { app };  
