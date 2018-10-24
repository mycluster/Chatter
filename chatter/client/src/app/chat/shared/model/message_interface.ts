import { User } from './user';
import { Action } from './action'

export interface MessageInter {
    from?: User;
    content?: any;
    action?: Action;
}