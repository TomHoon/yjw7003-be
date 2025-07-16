package com.yjw.yjw7003.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {

  private String UPLOAD_DIR = "uploads/";

  public String saveFile(MultipartFile file) {
    Path uploadPath = Paths.get(UPLOAD_DIR);

    String filename = file.getOriginalFilename();
    Path filePath = uploadPath.resolve(filename);

    try {
      file.transferTo(filePath);
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return filePath.toString();
  }
}
