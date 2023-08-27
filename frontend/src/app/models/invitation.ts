import { Event } from './event';
import { User } from './user';

export class Invitation {
  id!: number;
  type!: string;
  present!: boolean;
  event!: Event;
  user!: User;
}