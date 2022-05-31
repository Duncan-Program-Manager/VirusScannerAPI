package com.Energy.BasicSpringAPI.controller;

import com.Energy.BasicSpringAPI.dto.ProgramDTO;
import com.Energy.BasicSpringAPI.endpoint.virusScannerEndpoints;
import com.Energy.BasicSpringAPI.logic.ScanOptionChooser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> scanForVirus(@RequestBody ProgramDTO dto)
    {
        if(scanOptionChooser.scanFile(dto.getFile()))
        {
            return new ResponseEntity<>( HttpStatus.OK);
            //TODO add rabbitMQ for sending file to the right services

        }
        return new ResponseEntity<>("Possible virus found. Contact a admin for manual testing if sure there is no virus", HttpStatus.BAD_REQUEST);
    }
}
