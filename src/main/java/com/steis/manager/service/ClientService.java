package com.steis.manager.service;

import com.steis.manager.domain.Client;
import com.steis.manager.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Transactional
public class ClientService {

    private final ClientRepo clientRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Iterable<Client> getClients (Pageable pageable){
        return clientRepo.findAllByOrderByName(pageable);
    }

    @Value("${upload.clientList.path}")
    private String path;

    public String uploadFile(MultipartFile multipartFile) {
        try {
            byte[] bFile = multipartFile.getBytes();
            File file = new File(path + File.separator + multipartFile.getOriginalFilename());
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            outputStream.write(bFile);
            outputStream.close();

            System.out.println(
                    "File saved under: " + path + File.separator + multipartFile.getOriginalFilename());
            return  "File saved under: " + path + File.separator + multipartFile.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "File upload failed. File is empty";
    }
}
