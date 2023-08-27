import { Interview } from "./interview";
import { Offer } from "./offer";
import { User } from "./user";

export class OfferCandidacy {
    id!: number;
    creationDate?: Date;
    docDiploma?: string;
    establishment?: string;
    docCV?: string;
    docLetter?: string;
    score?: number;
    status?: string;
    offer!: Offer;
    interview!: Interview;
    user!: User;
}
