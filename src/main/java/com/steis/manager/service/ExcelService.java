package com.steis.manager.service;

import com.steis.manager.domain.Cashbox;
import com.steis.manager.domain.Client;
import com.steis.manager.domain.Master;
import com.steis.manager.repository.CashboxRepo;
import com.steis.manager.repository.ClientRepo;
import com.steis.manager.repository.MasterRepo;
import com.steis.manager.service.inter.ExcelServiceInter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ExcelService implements ExcelServiceInter {


    // insert dependency
    private final ClientRepo clientRepo;
    private final CashboxRepo cashboxRepo;
    private final MasterRepo masterRepo;

    // fields
    @Value("${upload.clientList.path.xls}")
    private String path;
    private File file;
    private int manyClients = 0;
    private Client client = new Client();
    private String oldClientName = null;


    @Autowired
    public ExcelService(ClientRepo clientRepo, CashboxRepo cashboxRepo, MasterRepo masterRepo) {
        this.clientRepo = clientRepo;
        this.cashboxRepo = cashboxRepo;
        this.masterRepo = masterRepo;
    }

    // create table header name
    @PostConstruct
    private void postConstr (){
        int i = 0;
        for (String name : nameCell) {
            tableName.put(name, i++);
        }
    }

    // table names
    private String[] nameCell = {
                                // 12 столбцов
            "#",                // int
            "Номер договора",   // String
            "Дата ввода",       // Date
            "УНП",              // Integer
            "Имя фирмы",        // String
            "СКНО",             // boolean
            "Наименование КСА", // String
            "№ КСА",            // String
            "Адрес",            // String
            "Год выпуска",      // Date
            "Имя мастера",      // String
            "Email"             // String

    };

    // map for numeration tables
    private Map<String, Integer> tableName = new HashMap<>();


    public void readExcel() throws IOException {

        boolean flag = false; // check is newClient
        file = new File(path);

        // create structure xls file
        Workbook workbook = new HSSFWorkbook(new FileInputStream(file));
        Sheet sheet = workbook.getSheet("Список клиентов");


        while (++manyClients < sheet.getLastRowNum()) {
            Cashbox cashbox = new Cashbox();
            Master master = null;


            Row row = sheet.getRow(manyClients);


            if (row.getCell(tableName.get("Номер договора")) != null) {
                client.setContract(row.getCell(tableName.get("Номер договора")).getStringCellValue());
            }
            if (row.getCell(tableName.get("Дата ввода")) != null) {
                cashbox.setDateEnter(row.getCell(tableName.get("Дата ввода")).getDateCellValue());
            }
            if (row.getCell(tableName.get("УНП")) != null) {
                client.setVat((int) row.getCell(tableName.get("УНП")).getNumericCellValue());
            }
            if (row.getCell(tableName.get("Имя фирмы")) != null) {
                String newClientName = row.getCell(tableName.get("Имя фирмы")).getStringCellValue();
                // check is new client
                if (!newClientName.equals(oldClientName) || oldClientName.isEmpty()){
                    oldClientName = newClientName;
                    flag = true;
                }
                client.setName(newClientName);
            }
            if (row.getCell(tableName.get("СКНО")) != null) {
                cashbox.setSkno(row.getCell(tableName.get("СКНО")).getStringCellValue().contains("СКНО"));
            }
            if (row.getCell(tableName.get("Наименование КСА")) != null) {
                cashbox.setNameModel(row.getCell(tableName.get("Наименование КСА")).getStringCellValue());
            }
            if (row.getCell(tableName.get("№ КСА")) != null) {
                cashbox.setSerialNumber(row.getCell(tableName.get("№ КСА")).getStringCellValue());
            }
            if (row.getCell(tableName.get("Адрес")) != null) {
                cashbox.setAddress(row.getCell(tableName.get("Адрес")).getStringCellValue());
            }
            if (row.getCell(tableName.get("Год выпуска")) != null) {
                cashbox.setDateCreate(row.getCell(tableName.get("Год выпуска")).getDateCellValue());
            }
            if (row.getCell(tableName.get("Имя мастера")) != null) {
                master = masterRepo.findByName(row.getCell(tableName.get("Имя мастера")).getStringCellValue());
                cashbox.setMaster(master);
            }
            if (row.getCell(tableName.get("Email")) != null) {
                client.setMail(row.getCell(tableName.get("Email")).getStringCellValue());
            }

            client.getCashboxes().add(cashbox);
            cashbox.setClient(client);
            cashboxRepo.save(cashbox);

            if (flag) { // if is new clients
                clientRepo.save(client);
                client = new Client();
                flag = false;
            }
        }


    }

    public void createExcel() {
        file = new File(path);

        // create new struct xls file
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Список клиентов");
        Row row = sheet.createRow(manyClients);

        // field style and format
        DataFormat format = workbook.createDataFormat();
        CellStyle dataStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        CellStyle defaultStyle = workbook.createCellStyle();
        // style
        dataStyle.setDataFormat(format.getFormat("dd.mm.yy"));


        // create a table and give name
        for (Map.Entry<String, Integer> item : tableName.entrySet()) {
            Cell newCell = row.createCell(item.getValue());
            newCell.setCellValue(item.getKey());
        }

        List<Client> clients = clientRepo.findAll();

        for (Client client : clients) {

            List<Cashbox> cashboxes = cashboxRepo.findByClient(client);

            for (Cashbox cashbox : cashboxes) {

                int i = 0;

                Cell dataCell = null;

                row = sheet.createRow(++manyClients);


                row.createCell(tableName.get("#")).setCellValue(manyClients); // номер строки

                row.createCell(tableName.get("Номер договора")).setCellValue(client.getContract()); // номер договора

                dataCell = row.createCell(tableName.get("Дата ввода"));
                dataCell.setCellStyle(dataStyle);
                dataCell.setCellValue(cashbox.getDateEnter());// дата ввода

                Integer vatValue = client.getVat(); // УНП
                if (vatValue != null) {
                    row.createCell(tableName.get("УНП")).setCellValue(vatValue);
                }

                row.createCell(tableName.get("Имя фирмы")).setCellValue(client.getName()); // название организации

                if (cashbox.isSkno()) // есть ли скно
                    row.createCell(tableName.get("СКНО")).setCellValue("СКНО");

                row.createCell(tableName.get("Наименование КСА")).setCellValue(cashbox.getNameModel()); // имя модели КСА

//                row.createCell(tableName.get("№ КСА")).setCellValue(cashbox.getSerialNumber()); // серийный номер

                row.createCell(tableName.get("Адрес")).setCellValue(cashbox.getAddress()); // адрес

                dataCell = row.createCell(tableName.get("Год выпуска"));
                dataCell.setCellStyle(dataStyle);
                dataCell.setCellValue(cashbox.getDateCreate()); // дата


            }
        }

        for (int i = 0; i < tableName.size(); i++) {
            sheet.autoSizeColumn(i);
        }

        // создание и запись в файл
        try {
            workbook.write(new FileOutputStream(file));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
