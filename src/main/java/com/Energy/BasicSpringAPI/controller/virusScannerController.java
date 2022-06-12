package com.Energy.BasicSpringAPI.controller;

import com.Energy.BasicSpringAPI.dto.ProgramDTO;
import com.Energy.BasicSpringAPI.endpoint.virusScannerEndpoints;
import com.Energy.BasicSpringAPI.logic.ScanOptionChooser;
import com.Energy.BasicSpringAPI.rabbitmq.RabbitMQSender;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.nio.charset.StandardCharsets;
import java.util.Date;

@RestController
@RequestMapping(value = virusScannerEndpoints.BASE)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class virusScannerController {
    public static ScanOptionChooser scanOptionChooser = new ScanOptionChooser();

    @Autowired
    private RabbitMQSender rabbitMQSender;

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
            JSONParser parser = new JSONParser();
            Gson gson = new Gson();
            JSONObject fullJson = new JSONObject();
            JSONObject userInfo = null;
            try {
                userInfo = (JSONObject) parser.parse(gson.toJson(dto));
            } catch (ParseException e) {
                e.printStackTrace();
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            userInfo.put("UploadDate", new Date().toString());

            fullJson.put("method", "newProgram");
            fullJson.put("data", userInfo);
            rabbitMQSender.send(new Message(fullJson.toJSONString().getBytes(StandardCharsets.UTF_8)));
            if(filefile.delete())
            {
                System.out.println("deleted the file");
            }
            else
            {
                System.out.println("error deleting file");
            }
            return new ResponseEntity<>( HttpStatus.OK);

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
