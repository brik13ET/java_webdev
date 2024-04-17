import { Location }			from '@angular/common';
import { Component, OnInit, ViewChild }from '@angular/core';
import { Project }			from '../model/project';
import { ProjectService } 	from '../service/project.service';
import { ActivatedRoute } 	from '@angular/router';

@Component({
	selector: 'app-edit-project',
	templateUrl: './edit-project.component.html',
	styleUrl: './edit-project.component.css',
})
export class EditProjectComponent implements OnInit {
	prj: Project = {} as Project;
	isNew: boolean = false;
	loading: boolean = true;
	
	
	@ViewChild('id')			id!			: number;
	@ViewChild('name')			name!		: string;
	@ViewChild('description')	description!: string;
	@ViewChild('begin')			begin!		: Date;
	@ViewChild('end')			end!		: Date;
	
	constructor(
		private service: ProjectService,
		private location: Location,
		private router: ActivatedRoute
	)  {
		if (this.router.snapshot.url[1].path != 'new')
			this.service
		.getProject(+this.router.snapshot.url[1].path)
		.subscribe(
			prj => {
				this.isNew = true;
				this.prj = prj;
				this.loading = false;
			}
		);
		
		this.loading = false;
		if (this.prj == null) this.prj = {} as Project;
	}
	
	ngOnInit(): void {
	}
	
	goBack() {
		this.location.back();
	}
	
	apply()
	{
		this.loading = true;
		// console.log(this.prj);
		
		this.service.update(this.prj).subscribe(prj => {
			if (prj == null)
				alert('Проект обновить не удалось');
			else{
				alert(`Проект ${prj.name} обновлён`);
				this.location.back();
			}
			this.loading = false;
		});
	}
	create()
	{
		this.loading = true;
		this.service.create(this.prj).subscribe((prj) => {
			if (prj == null)
				alert('Проект создать не удалось');
			else{
				alert(`Проект ${prj.name} создан`)
				this.location.back();	
			}
			this.loading = false;
		});
	}
	
	clear()
	{
		this.prj = { id: this.prj.id } as Project;
	}
	
}
