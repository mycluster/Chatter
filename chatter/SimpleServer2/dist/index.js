"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var chat_server_1 = require("./chat-server");
//creates a new chatterbox server
var app = new chat_server_1.ChatServer().getApp();
exports.app = app;
