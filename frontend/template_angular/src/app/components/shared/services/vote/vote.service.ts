import { LoginService } from 'src/app/components/shared/services/login.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vote } from 'src/app/components/models/Vote';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VoteService {

 private apiServerUrl = environment.apiBaseUrl3;

  constructor(private http: HttpClient,private loginService:LoginService){}

  getVotes(): Observable<Vote[]> {
    // Gets total votes
    console.log('Gets total votes')
    return this.http.get<Vote[]>(`${this.apiServerUrl}/getallvotes`);
  }

  removevote(voteId: any): Observable<Vote>{
    console.log('Removes',voteId);
    return this.http.get<Vote>(`${this.apiServerUrl}/removeVote/${voteId}`)
  }


  //getvotesbyusername(username: string): Observable<Vote> {
    //console.log('getvotesbyusername',username)
    //return this.http.get<Vote>(`${this.apiServerUrl}/retrieveVoteByUserName/${username}`);

  //}

  updatevote(vote: any): Observable<Vote>{
    console.log('updatevote')
    //let data = {}
    //data[username] = vote
    return this.http.patch<Vote>(`${this.apiServerUrl}/changeVoteTypee`, vote)
  }

  vote(votePayload: { voteType: string; username: string; id: number; }): Observable<Vote>{
    // The payload of an API is the data you are interested in transporting to the server when you make an API request
    //it is the body of your HTTP request and response message.
    const {id ,username,voteType} = votePayload
    console.log('vottteee')
    return this.http.post<Vote>(`${this.apiServerUrl}/Vote`, votePayload, {
      headers: {
        'Authorization': `Bearer ${this.loginService.getTOKEN()}`,
        'Content-type':'application/json'

      }
    })
  }

  desaffecterVotee(voteId: any) {
        return this.http.get<Vote>(`${this.apiServerUrl}/desaffecterVotee/${voteId}`)
  }
  nbrofvote(votetype: any) {
        return this.http.get<Vote>(`${this.apiServerUrl}/nbrofvote/${votetype}`)
  }

    retrieveVoteByUserName(username: string) {
        return this.http.get<Vote>(`${this.apiServerUrl}/retrieveVoteByUserName/${username}`)

  }

}
