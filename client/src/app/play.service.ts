import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Play } from './shared/model/play';
import { environment } from '../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlayService {

  private static PLAYS_URL = `${environment.backendServer}/plays`;
  private static CREATE_COMMAND = 'CREATE_PLAY';

  constructor(private http: HttpClient) { }

  fetchPlays(): Observable<Play[]> {
    return this.http.get<Play[]>(PlayService.PLAYS_URL);
  }

  createPlay(name: string): Observable<Play> {
    return this.http.post<Play>(PlayService.PLAYS_URL, { command: PlayService.CREATE_COMMAND, name }); // TODO: error handling
  }
}
