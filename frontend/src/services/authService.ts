import api, { toApiError } from './api';
import type { Utente, UtenteCorrente } from '../types';

/** GET /rest/me: chi è l'utente della sessione, risposta vuota se anonimo (il login vero e proprio è la pagina Thymeleaf /login) */
export async function getUtenteCorrente(): Promise<UtenteCorrente> {
  try {
    const { data } = await api.get<Utente | ''>('/me');
    if (!data) {
      return { autenticato: false, username: null, ruolo: null };
    }
    return { autenticato: true, username: data.username, ruolo: data.ruolo };
  } catch (e) {
    throw toApiError(e);
  }
}
