import axios, { AxiosError } from 'axios';

/** Indirizzo del backend Spring Boot: serve alle chiamate REST e ai link verso le pagine Thymeleaf. */
export const BACKEND_URL = 'http://localhost:8080';

/*
 * UNICA istanza axios dell'applicazione: la baseURL si scrive una volta sola e tutti i servizi la usano.
 * React (porta 5173) e Spring (porta 8080) sono origini diverse, quindi le chiamate passano dal CORS.
 * withCredentials fa inviare il cookie di sessione JSESSIONID: è la stessa sessione delle pagine Thymeleaf,
 * perché i cookie non distinguono le porte e localhost resta lo stesso sito.
 */
const api = axios.create({
  baseURL: `${BACKEND_URL}/rest`,
  headers: { Accept: 'application/json' },
  withCredentials: true,
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
