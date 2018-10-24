var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
var MessageService = /** @class */ (function () {
    function MessageService(http) {
        this.http = http;
        this.httpOptions = {
            headers: new HttpHeaders({ 'Content-Type': 'application/json' })
        };
    }
    MessageService.prototype.selectAllMessage = function () {
        return this.http.get();
    };
    MessageService.prototype.selectMostRecentMessage = function () {
        return this.http.get(http); //localhost:8085/Chatter/selectMostRecentMessage)
    };
    MessageService.prototype.insertMessage = function (message) {
        var body = new HttpParams();
        var headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
        body = body.set('username', username);
        body = body.set('message', message);
    };
    MessageService.prototype.deleteMessageById = function (id) {
        var body = new HttpParams();
        var headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
        body = body.set('message', null);
    };
    MessageService.prototype.updateNote = function (id) {
        var body = new HttpParams();
        var headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
        body = body.set('message', message);
    };
    MessageService = __decorate([
        Injectable({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [HttpClient])
    ], MessageService);
    return MessageService;
}());
export { MessageService };
//# sourceMappingURL=message.service.js.map