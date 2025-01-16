package vn.hoidanit.laptopshop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

/*
 * This service class handles file upload functionality.
 * It is responsible for saving uploaded files to the server's file system.
 */
@Service
public class UploadService {

    // ServletContext is injected to access the server's context and determine the root path for file storage.
    private final ServletContext servletContext;

    /*
     * Constructor that initializes the ServletContext dependency.
     * @param servletContext the servlet context used to get the real path of the resources.
     */
    public UploadService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /*
     * This method handles saving the uploaded file to the server's file system.
     * If the file is not empty, it will save the file to the specified target folder within the "images" folder.
     * 
     * @param file the uploaded file to be saved.
     * @param targetFolder the folder where the file will be saved.
     * @return the final name of the saved file, or an empty string if the file is empty.
     */
    public String handleSaveUploadFile(MultipartFile file, String targetFolder) {
        // don't upload file
        if (file.isEmpty()) {
            return "";
        }
        // relative path: absolute path
        String rootPath = this.servletContext.getRealPath("/resources/images");
        String finalName = "";
        
        try {
            byte[] bytes = file.getBytes();

            File dir = new File(rootPath + File.separator + targetFolder);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Create the file on server
            finalName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            File serverFile = new File(dir.getAbsolutePath() + File.separator + finalName);


            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace(); 
        }
        return finalName;
    }
}
