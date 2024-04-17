import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Project } from '../model/project';
import { ProjectService } from '../service/project.service';

@Component({
  selector: 'app-project-card',
  templateUrl: './project-card.component.html',
  styleUrl: './project-card.component.css'
})
export class ProjectCardComponent {
  @Input() project!: Project; // https://www.reddit.com/r/Angular2/comments/lxt5ao/strict_mode_on_input_gives_property_xy_has_no/
  @Input() backlog!: number;
  @Output() update: EventEmitter<any> = new EventEmitter();

  constructor(private service: ProjectService) { }

  deleteprj(id: number)
  {
    this.service.delete(id)
		.subscribe( () => {
			this.update.emit(null);
		}
	);
    
  }
}
