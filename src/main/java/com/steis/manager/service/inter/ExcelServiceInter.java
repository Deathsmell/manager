package com.steis.manager.service.inter;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ExcelServiceInter {

    void readExcel() throws IOException;
    void createExcel();
}
