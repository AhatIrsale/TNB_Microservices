import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientComponent } from '../app/client/client.component';
import { CategorieComponent } from './categorie/categorie.component';
import { TaxComponent } from './tax/tax.component';
import { TerrainComponent } from './terrain/terrain.component';
import { TauxComponent } from './taux/taux.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { UserTerainsComponent } from './user-terains/user-terains.component';
import { TerrainTaxesComponent } from './terrain-taxes/terrain-taxes.component';
import { UserComponent } from './user/user.component';


const routes: Routes = [
 // { path: '', redirectTo: '/clients', pathMatch: 'full' },
  { path:'clients', component: ClientComponent },
  { path:'categories', component: CategorieComponent },
  { path:'taxes', component: TaxComponent },
  { path:'terrains', component: TerrainComponent },
  { path:'taux', component: TauxComponent },

  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component:   RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: BoardUserComponent },
  { path: 'mod', component: BoardModeratorComponent },
  { path: 'admin', component: BoardAdminComponent },


  { path: 'terainu', component: UserTerainsComponent },
  { path: 'terainut', component: TerrainTaxesComponent },
  { path: 'users', component: UserComponent }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

 }
