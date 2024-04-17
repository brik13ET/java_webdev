import { Injectable } from '@angular/core';
import { Project } from '../model/project';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http: HttpClient) { }
  
  getProject(id: number): Observable<Project>
  {
    return this.http.get<Project>(`${environment.apiUrl}/projects/${id}`)
  }

  getAll    (): Observable<Project[]>
  {
    return this.http.get<Project[]>(`${environment.apiUrl}/projects/all`)
  }

  getPending(): Observable<{ [key: number]: number; }>
  {
    return this.http.get<{ [key: number]: number; }>(`${environment.apiUrl}/projects/pending_count`)
  }

  getInRange(start_date: Date, end_date: Date): Observable<Project[]>
  {
    return this.http.get<Project[]>(
      `${environment.apiUrl}/projects`,
      {
        headers: undefined,
        params: {
          "start_date": start_date.toISOString(),
          "end_date"  : end_date  .toISOString()
        }
      }
    );
  }

  search    (query: string): Observable<Project[]>
  {
    return this.http.get<Project[]>(
      `${environment.apiUrl}/projects`,
      {
        headers: undefined,
        params: {"search": query}
      }
    );
  }

  create    (proj: Project): Observable<Project>
  {
    return this.http.post<Project>(`${environment.apiUrl}/projects`, proj,
	{
		headers: {"Content-Type": "application/json; charset=UTF-8"}
	});
  }

  update    (proj: Project): Observable<Project>
  {
    return this.http.put<Project>(
		`${environment.apiUrl}/projects/${proj.id}`,
		proj,
		{
			headers: {"Content-Type": "application/json; charset=UTF-8"}
		}
	);
  }

  delete    (id: number): Observable<void>
  {
    return this.http.delete<void>(`${environment.apiUrl}/projects/${id}`);
  }
}
