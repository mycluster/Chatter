var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
import { Component, Inject } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
var DialogUserComponent = /** @class */ (function () {
    function DialogUserComponent(dialogRef, params) {
        this.dialogRef = dialogRef;
        this.params = params;
        this.usernameFormControl = new FormControl('', [Validators.required]);
        this.previousUsername = params.username ? params.username : undefined;
    }
    DialogUserComponent.prototype.ngOnInit = function () {
    };
    DialogUserComponent.prototype.onSave = function () {
        this.dialogRef.close({
            dialogType: this.params.dialogType,
            previousUsername: this.previousUsername
        });
    };
    DialogUserComponent = __decorate([
        Component({
            selector: 'tcc-dialog-user',
            templateUrl: './dialog-user.component.html',
            styleUrls: ['./dialog-user.component.css']
        }),
        __param(1, Inject(MAT_DIALOG_DATA)),
        __metadata("design:paramtypes", [MatDialogRef, Object])
    ], DialogUserComponent);
    return DialogUserComponent;
}());
export { DialogUserComponent };
//# sourceMappingURL=dialog-user.component.js.map