import { Component } from '@angular/core';
import { FormComponent } from './cron/form.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'rockwell-app';
}
