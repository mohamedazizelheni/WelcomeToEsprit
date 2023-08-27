import { OfferCandidacy } from "./offer-candidacy";

export class Offer {
    id!:number;
    speciality?:string;
    creationDate?: Date;
    description?:string;
    offerCandidacy?: OfferCandidacy;
}
