import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable()
export class AppService {

  constructor(private http: HttpClient) { }

  obterProximaSequencia(numero: number): Observable<number> {
    console.log('Calculando próxima sequência de:', numero);
    return this.http.get<number>(`${environment.api}/alticci/${numero}`);
  }
}
