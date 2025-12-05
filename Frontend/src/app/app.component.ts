import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'EMI Calculator';
  emiForm: FormGroup;
  emi: number | null = null;
  interestAmount: number | null = null;
  totalPayable: number | null = null;
  isLoading: boolean = false;
  errorMsg: string = '';

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.emiForm = this.fb.group({
      loanAmount: ['', [Validators.required, Validators.min(1)]],
      yearlyInterestRate: ['', [Validators.required, Validators.min(0)]],
      loanTermYears: ['', [Validators.required, Validators.min(1)]]
    });
  }

  onCalculateClick() {
    if (this.emiForm.invalid) {
      this.errorMsg = 'Please fill all fields correctly';
      return;
    }
    let loanAmt = this.emiForm.value.loanAmount;
    let interestRate = this.emiForm.value.yearlyInterestRate;
    let years = this.emiForm.value.loanTermYears;
    
    this.isLoading = true;
    this.errorMsg = '';
    this.emi = null;
    this.interestAmount = null;
    this.totalPayable = null;
    let apiUrl = `http://localhost:8080/emi?loanAmount=${loanAmt}&yearlyInterestRate=${interestRate}&loanTermYears=${years}`;

    this.http.get<number>(apiUrl).subscribe(
      (response) => {
        this.emi = response;
        const numberOfMonths = years * 12;
        this.totalPayable = Math.round(this.emi * numberOfMonths * 100) / 100;
        this.interestAmount = Math.round((this.totalPayable - loanAmt) * 100) / 100;
        this.isLoading = false;
      },
      (error) => {
        this.errorMsg = 'Something went wrong. Try again!';
        this.isLoading = false;
        console.log('Error: ', error);
      }
    );
  }
}
