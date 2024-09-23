import { Component, OnInit } from '@angular/core';
import { CronConfig } from './cron-config';
import { FormsModule } from '@angular/forms';
import { CronService } from './cron.service';
import * as cronParser from 'cron-parser';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

  public title: string = "Scheduler Configuration Form"
  public cronConfig: CronConfig = new CronConfig()
  public parsedCron: any;

  constructor(private cronService: CronService) { }

  ngOnInit(): void { }

  public configureCron(): void {
    console.log(this.cronConfig)
    this.cronService.configureCron(this.cronConfig).subscribe(
      response => console.log('Data successfully sent.')
    )
  }

  public parseCronExpression(): void {
    try {
      const interval = cronParser.parseExpression(this.cronConfig.cronExpression);
      this.parsedCron = {
        next: interval.next().toString()
      };

    } catch (error) {
      console.error('Error parsing cron expression:', error);
      this.parsedCron = null;
    }
  }

}
