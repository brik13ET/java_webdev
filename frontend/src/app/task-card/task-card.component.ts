import { Component, EventEmitter, Input, Output } from '@angular/core';
import { TaskService } from '../service/task.service';
import { Task } from '../model/task';

@Component({
  selector: 'app-task-card',
  templateUrl: './task-card.component.html',
  styleUrl: './task-card.component.css'
})
export class TaskCardComponent {

  @Input()  public task!: Task;
  @Input()  public projectid!: number;
  @Output() public update: EventEmitter<any> = new EventEmitter();

  constructor(private service: TaskService) {}

  deletetsk()
  {
    this.service.deleteById(this.projectid, this.task.id)
		.subscribe( () => {
			this.update.emit(null);
		}
	);
    
  }
}
