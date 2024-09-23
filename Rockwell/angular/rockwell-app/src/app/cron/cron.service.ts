import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CronConfig } from './cron-config';

@Injectable({
  providedIn: 'root'
})
export class CronService {

  private endpoint: string = "http://localhost:8080/api/configure-cron"
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })

  constructor(private httpClient: HttpClient) { }

  configureCron(cronConfig: CronConfig): Observable<void> {
    return this.httpClient.post<void>(this.endpoint, cronConfig, { headers: this.httpHeaders })
  }
}
