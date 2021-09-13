//package br.gov.sp.fatec.cadi.controller;
//
//import br.gov.sp.fatec.cadi.domain.Cadi;
//import br.gov.sp.fatec.cadi.service.CadiService;
//import br.gov.sp.fatec.project.service.ProjectService;
//import org.assertj.core.util.Lists;
//import org.json.JSONObject;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Base64;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//
//import static br.gov.sp.fatec.cadi.fixture.CadiFixture.newCadi;
//import static br.gov.sp.fatec.utils.commons.JSONParser.toJSON;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//public class CadiControllerTest {
//
//    private static final String URL = "/dev/cadi";
//
//    @InjectMocks
//    private CadiController controller;
//
//    @Mock
//    private CadiService service;
//
//    @Mock
//    private ProjectService projectService;
//
//    private MockMvc mockMvc;
//
//     @BeforeEach
//    public void onInit() {
//        mockMvc = MockMvcBuilders.standaloneSetup(controller)
//                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
//                .build();
//    }
//
//    @Test
//    public void findAll_shouldSucceed() throws Exception {
//        List<Cadi> cadiList = Lists.newArrayList(newCadi(1L, true),
//                newCadi(2L, true),
//                newCadi(3L, true));
//
//        when(service.findAll()).thenReturn(cadiList);
//
//        mockMvc.perform(get(URL))
//                .andExpect(status().isOk());
//
//        verify(service).findAll();
//    }
//
//    @Test
//    public void findActive_shouldSucceed() throws Exception {
//        List<Cadi> cadiList = Lists.newArrayList(newCadi(1L, true),
//                newCadi(2L, true),
//                newCadi(3L, true),
//                newCadi(4L, true),
//                newCadi(5L, true));
//
//        when(service.findActive()).thenReturn(cadiList);
//
//        mockMvc.perform(get(URL + "/active"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void findById_shouldSucceed() throws Exception {
//        Cadi cadi = newCadi();
//        when(service.findById(cadi.getId())).thenReturn(cadi);
//
//        mockMvc.perform(get(URL + "/" + cadi.getId()))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void update_ShouldSucceed() throws Exception {
//        Cadi cadi = newCadi();
//        Cadi updated = newCadi();
//        updated.setEmail("newEmail@test.com");
//
//        mockMvc.perform(put(URL + "/" + cadi.getId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(Objects.requireNonNull(toJSON(cadi))))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void activate_shouldSucceed() throws Exception {
//        Cadi cadi = newCadi();
//        JSONObject base64 = new JSONObject();
//        base64.put("dateTime", new Date());
//        base64.put("email", cadi.getEmail());
//        String b64 = Base64.getEncoder().encodeToString(base64.toString().getBytes());
//
//        mockMvc.perform(get(URL + "/activate/" + b64))
//                .andExpect(status().isOk());
//    }
//}
