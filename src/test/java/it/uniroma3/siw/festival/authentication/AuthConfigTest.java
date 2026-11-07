package it.uniroma3.siw.festival.authentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import it.uniroma3.siw.festival.model.Ruolo;
import it.uniroma3.siw.festival.model.Utente;
import it.uniroma3.siw.festival.repository.UtenteRepository;

/**
 * Test della catena di filtri di Spring Security tramite MockMvc: le richieste attraversano i filtri
 * veri (autenticazione, CSRF, autorizzazione sugli URL) senza avviare un server HTTP.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthConfigTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JdbcTemplate jdbc;

    @BeforeEach
    void setUp() {
        utenteRepository.save(new Utente("admin", passwordEncoder.encode("admin"), Ruolo.ADMIN));
        utenteRepository.save(new Utente("user1", passwordEncoder.encode("user1"), Ruolo.USER));
    }

    @AfterEach
    void tearDown() {
        jdbc.execute("TRUNCATE utente RESTART IDENTITY CASCADE");
    }

    // --- Autorizzazione sulle pagine ---

    @Test
    void anonimoSuAreaAdminVieneMandatoAlLogin() throws Exception {
        mvc.perform(get("/admin/festivals"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser(authorities = "USER")
    void utenteSenzaRuoloAdminRiceve403() throws Exception {
        mvc.perform(get("/admin/festivals")).andExpect(status().isForbidden());
    }

    @Test
    void homeELoginSonoPubbliche() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
        mvc.perform(get("/login")).andExpect(status().isOk());
    }

    // --- API: la scrittura richiede il login (nessun token CSRF per /rest/**) ---

    @Test
    void anonimoSuApiDiScritturaVieneMandatoAlLogin() throws Exception {
        mvc.perform(post("/rest/movies/1/reviews")
                        .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    void leLettureDelleApiSonoPubbliche() throws Exception {
        mvc.perform(get("/rest/festivals")).andExpect(status().isOk());
        mvc.perform(get("/rest/me")).andExpect(status().isOk());
    }

    // --- Login con form ---

    @Test
    void loginConCredenzialiCorrette() throws Exception {
        mvc.perform(formLogin("/login").user("admin").password("admin"))
                .andExpect(redirectedUrl("/"))
                .andExpect(authenticated().withUsername("admin").withAuthorities(AuthorityUtils.createAuthorityList("ADMIN")));
    }

    @Test
    void loginConPasswordSbagliata() throws Exception {
        mvc.perform(formLogin("/login").user("admin").password("sbagliata"))
                .andExpect(redirectedUrl("/login?error=true"))
                .andExpect(unauthenticated());
    }

    // --- Registrazione ---

    @Test
    void registrazioneValidaCreaUnUtenteUser() throws Exception {
        mvc.perform(post("/register").with(csrf()).param("username", "nuovo").param("password", "segreta"))
                .andExpect(redirectedUrl("/login"))
                .andExpect(flash().attributeExists("messaggio"));

        Utente nuovo = utenteRepository.findByUsername("nuovo").orElseThrow();
        assertThat(nuovo.getRuolo()).isEqualTo(Ruolo.USER);
        assertThat(passwordEncoder.matches("segreta", nuovo.getPassword())).isTrue();
    }

    @Test
    void registrazioneConUsernameEsistenteMostraErroreSulCampo() throws Exception {
        mvc.perform(post("/register").with(csrf()).param("username", "user1").param("password", "segreta"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("utente", "username"));
    }

    @Test
    void registrazioneConPasswordTroppoCorta() throws Exception {
        mvc.perform(post("/register").with(csrf()).param("username", "nuovo").param("password", "x"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("utente", "password"));
        assertThat(utenteRepository.existsByUsername("nuovo")).isFalse();
    }
}
