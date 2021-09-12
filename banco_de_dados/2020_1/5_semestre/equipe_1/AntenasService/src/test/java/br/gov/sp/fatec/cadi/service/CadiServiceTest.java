package br.gov.sp.fatec.cadi.service;

import br.gov.sp.fatec.cadi.domain.Cadi;
import br.gov.sp.fatec.cadi.exception.CadiException.CadiInactiveException;
import br.gov.sp.fatec.cadi.exception.CadiException.CadiNotFoundException;
import br.gov.sp.fatec.cadi.repository.CadiRepository;
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

import static br.gov.sp.fatec.cadi.fixture.CadiFixture.newCadi;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CadiServiceTest {

    @InjectMocks
    private CadiService service;

    @Mock
    private CadiRepository repository;

    @Mock
    private ProjectService projectService;

    @Mock
    private SendEmail sendEmail;

    @Test
    public void save_shoudSucceed() {
        Cadi cadi = newCadi();
        when(repository.save(cadi)).thenReturn(cadi);

        Cadi saved = service.save(cadi);

        assertEquals(cadi.getId(), saved.getId());
    }

    @Test
    public void findAll_shouldSucceed() {
        List<Cadi> cadiList = Lists.newArrayList(newCadi(1L, true),
                newCadi(2L, true),
                newCadi(3L, true),
                newCadi(4L, true),
                newCadi(5L, true));

        when(repository.findAll()).thenReturn(cadiList);

        List<Cadi> found = service.findAll();

        assertEquals(cadiList.size(), found.size());
    }

    @Test
    public void findActive_shouldSucceed() {
        List<Cadi> cadiList = Lists.newArrayList(newCadi(1L, true),
                newCadi(2L, true),
                newCadi(3L, true),
                newCadi(4L, true),
                newCadi(5L, true));

        when(repository.findAllByActive(true)).thenReturn(cadiList);

        List<Cadi> found = service.findActive();

        assertEquals(cadiList.size(), found.size());
    }

    @Test
    public void findById_shouldSucceed() {
        Cadi cadi = newCadi();
        when(repository.findById(cadi.getId())).thenReturn(java.util.Optional.of(cadi));

        Cadi found = service.findById(cadi.getId());

        assertEquals(cadi.getId(), found.getId());
    }

    @Test
    public void findById_shouldFail() {
        Assertions.assertThrows(CadiNotFoundException.class, () -> {
            service.findById(1L);
        });
    }

    @Test
    public void update_shouldSucceed() {
        Cadi cadi = newCadi();
        Cadi updated = newCadi();
        updated.setEmail("newEmail@test.com");

        when(repository.findById(cadi.getId())).thenReturn(java.util.Optional.of(cadi));
        when(repository.save(updated)).thenReturn(updated);

        Cadi returned = service.update(cadi.getId(), updated);

        assertEquals(updated.getEmail(), returned.getEmail());
    }

    @Test
    public void update_shouldFail() {
        Cadi updated = newCadi();
        updated.setEmail("newEmail@test.com");

        Assertions.assertThrows(CadiNotFoundException.class, () -> {
            service.update(2L, updated);
        });
    }

    @Test
    public void activate_shouldSucceed() throws JSONException {
        Cadi cadi = newCadi();
        JSONObject base64 = new JSONObject();
        base64.put("dateTime", new Date());
        base64.put("email", cadi.getEmail());
        String b64 = Base64.getEncoder().encodeToString(base64.toString().getBytes());

        when(repository.findByEmail((String) base64.get("email"))).thenReturn(cadi);
        when(repository.save(cadi)).thenReturn(cadi);
        service.activate(b64);
        assertTrue(cadi.isActive());
    }

    @Test
    public void activate_shouldFail() throws JSONException {
        Cadi cadi = newCadi();
        JSONObject base64 = new JSONObject();
        base64.put("dateTime", new Date());
        base64.put("email", cadi.getEmail());
        String b64 = Base64.getEncoder().encodeToString(base64.toString().getBytes());

        Assertions.assertThrows(CadiNotFoundException.class, () -> {
            service.activate(b64);
        });
    }

    @Test
    public void login_shouldSucceed() {
        Cadi cadi = newCadi();

        Map<String, String> login = new HashMap<>();
        login.put("email", cadi.getEmail());
        login.put("password", cadi.getPassword());

        when(repository.findByEmailAndPassword(cadi.getEmail(), Base64.getEncoder().encodeToString(cadi.getPassword().getBytes())))
                .thenReturn(cadi);
        service.login(login);
    }

    @Test
    public void login_shouldFail_notFound() {
        Cadi cadi = newCadi();

        Map<String, String> login = new HashMap<>();
        login.put("email", cadi.getEmail());
        login.put("password", cadi.getPassword());

        Assertions.assertThrows(CadiNotFoundException.class, () -> {
            service.login(login);
        });
    }

    @Test
    public void login_shouldFail_Inactive() {
        Cadi cadi = newCadi();
        cadi.setActive(false);

        Map<String, String> login = new HashMap<>();
        login.put("email", cadi.getEmail());
        login.put("password", cadi.getPassword());

        when(repository.findByEmailAndPassword(cadi.getEmail(), Base64.getEncoder().encodeToString(cadi.getPassword().getBytes())))
                .thenReturn(cadi);

        Assertions.assertThrows(CadiInactiveException.class, () -> {
            service.login(login);
        });
    }
}
