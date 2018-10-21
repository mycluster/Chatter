"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var chatterBox_server_1 = require("./chatterBox-server");
//creates a new chatterbox server
var app = new chatterBox_server_1.ChatterboxServer().getApp();
exports.app = app;
