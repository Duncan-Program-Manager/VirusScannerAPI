package com.Energy.BasicSpringAPI.controller;

import com.Energy.BasicSpringAPI.dto.ProgramDTO;
import com.Energy.BasicSpringAPI.endpoint.virusScannerEndpoints;
import com.Energy.BasicSpringAPI.logic.ScanOptionChooser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping(value = virusScannerEndpoints.BASE)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class virusScannerController {
    public static ScanOptionChooser scanOptionChooser = new ScanOptionChooser();

    @PostMapping(value = virusScannerEndpoints.TEST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> testCall()
    {
        return new ResponseEntity<>("works!", HttpStatus.OK);
    }

    @PostMapping(value = virusScannerEndpoints.SCAN, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> scanForVirus(@ModelAttribute ProgramDTO dto)
    {
        File filefile = new File(dto.getFile().getOriginalFilename());
        try (OutputStream os = new FileOutputStream(filefile)) {
            os.write(dto.getFile().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("error with file", HttpStatus.BAD_REQUEST);
        }
        if(scanOptionChooser.scanFile(filefile))
        {
            return new ResponseEntity<>( HttpStatus.OK);
            //TODO add rabbitMQ for sending file to the right services
        }
        return new ResponseEntity<>("Possible virus found. Contact a admin for manual testing if sure there is no virus", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/testScan", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> testscan(@RequestBody MultipartFile file)
    {
        System.out.println("going to scan now");
        File filefile = new File(file.getOriginalFilename());
        try (OutputStream os = new FileOutputStream(filefile)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.getSize());
        if(scanOptionChooser.scanFile(filefile))
        {
            if(filefile.delete())
            {
                System.out.println("deleted the file");
            }
            else
            {
                System.out.println("error deleting file");
            }
            return new ResponseEntity<>( HttpStatus.OK);
            //TODO add rabbitMQ for sending file to the right services

        }
        return new ResponseEntity<>("Possible virus found. Contact a admin for manual testing if sure there is no virus", HttpStatus.BAD_REQUEST);
    }
}
