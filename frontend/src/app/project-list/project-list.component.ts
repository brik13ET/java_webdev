import { Component, OnInit } from '@angular/core';
import { Project } from '../model/project';
import { ProjectService } from '../service/project.service';
import { Observable, filter, forkJoin } from 'rxjs';

@Component({
	selector: 'app-project-list',
	templateUrl: './project-list.component.html',
	styleUrl: './project-list.component.css'
})
export class ProjectListComponent implements OnInit {
	
	projects_blog: [Project, number][] = [];
	
	updating: boolean = false;
	
	constructor( private service: ProjectService) { }
	
	search(query: string)
	{
		if (query != '')
			this.fetch_projects(
			this.service.getAll(),
			(pb) => pb[0].name			.toLowerCase().includes(query.toLowerCase()) ||
					pb[0].description	.toLowerCase().includes(query.toLowerCase())
			
		);
		else
		this.fetch_projects(
			this.service.getAll()			
		);
	}
	
	fetch_projects(
		observ: Observable<Project[]>,
		filter: (prj: [Project, number]) => boolean = () => true,
		forEach: (prj: [Project, number]) => void = () => {}
	) {
		this.updating = true;
		this.projects_blog = [];
		forkJoin([
			observ,
			this.service.getPending()
		]).subscribe(recv => {
			let prjs  = recv[0];
			let blogs = recv[1];
			prjs.forEach((prj) =>
				{
				if (filter([prj, blogs[prj.id] || 0]))
					{
						this.projects_blog.push( [prj, blogs[prj.id]]);
						forEach([prj, blogs[prj.id]]);
					}
			});
			this.updating = false;
		});
	}
	
	getAllProjs()
	{
		this.fetch_projects(this.service.getAll());
	}
	
	ngOnInit(): void
	{
		this.getAllProjs();
	}
	
	
}
