import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// Frontend e backend sono due server separati, come nelle slide di React (lezione 12):
// React su http://localhost:5173 (npm run dev), Spring Boot su http://localhost:8080.
// Le chiamate tra i due passano dal CORS, configurato in CorsConfig lato Spring.
export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    strictPort: true, // CorsConfig permette solo la 5173: se è occupata meglio un errore che un'altra porta
  },
  build: {
    // MUI porta il bundle a ~540 kB (175 kB compressi): accettabile per due pagine, alzo la soglia dell'avviso
    chunkSizeWarningLimit: 700,
  },
});
