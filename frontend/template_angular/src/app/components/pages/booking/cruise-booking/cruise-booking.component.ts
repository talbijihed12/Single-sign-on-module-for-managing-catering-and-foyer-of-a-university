import { Component } from '@angular/core';
import { UntypedFormGroup, UntypedFormControl, Validators } from '@angular/forms';
import { CruiseHelperService } from 'src/app/components/helper/cruise/cruise-helper.service';

@Component({
  selector: 'app-cruise-booking',
  templateUrl: './cruise-booking.component.html',
  styleUrls: ['./cruise-booking.component.css']
})
export class CruiseBookingComponent extends CruiseHelperService {
  bookingForm = new UntypedFormGroup({
    firstname: new UntypedFormControl('', [Validators.required]),
    lastname: new UntypedFormControl('', [Validators.required]),
    email: new UntypedFormControl('', [Validators.required]),
    verifyemail: new UntypedFormControl('', [Validators.required]),
    citizenship: new UntypedFormControl('', [Validators.required]),
    dateofbirth: new UntypedFormControl('', [Validators.required]),
    genderInfo: new UntypedFormGroup({
      gender: new UntypedFormControl('Male', [Validators.required]),
    }),
    country: new UntypedFormControl('', [Validators.required]),
    phone: new UntypedFormControl('', [Validators.required]),
    address: new UntypedFormControl('', [Validators.required]),
    state: new UntypedFormControl('', [Validators.required]),
    zipcode: new UntypedFormControl('', [Validators.required]),
    cardInfo: new UntypedFormGroup({
      cardtype: new UntypedFormControl('', [Validators.required]),
      cardname: new UntypedFormControl('', [Validators.required]),
      cardnumber: new UntypedFormControl('', [Validators.required,Validators.minLength(16)]),
      cvv: new UntypedFormControl('', [Validators.required,Validators.minLength(3)]),
      expmonth: new UntypedFormControl('', [Validators.required]),
      expyear: new UntypedFormControl('', [Validators.required]),
      zipcode: new UntypedFormControl('', [Validators.required,Validators.minLength(6)])
    })
  });
  onSubmit() {
    console.log(this.bookingForm.value);
    this.bookingForm.reset();
  }
}
