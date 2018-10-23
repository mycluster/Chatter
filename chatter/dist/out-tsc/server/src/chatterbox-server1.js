import { createServer } from 'http';
import * as express from 'express';
import * as socketIo from 'socket.io';
var ChatterboxServer = /** @class */ (function () {
    function ChatterboxServer() {
        this.createApp();
        this.config();
        this.createServer();
        this.sockets();
        this.listen();
    }
    ChatterboxServer.prototype.createApp = function () {
        this.app = express();
    };
    ChatterboxServer.prototype.createServer = function () {
        this.server = createServer(this.app);
    };
    ChatterboxServer.prototype.config = function () {
        this.port = process.env.PORT || ChatterboxServer.PORT;
    };
    ChatterboxServer.prototype.sockets = function () {
        this.io = socketIo(this.server);
    };
    ChatterboxServer.prototype.listen = function () {
        var _this = this;
        this.server.listen(this.port, function () {
            console.log('Chatterbox is currently running on port %s', _this.port);
        });
        this.io.on('connect', function (socket) {
            console.log('Client connected on port %s.', _this.port);
            socket.on('message', function (m) {
                console.log('[server](message): %s', JSON.stringify(m));
                _this.io.emit('message', m);
            });
            socket.on('disconnect', function () {
                console.log('Client has been disconnected');
            });
        });
    };
    ChatterboxServer.prototype.getApp = function () {
        return this.app;
    };
    ChatterboxServer.PORT = 8085; //we may want to change this
    return ChatterboxServer;
}());
export { ChatterboxServer };
//# sourceMappingURL=chatterbox-server1.js.map