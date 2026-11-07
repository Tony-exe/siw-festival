import { AppBar, Box, Button, Link, Toolbar, Typography } from '@mui/material';
import { useAuth } from '../context/AuthContext';

/**
 * Stesso header delle pagine Thymeleaf (fragments/layout.html): barra blu con il logo a sinistra e i link
 * a destra, così React e Thymeleaf sembrano un'unica applicazione. I link portano alle pagine Thymeleaf
 * con normali href (navigazione completa, non del router).
 */
export default function Navbar() {
  const { utente } = useAuth();
  const isAdmin = utente?.ruolo === 'ADMIN';

  return (
    <AppBar position="sticky" color="primary">
      <Toolbar sx={{ flexWrap: 'wrap', gap: '8px 18px', py: 1 }}>
        <Link href="/" color="inherit" underline="none" variant="h6" sx={{ fontWeight: 700, mr: 'auto' }}>
          🎬 Festival
        </Link>
        <Link href="/festivals" color="inherit" underline="hover">Festival</Link>
        {isAdmin && (
          <>
            <Link href="/admin" color="inherit" underline="hover">Gestione</Link>
            <Link href="/admin/festivals" color="inherit" underline="hover">Festival e proiezioni</Link>
            <Link href="/admin/films" color="inherit" underline="hover">Film e cast</Link>
            <Link href="/admin/sale" color="inherit" underline="hover">Sale</Link>
          </>
        )}

        {utente?.autenticato && (
          <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
            <Typography variant="body1">Ciao, {utente.username}</Typography>
            {/* Logout = POST a /logout (gestito da Spring Security) */}
            <form method="post" action="/logout">
              <Button type="submit" variant="outlined" color="inherit" size="small">Esci</Button>
            </form>
          </Box>
        )}
        {utente && !utente.autenticato && (
          <>
            <Link href="/login" color="inherit" underline="hover">Accedi</Link>
            <Link href="/register" color="inherit" underline="hover">Registrati</Link>
          </>
        )}
      </Toolbar>
    </AppBar>
  );
}
