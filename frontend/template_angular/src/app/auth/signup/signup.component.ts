import { Component, OnInit } from '@angular/core';
import { SignupRequestPayload } from './signupRequestPayload';
import { Router } from '@angular/router';
import { UserService } from 'src/app/components/shared/services/user.service';
import {
  FormGroup,
  UntypedFormBuilder,

  Validators,
} from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthConfig } from 'angular-oauth2-oidc';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
signupForm: FormGroup;
signupRequestPayload:SignupRequestPayload;



  constructor(private userService: UserService,
    private router: Router,
    private toastr: ToastrService,
    private formBuilder: UntypedFormBuilder) { this.signupForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      gender: ['', Validators.required]
    });
  this.signupRequestPayload ={
    username:'',
    email:'',
    password:'',
    firstName: '',
    lastName: '',
    gender: ''

  }}

  ngOnInit(): void {
  }

  initSignUpForm() {
    this.signupForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }
  signup() {
    if (this.signupForm.valid) {
      this.signupRequestPayload = this.signupForm.value;
      this.userService.signup(this.signupRequestPayload).subscribe(
        data => {
          console.log(data)
          this.router.navigate([''])
         },
        () => {
          this.toastr.error('Registration Failed! Please try again');
        }
      );
    } else {
      this.toastr.error('Registration Failed! Please try again');
    }

  }


}
