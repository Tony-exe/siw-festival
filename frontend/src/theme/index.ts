import { createTheme } from '@mui/material/styles';

// Tema MUI: stessi colori delle variabili di static/css/app.css (blu MUI dell'header, viola dei pulsanti,
// sfondo grigio chiaro), così React e Thymeleaf sembrano un'unica applicazione.
const theme = createTheme({
  palette: {
    primary: { main: '#1976d2', dark: '#115293' },
    secondary: { main: '#7d2ae8' },
    background: { default: '#f4f6f8' },
  },
  typography: {
    fontFamily: '"Roboto", "Segoe UI", Arial, sans-serif',
  },
  components: {
    // testo dei pulsanti come in Thymeleaf ("Esci", non "ESCI" maiuscolo come nel default di MUI)
    MuiButton: { defaultProps: { disableElevation: true }, styleOverrides: { root: { textTransform: 'none' } } },
  },
});

export default theme;
