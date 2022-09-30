package com.seasolutions.testjava.resources;

import com.seasolutions.testjava.TestjavaApplication;
import com.seasolutions.testjava.builder.SectorBuilder;
import com.seasolutions.testjava.entities.Sector;
import com.seasolutions.testjava.services.dto.SectorDTO;
import com.seasolutions.testjava.util.TestUtil;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestjavaApplication.class)
public class SectorResourceTest {

    private static final String BASE_URL = "/api/sectors";
    private static final Long ID_INEXISTENTE = 10000L;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    private MockMvc mockMvc;

    @Autowired
    private SectorBuilder sectorBuilder;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.sectorBuilder.setCustomizacao(null);
        this.sectorBuilder.delete();
    }

    @Test
    @SneakyThrows
    public void inserir() {
        SectorDTO dto  = sectorBuilder.construirObjDTO();
        mockMvc.perform(post(BASE_URL)
                .content(TestUtil.convertObjectToJsonBytes(dto))
                .contentType(APPLICATION_JSON_UTF8) )
                .andExpect(status().isCreated());

    }

    @Test
    @SneakyThrows
    public void buscarTodos() {
        Sector sector = sectorBuilder.construir();
        mockMvc.perform(get(BASE_URL).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void buscarPorIdSucesso() {
        Sector sector = sectorBuilder.construir();
        String idSector = "/" + sector.getId();

        mockMvc.perform(get(BASE_URL + idSector)
                .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void buscarPorIdNaoEncontrado() {
        String idSector = "/" + ID_INEXISTENTE;
        mockMvc.perform(get(BASE_URL + idSector)
                .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    public void desativarUsuario() {
        Sector sector = sectorBuilder.construir();
        mockMvc.perform(delete(BASE_URL + sector.getId())
                .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }
}