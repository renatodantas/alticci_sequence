import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
})
export class AppComponent {

  sequencia!: Observable<number>

  constructor(private service: AppService) { }

  calcular(numero: any) {
    this.sequencia = this.service.obterProximaSequencia(numero);
  }
}
