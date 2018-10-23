var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component, ViewChild, ElementRef } from '@angular/core';
import { MatDialog, MatList } from '@angular/material';
import { Action } from './shared/model/action';
import { Event } from './shared/model/event';
import { SocketService } from './shared/services/socket.service';
var WSP = 'https://tools.ietf.org/html/rfc6455';
var ChatterboxComponent = /** @class */ (function () {
    function ChatterboxComponent(socketService, dialog) {
        this.socketService = socketService;
        this.dialog = dialog;
        this.action = Action;
        this.messages = [];
        this.defaultDialogUserParams = {
            disableClose: true,
            data: {
                title: 'Welcome to Chatterbox',
                dialogType: DialogUserType.NEW
            }
        };
    }
    ChatterboxComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.initModel();
        setTimeout(function () {
            _this.openUserPopup(_this.this.defaultDialogUserParams);
        }, 0);
    };
    ChatterboxComponent.prototype.ngAfterViewInit = function () {
        var _this = this;
        this.MatListItem.changes.subscribe(function (elements) {
            _this.scrollToBottom();
        });
    };
    ChatterboxComponent.prototype.scrollToBottom = function () {
        try {
            this.matList.nativeElement.scrollTop = this.matList.nativeElement.scrollHeight;
        }
        catch (err) {
        }
    };
    ChatterboxComponent.prototype.initModel = function () {
        var randomId = this.getRandomId();
        this.user = {
            id: randomId
        };
    };
    ChatterboxComponent.prototype.initIoConnection = function () {
        var _this = this;
        this.socketService.initSocket();
        this.ioConnection = this.this.socketService.onMessage()
            .subscribe(function (message) {
            _this.messages.push(message);
        });
        this.socketService.onEvent(Event.CONNECT)
            .subscribe(function () {
            console.log('User has been disconnected');
        });
    };
    ChatterboxComponent.prototype.getRandomId = function () {
        return Math.floor(Math.random() * 999999) + 1;
    };
    ChatterboxComponent.prototype.onClickUserInfo = function () {
        this.openUserPopup({
            data: {
                username: this.user.name,
                title: 'Edit details',
                dialogType: DialogUserType.EDIT
            }
        });
    };
    ChatterboxComponent.prototype.sendMessage = function (message) {
        if (!message) {
            return;
        }
        this.socketService.send({
            from: this.user,
            content: message
        });
        this.messageContent = null;
    };
    ChatterboxComponent.prototype.sendNotification = function (params, action) {
        var message;
        if (action === Action.JOINED) {
            message = {
                from: this.user,
                action: action,
                content: {
                    username: this.user.name,
                    previousUsername: params.previousUsername
                }
            };
        }
        this.socketService.send(message);
    };
    __decorate([
        ViewChild(MatList, { read: ElementRef }),
        __metadata("design:type", ElementRef)
    ], ChatterboxComponent.prototype, "matList", void 0);
    ChatterboxComponent = __decorate([
        Component({
            selector: 'app-chat',
            templateUrl: './chat.component.html',
            styleUrls: ['./chat.component.css']
        }),
        __metadata("design:paramtypes", [SocketService, MatDialog])
    ], ChatterboxComponent);
    return ChatterboxComponent;
}());
export { ChatterboxComponent };
//# sourceMappingURL=chat.component.js.map