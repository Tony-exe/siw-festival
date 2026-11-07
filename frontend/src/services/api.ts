import axios, { AxiosError } from 'axios';

/*
 * UNICA istanza axios dell'applicazione: la baseURL si scrive una volta sola e tutti i servizi la usano.
 * Le richieste sono sulla stessa origine del backend (in sviluppo grazie al proxy di Vite), quindi il
 * browser invia da solo il cookie di sessione JSESSIONID: è la stessa sessione delle pagine Thymeleaf.
 */
const api = axios.create({
  baseURL: '/rest',
  headers: { Accept: 'application/json' },
  withCredentials: true, // invia il cookie di sessione anche se il frontend gira su un'altra origine (CORS)
});

export default api;

/** Corpo degli errori del backend, prodotto da RestExceptionHandler. */
interface ProblemDetail {
  detail?: string;
}

/** Errore "leggibile" che i servizi passano ai componenti: status HTTP e messaggio da mostrare. */
export class ApiError extends Error {
  status: number;

  constructor(status: number, messaggio: string) {
    super(messaggio);
    this.status = status;
  }
}

/**
 * Trasforma qualunque errore di axios in un ApiError con un messaggio comprensibile.
 * axios, a differenza di fetch, lancia un'eccezione per ogni risposta 4xx/5xx: basta un try/catch.
 */
export function toApiError(errore: unknown): ApiError {
  if (!axios.isAxiosError(errore) || !errore.response) {
    return new ApiError(0, 'Impossibile contattare il server. Riprova.');
  }
  const { status, data } = (errore as AxiosError<ProblemDetail>).response!;
  if (status === 404) {
    return new ApiError(404, data?.detail ?? 'Risorsa non trovata.');
  }
  return new ApiError(status, data?.detail ?? `Errore ${status}`);
}
