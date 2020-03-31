package com.steis.manager;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.steis.manager.domain.Cashbox;
import com.steis.manager.domain.Client;
import com.steis.manager.domain.User;
import com.steis.manager.repository.CashboxRepo;
import com.steis.manager.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Streamable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ManagerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void mainPageTest() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Это начальная страница")));
    }

    @Test
    void clientPageTest() throws Exception {
        this.mockMvc.perform(get("/clients"))
                .andDo(print())
                .andExpect(status().isOk())
        .andExpect(content().string("List clients"));
    }

    @Test
    void cashboxPageTest() throws Exception {
        this.mockMvc.perform(get("/cashboxes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Cashboxes list"));
    }

}
