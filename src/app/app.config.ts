import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withFetch } from '@angular/common/http'; // Removed withInterceptors
import { routes } from './app.routes';
import { provideAnimations } from '@angular/platform-browser/animations';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(), // Clean HTTP client without interceptors
    provideAnimations(),
      provideHttpClient(
      withFetch() // 👈 Enable Fetch API globally
    ),
  ]
};