package com.bilgeadam.utility;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileService {

    public String decodeBase64(String image) throws IOException {
        String fileName = generateRandomName();
        File target = new File("pictures-storage/"+fileName);

//                new File("pictures-storage/"+fileName);
//                new File("C:/Users/PC/Desktop/human-backend/HumanResourcesManagement/pictures-storage/"+fileName);
        OutputStream outputStream = new FileOutputStream(target);
        byte [] base64 = Base64.getDecoder().decode(image);
        outputStream.write(base64);
        outputStream.close();
        return fileName;
    }

    private String generateRandomName() {

    return UUID.randomUUID().toString().replaceAll("-","");
    }

}
