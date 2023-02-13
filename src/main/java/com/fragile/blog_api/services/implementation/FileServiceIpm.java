package com.fragile.blog_api.services.implementation;

import com.fragile.blog_api.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class FileServiceIpm implements FileService {


    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //        filename
        String name = file.getOriginalFilename();

        //        random names generate files
        String randomId = UUID.randomUUID().toString();
        String filName1 = randomId.concat(name.substring(name.lastIndexOf(".")));

        //        filllpath
        String filePath = path + File.separator + filName1;

        //        create folder if not created
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));

        return filName1;
    }
    @Override
    public InputStream getResources(String path, String fileName) throws FileNotFoundException {
    String fullPath = path+File.separator+fileName;
    InputStream is = new FileInputStream(fullPath);
        return is;
    }
}
