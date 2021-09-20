package com.company.thl.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thl.spring.Application;
import com.thl.spring.config.WebSecurityConfig;
import com.thl.spring.controller.SneakersController;
import com.thl.spring.model.Sneakers;
import com.thl.spring.service.SneakersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WithMockUser(value = "Test")
@SpringBootTest(classes = {Application.class, WebSecurityConfig.class})
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class SneakersTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SneakersController sneakersController;

    @Autowired
    private SneakersService sneakersService;

    private final Random random = new Random();

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void testSave() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(put("/sneakers/").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(makeRandomSneakers()))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus(), is(HttpStatus.OK.value()));
        assertThat(Integer.valueOf(response.getContentAsString()), is(1));
    }

    @Test
    public void testFindByFirm() throws Exception {
        Sneakers sneakers = makeRandomSneakers();
        sneakersService.save(sneakers);
        MockHttpServletResponse response = mockMvc
                .perform(get("/sneakers/firms/{firm}", sneakers.getFirm())).andReturn().getResponse();

        assertThat(response.getStatus(), is(HttpStatus.OK.value()));
        assertThat(response.getContentAsString(), is(objectMapper.writeValueAsString(List.of(sneakers))));
    }

    @Test
    public void testFindById() throws Exception {
        Sneakers sneakers = makeRandomSneakers();
        int id = sneakersService.save(sneakers);
        MockHttpServletResponse response = mockMvc
                .perform(get("/sneakers/{id}", id)).andReturn().getResponse();

        assertThat(response.getStatus(), is(HttpStatus.OK.value()));
        assertThat(response.getContentAsString(), is(objectMapper.writeValueAsString(sneakers)));
    }

    @Test
    public void testDelete() throws Exception {
        Sneakers sneakers = makeRandomSneakers();
        int id = sneakersService.save(sneakers);
        MockHttpServletResponse response = mockMvc.perform(delete("/sneakers/{id}", id))
                .andReturn().getResponse();

        assertThat(response.getStatus(), is(HttpStatus.OK.value()));
    }

    private Sneakers makeRandomSneakers() {
        return new Sneakers(0, UUID.randomUUID().toString(),
                UUID.randomUUID().toString(), random.nextInt(), random.nextInt());
    }
}
