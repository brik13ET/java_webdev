import { Component, Input } from '@angular/core';
import { Location }			from '@angular/common';
import { Task } from '../model/task';
import { TaskService } from '../service/task.service';
import { Observable, forkJoin } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css'
})
export class TaskListComponent {
	tasks: Task[] = [];
	project!: number;
	updating: boolean = false;
	
	constructor(
		private service: TaskService,
		private location: Location,
		private router: ActivatedRoute
	) {
		if (+router.snapshot.url[1].path != null && +router.snapshot.url[1].path != undefined)
			this.project = +router.snapshot.url[1].path;
	}

  
	fetch_tasks(
		observ: Observable<Task[]>
	) {
		this.updating = true;
		this.tasks = [];
		observ.subscribe(recv => {
			this.tasks = recv;
			this.updating = false;
		});
	}
	
	getAllTasks()
	{
		this.fetch_tasks(this.service.getAll(this.project));
	}
	
	ngOnInit(): void
	{
		this.getAllTasks();
	}

	goBack()
	{
		this.location.back();
	}
}
