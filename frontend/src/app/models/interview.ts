import { AdmissionCandidacy } from "./admission-candidacy";
import { Complain } from "./complain";
import { OfferCandidacy } from "./offer-candidacy";
import { Room } from "./room";
import { User } from "./user";

export class Interview {
    id!: number;
    date?: Date;
    admissionCandidacy?: AdmissionCandidacy;
    offerCandidacy?: OfferCandidacy;
    room!: Room;
    complain?: Complain;
    user!: User;
}
