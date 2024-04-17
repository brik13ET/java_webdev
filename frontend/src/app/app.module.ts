import { NgModule }				from '@angular/core';
import { BrowserModule }		from '@angular/platform-browser';

import { HttpClientModule }     from '@angular/common/http';
import { FormsModule }			from '@angular/forms';
import { AppRoutingModule }     from './app-routing.module';
import { AppComponent }         from './app.component';

import { ProjectListComponent } from './project-list/project-list.component';
import { EditProjectComponent } from './edit-project/edit-project.component';
import { TaskListComponent }    from './task-list/task-list.component';
import { ProjectCardComponent } from './project-card/project-card.component';
import { TaskCardComponent }    from './task-card/task-card.component';

@NgModule({
  declarations: [
    AppComponent,
    ProjectListComponent,
    EditProjectComponent,
    TaskListComponent,
    ProjectCardComponent,
    TaskCardComponent
  ],
imports: [
	BrowserModule,
    HttpClientModule,
    AppRoutingModule,
	FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
