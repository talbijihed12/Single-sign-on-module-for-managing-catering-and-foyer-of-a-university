import { Component } from '@angular/core';
import { UntypedFormGroup, UntypedFormControl, Validators } from '@angular/forms';
import { CarHelperService } from 'src/app/components/helper/cars/car-helper.service';

@Component({
  selector: 'app-car-booking',
  templateUrl: './car-booking.component.html',
  styleUrls: ['./car-booking.component.css']
})
export class CarBookingComponent extends CarHelperService {
  bookingForm = new UntypedFormGroup({
    firstname: new UntypedFormControl('', [Validators.required]),
    lastname: new UntypedFormControl('', [Validators.required]),
    email: new UntypedFormControl('', [Validators.required]),
    verifyemail: new UntypedFormControl('', [Validators.required]),
    country: new UntypedFormControl('', [Validators.required]),
    phone: new UntypedFormControl('', [Validators.required]),
    cardInfo: new UntypedFormGroup({
      cardtype: new UntypedFormControl('', [Validators.required]),
      cardname: new UntypedFormControl('', [Validators.required]),
      cardnumber: new UntypedFormControl('', [Validators.required,Validators.minLength(16)]),
      cvv: new UntypedFormControl('', [Validators.required,Validators.minLength(3)]),
      expmonth: new UntypedFormControl('', [Validators.required]),
      expyear: new UntypedFormControl('', [Validators.required]),
      zipcode: new UntypedFormControl('', [Validators.required,Validators.minLength(6)])
    }),
    flightInfo:new UntypedFormGroup({
      airlinename: new UntypedFormControl('', [Validators.required]),
      arrivingnumber: new UntypedFormControl('', [Validators.required]),
    })
  });
  onSubmit() {
    console.log(this.bookingForm.value);
    this.bookingForm.reset();
  }
}
