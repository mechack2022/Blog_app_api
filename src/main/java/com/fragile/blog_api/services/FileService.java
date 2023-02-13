package com.fragile.blog_api.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public interface FileService {

    String uploadImage(String path, MultipartFile file) throws IOException;

    InputStream getResources(String path, String fileName) throws FileNotFoundException;


}
