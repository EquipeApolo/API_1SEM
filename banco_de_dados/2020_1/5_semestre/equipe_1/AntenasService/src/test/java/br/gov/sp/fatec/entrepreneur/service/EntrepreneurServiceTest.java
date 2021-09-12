package br.gov.sp.fatec.entrepreneur.service;

import br.gov.sp.fatec.entrepreneur.domain.Entrepreneur;
import br.gov.sp.fatec.entrepreneur.exception.EntrepreneurException.EntrepreneurInactiveException;
import br.gov.sp.fatec.entrepreneur.exception.EntrepreneurException.EntrepreneurNotFoundException;
import br.gov.sp.fatec.entrepreneur.repository.EntrepreneurRepository;
import br.gov.sp.fatec.project.service.ProjectService;
import br.gov.sp.fatec.utils.commons.SendEmail;
import org.assertj.core.util.Lists;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static br.gov.sp.fatec.entrepreneur.fixture.EntrepreneurFixture.newEntrepreneur;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EntrepreneurServiceTest {
    @InjectMocks
    private EntrepreneurService service;

    @Mock
    private EntrepreneurRepository repository;

    @Mock
    private ProjectService projectService;

    @Mock
    private SendEmail sendEmail;

    @Test
    public void save_shoudSucceed() {
        Entrepreneur entrepreneur = newEntrepreneur();
        when(repository.save(entrepreneur)).thenReturn(entrepreneur);

        Entrepreneur saved = service.save(entrepreneur);

        assertEquals(entrepreneur.getId(), saved.getId());
    }

    @Test
    public void findAll_shouldSucceed() {
        List<Entrepreneur> entrepreneurList = Lists.newArrayList(newEntrepreneur(1L, true),
                newEntrepreneur(2L, true),
                newEntrepreneur(3L, true),
                newEntrepreneur(4L, true),
                newEntrepreneur(5L, true));

        when(repository.findAll()).thenReturn(entrepreneurList);

        List<Entrepreneur> found = service.findAll();

        assertEquals(entrepreneurList.size(), found.size());
    }

    @Test
    public void findActive_shouldSucceed() {
        List<Entrepreneur> entrepreneurList = Lists.newArrayList(newEntrepreneur(1L, true),
                newEntrepreneur(2L, true),
                newEntrepreneur(3L, true),
                newEntrepreneur(4L, true),
                newEntrepreneur(5L, true));

        when(repository.findAllByActive(true)).thenReturn(entrepreneurList);

        List<Entrepreneur> found = service.findActive();

        assertEquals(entrepreneurList.size(), found.size());
    }

    @Test
    public void findById_shouldSucceed() {
        Entrepreneur entrepreneur = newEntrepreneur();
        when(repository.findById(entrepreneur.getId())).thenReturn(Optional.of(entrepreneur));

        Entrepreneur found = service.findById(entrepreneur.getId());

        assertEquals(entrepreneur.getId(), found.getId());
    }

    @Test
    public void findById_shouldFail() {
        Assertions.assertThrows(EntrepreneurNotFoundException.class, () -> {
            service.findById(1L);
        });
    }

    @Test
    public void update_shouldSucceed() {
        Entrepreneur entrepreneur = newEntrepreneur();
        Entrepreneur updated = newEntrepreneur();
        updated.setEmail("newEmail@test.com");

        when(repository.findById(entrepreneur.getId())).thenReturn(java.util.Optional.of(entrepreneur));
        when(repository.save(updated)).thenReturn(updated);

        Entrepreneur returned = service.update(entrepreneur.getId(), updated);

        assertEquals(updated.getEmail(), returned.getEmail());
    }

    @Test
    public void update_shouldFail() {
        Entrepreneur updated = newEntrepreneur();

        Assertions.assertThrows(EntrepreneurNotFoundException.class, () -> {
            service.update(2L, updated);
        });
    }

    @Test
    public void login_shouldSucceed() {
        Entrepreneur entrepreneur = newEntrepreneur();

        Map<String, String> login = new HashMap<>();
        login.put("email", entrepreneur.getEmail());
        login.put("password", entrepreneur.getPassword());

        when(repository.findByEmailAndPassword(entrepreneur.getEmail(), Base64.getEncoder().encodeToString(entrepreneur.getPassword().getBytes()))).thenReturn(entrepreneur);
        service.login(login);
    }

    @Test
    public void login_shouldFail_notFound() {
        Entrepreneur entrepreneur = newEntrepreneur();

        Map<String, String> login = new HashMap<>();
        login.put("email", entrepreneur.getEmail());
        login.put("password", entrepreneur.getPassword());

        Assertions.assertThrows(EntrepreneurNotFoundException.class, () -> {
            service.login(login);
        });
    }

    @Test
    public void login_shouldFail_Inactive() {
        Entrepreneur entrepreneur = newEntrepreneur();
        entrepreneur.setActive(false);

        Map<String, String> login = new HashMap<>();
        login.put("email", entrepreneur.getEmail());
        login.put("password", entrepreneur.getPassword());

        when(repository.findByEmailAndPassword(entrepreneur.getEmail(), Base64.getEncoder().encodeToString(entrepreneur.getPassword().getBytes()))).thenReturn(entrepreneur);

        Assertions.assertThrows(EntrepreneurInactiveException.class, () -> {
            service.login(login);
        });
    }

    @Test
    public void activate_shouldSucceed() throws JSONException {
        Entrepreneur entrepreneur = newEntrepreneur();
        JSONObject base64 = new JSONObject();
        base64.put("dateTime", new Date());
        base64.put("email", entrepreneur.getEmail());
        String b64 = Base64.getEncoder().encodeToString(base64.toString().getBytes());

        when(repository.findByEmail((String) base64.get("email"))).thenReturn(entrepreneur);
        service.activate(b64);
        assertTrue(entrepreneur.isActive());
    }

    @Test
    public void activate_shouldFail() throws JSONException {
        Entrepreneur entrepreneur = newEntrepreneur();
        JSONObject base64 = new JSONObject();
        base64.put("dateTime", new Date());
        base64.put("email", entrepreneur.getEmail());
        String b64 = Base64.getEncoder().encodeToString(base64.toString().getBytes());


        Assertions.assertThrows(EntrepreneurNotFoundException.class, () -> {
            service.activate(b64);
        });
    }
}
