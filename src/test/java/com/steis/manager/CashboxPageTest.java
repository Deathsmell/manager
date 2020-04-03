package com.steis.manager;

import com.steis.manager.domain.Cashbox;
import com.steis.manager.repository.CashboxRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.yml")
@Sql(value = { })
public class CashboxPageTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CashboxRepo cashboxRepo;

    private String url = "/cashboxes";

    @Test
    void contentCashboxPageTest() throws Exception {
        this.mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(xpath("").nodeCount(0));
    }


    @Test
    void myTest (){

    }

}
