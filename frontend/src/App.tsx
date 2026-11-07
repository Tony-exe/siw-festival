import { Routes, Route } from 'react-router-dom';
import { Box, Container, Link, Paper, Typography } from '@mui/material';
import Navbar from './components/Navbar';
import ScreeningsPage from './pages/ScreeningsPage';

/**
 * Componente radice: layout comune (navbar, contenuto, footer) e rotte. Il router decide sul client quale
 * pagina mostrare in base all'URL, senza ricaricare la pagina. Le rotte sono relative a basename "/app".
 * L'unica pagina React è il programma delle proiezioni (sola lettura, pubblica): il dettaglio film e
 * tutti i form sono pagine Thymeleaf.
 */
export default function App() {
  return (
    <Box sx={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
      <Navbar />
      {/* Card bianca centrata, come il <main> delle pagine Thymeleaf */}
      <Container component="main" maxWidth={false} sx={{ flex: 1, maxWidth: 1100, my: 4 }}>
        <Paper sx={{ px: { xs: 2, sm: 4 }, py: { xs: 2.5, sm: 3.5 } }}>
          <Routes>
            <Route path="/festivals/:id/screenings" element={<ScreeningsPage />} />
            <Route path="*" element={
              <Typography color="text.secondary">Pagina non trovata. <Link href="/">Torna alla home</Link></Typography>
            } />
          </Routes>
        </Paper>
      </Container>
      <Typography component="footer" variant="body2" color="text.secondary" align="center" sx={{ py: 2 }}>
        Sistemi Informativi su Web · Roma Tre · Progetto festival cinematografici
      </Typography>
    </Box>
  );
}
