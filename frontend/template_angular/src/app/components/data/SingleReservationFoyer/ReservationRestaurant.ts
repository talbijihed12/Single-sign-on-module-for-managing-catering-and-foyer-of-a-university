export class ReservationRestaurant{
     idR !:number;
     dateStart!:Date;
     dateEnd!:Date;
     nbrPerson!: number;
     nameGrp !:String;
     approved!: Boolean;
     expired!: Boolean;
     username!: String;

}
export enum Pension {
    Demi_pension ='Demi_pension', 
    pension_complete='pension_complete',
}
export enum Allergy {
    Oeuf='Oeuf',
    Lait='Lait',
    Moutarde='Moutarde',
    Arachide='Arachide',
    Mollusques_et_crustacés='Mollusques_et_crustacés',
    Poissons='Poissons',
    Graines_de_sésame='Graines_de_sésame',
    Soja='Soja',
    Sulfites='Sulfites' ,
    Noix='Noix',
    Blé_et_triticale='Blé_et_triticale',

}