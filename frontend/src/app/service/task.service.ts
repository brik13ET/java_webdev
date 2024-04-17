import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { Task } from '../model/task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) { }

  getAll(projectId: number): Observable<Task[]> {
    return this.http.get<Task[]>(`${environment.apiUrl}/projects/${projectId}/tasks`);
  }

  getId(projectId: number, taskId: number): Observable<Task> {
    return this.http.get<Task>(`${environment.apiUrl}/projects/${projectId}/tasks/${taskId}`);
  }

  create(projectId: number, task: Task): Observable<Task> {
    return this.http.post<Task>(`${environment.apiUrl}/projects/${projectId}/tasks`, task,
    {
      headers: {"Content-Type": "application/json; charset=UTF-8"}
    });
  }

  update(projectId: number, taskId: number, task: Task): Observable<Task> {
    return this.http.put<Task>(`${environment.apiUrl}/projects/${projectId}/tasks/${taskId}`, task,
    {
      headers: {"Content-Type": "application/json; charset=UTF-8"}
    });
  }

  deleteById(projectId: number, taskId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/projects/${projectId}/tasks/${taskId}`);
  }

  deleteFinished(projectId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/projects/${projectId}/tasks/finished`);
  }

}
