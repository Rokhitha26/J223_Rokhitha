import { Routes } from '@angular/router';
import { LoginComponent } from './login/login';
import { RegisterComponent } from './register/register';
import { Home } from './home/home';
import { AuthGuard } from './auth-guard';

import { AddBook } from './add-book/add-book';
import { DeleteBook } from './delete-book/delete-book';
import { GetAllBooks } from './get-all-books/get-all-books';
import { GetBookByIsbn } from './get-book-by-isbn/get-book-by-isbn';
import { UpdateBooks } from './update-books/update-books'; // ✅ Corrected import name


export const routes: Routes = [
  {
    path: '',
    component: Home,
    canActivate: [AuthGuard],
    children: [
      { path: '', component: GetAllBooks, canActivate: [AuthGuard] },
      { path: 'all-books', component: GetAllBooks, canActivate: [AuthGuard] },
      { path: 'add-book', component: AddBook, canActivate: [AuthGuard] },
      { path: 'delete-book', component: DeleteBook, canActivate: [AuthGuard] },
 // if created
      { path: 'book/:isbn', component: GetBookByIsbn, canActivate: [AuthGuard] },
      { path: 'get-book', component: GetBookByIsbn, canActivate: [AuthGuard] },

{ path: 'update-book', component: UpdateBooks, canActivate: [AuthGuard] },

      { path: 'update-book/:isbn', component: UpdateBooks, canActivate: [AuthGuard] }

    ]
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: '**', redirectTo: '/login' }
];
