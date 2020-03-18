package com.steis.manager.service;

import com.steis.manager.domain.Cashbox;
import com.steis.manager.domain.Client;
import com.steis.manager.domain.Master;
import com.steis.manager.repository.CashboxRepo;
import com.steis.manager.repository.ClientRepo;
import com.steis.manager.repository.MasterRepo;
import org.apache.poi.ss.usermodel.Workbook;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ExcelServiceTest {



    @MockBean
    private ClientRepo clientRepo;

    @MockBean
    private MasterRepo masterRepo;


    @MockBean
    private CashboxRepo cashboxRepo;

    @Autowired
    private ExcelService excelService =  new ExcelService(clientRepo,cashboxRepo,masterRepo);

    @Mock
    private File file = mock(File.class);

    @Mock
    private Cashbox cashbox = mock(Cashbox.class);

    @Mock
    private Client client = mock(Client.class);


    @Test
    @Ignore
    void readExcel() throws IOException {

//        doReturn(true).when(clientRepo).save(client);
//        doReturn(true).when(cashboxRepo).save(cashbox);

        excelService.readExcel();

        verify(clientRepo).save(client);


    }


    @Test
    void createExcel() throws IOException {


    }
}