// Interfacce TypeScript che replicano le entità Java restituite dai @RestController (package model).
// Se un'entità cambia, si aggiorna qui e il compilatore segnala tutti i punti del codice da sistemare.
// Date e orari arrivano come stringhe ISO: LocalDate → "2026-10-26", LocalTime → "18:00:00".

export interface Festival {
  id: number;
  nome: string;
  anno: number;
  citta: string;
  dataInizio: string;
  dataFine: string;
  descrizione: string | null;
}

export interface Regista {
  id: number;
  nome: string;
  cognome: string;
  dataNascita: string | null;
  nazionalita: string | null;
}

export interface Film {
  id: number;
  titolo: string;
  anno: number;
  durata: number;
  genere: string;
  paeseProduzione: string;
  regista: Regista;
}

export interface Sala {
  id: number;
  nome: string;
  indirizzo: string;
  capienza: number;
}

export type StatoProiezione = 'SCHEDULED' | 'COMPLETED' | 'CANCELLED'; // union type = enum Java

export interface Proiezione {
  id: number;
  data: string;
  ora: string;
  oraFine: string;
  stato: StatoProiezione;
  film: Omit<Film, 'regista'>; // nelle proiezioni il regista non viene inviato
  sala: Sala;
  festival: Festival;
}

/** Utente restituito da GET /rest/me (la password non viene mai inviata). */
export interface Utente {
  id: number;
  username: string;
  ruolo: 'USER' | 'ADMIN';
}

/** Chi è collegato, ricavato da GET /rest/me: React lo usa per decidere cosa mostrare. */
export interface UtenteCorrente {
  autenticato: boolean;
  username: string | null;
  ruolo: 'USER' | 'ADMIN' | null;
}
