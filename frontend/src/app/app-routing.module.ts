import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { AuditListComponent } from './audit-list/audit-list.component';
import { AuthenticationGuard } from './authentication.guard';
import { HomeComponent } from './home/home.component';
import { LoginFailedComponent } from './login-failed/login-failed.component';
import { LoginComponent } from './login/login.component';
import { PersonsHomeComponent } from './persons-home/persons-home.component';
import { RegisterComponent } from './register/register.component';
import { UsersHistoryComponent } from './users-history/users-history.component';
import { UsersHomeComponent } from './users-home/users-home.component';
import { UsersListComponent } from './users-list/users-list.component';

const routes: Routes = [
  { path: '', canActivate:[AuthenticationGuard], children: [
    { path: 'login', component: LoginComponent },
    { path: 'home', component: HomeComponent },
    { path: 'login-failed', component: LoginFailedComponent },
    { path: 'users-home', component: UsersHomeComponent },
    { path: 'audit-list', component: AuditListComponent },
    { path: 'persons-home', component: PersonsHomeComponent },
    { path: '**', redirectTo: 'home', pathMatch: 'full' }
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
