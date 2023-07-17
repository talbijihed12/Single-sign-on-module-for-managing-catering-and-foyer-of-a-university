import { Component, OnInit } from '@angular/core';
import { UntypedFormGroup, UntypedFormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-contact-form',
  templateUrl: './contact-form.component.html',
  styleUrls: ['./contact-form.component.css']
})
export class ContactFormComponent implements OnInit {

  constructor() { }
  contactForm = new UntypedFormGroup({
    name: new UntypedFormControl('', [Validators.required]),
    email: new UntypedFormControl('', [Validators.required]),
    phone: new UntypedFormControl('', [Validators.required]),
    subject: new UntypedFormControl('', [Validators.required]),
    message: new UntypedFormControl('', [Validators.required])
  });
  onSubmit() {
    // TODO: Use EventEmitter with form value
    console.warn(this.contactForm.value);
    this.contactForm.reset();
  }

  ngOnInit(): void {
    
  }

}
