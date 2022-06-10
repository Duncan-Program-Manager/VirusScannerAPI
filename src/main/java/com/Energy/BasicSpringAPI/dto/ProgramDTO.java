package com.Energy.BasicSpringAPI.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

public class ProgramDTO {
    private String Name;
    private String Description;
    private String Location;
    private String Version;
    private Date UploadDate;
    private boolean UserUpload;
    private String Username;
    private MultipartFile file;

    public ProgramDTO(String name, String description, String location, String version, Date uploadDate, boolean userUpload, String username, MultipartFile file) {
        Name = name;
        Description = description;
        Location = location;
        Version = version;
        UploadDate = uploadDate;
        UserUpload = userUpload;
        Username = username;
        this.file = file;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public Date getUploadDate() {
        return UploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        UploadDate = uploadDate;
    }

    public boolean isUserUpload() {
        return UserUpload;
    }

    public void setUserUpload(boolean userUpload) {
        UserUpload = userUpload;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
