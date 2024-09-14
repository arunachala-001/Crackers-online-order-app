package com.java.crackers.shoppingcart.orderapp.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {

    @Value("${file.upload-dir}")
    private String imagePath;

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String fileName) {
        try{
            Path file = Paths.get(imagePath).resolve(fileName).normalize();
            System.out.println(file.toString());
            Resource resource = new UrlResource(file.toUri());
            System.out.println(resource);

            if(resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+
                                resource.getFilename()+"\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not serve file: " + fileName, e);
        }
    }

    @GetMapping("/hello")
    public String isThere() {
        return "Are you there?";
    }
}
