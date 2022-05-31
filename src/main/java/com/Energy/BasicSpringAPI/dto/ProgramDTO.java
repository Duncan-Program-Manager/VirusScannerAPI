package com.Energy.BasicSpringAPI.dto;

import java.io.File;
import java.util.Date;

public class ProgramDTO {
    private String Name;
    private String Description;
    private String Location;
    private String Version;
    private Date UploadDate;
    private boolean UserUpload;
    private String username;
    private File file;

    public ProgramDTO(String name, String description, String location, String version, Date uploadDate, boolean userUpload, String username, File file) {
        Name = name;
        Description = description;
        Location = location;
        Version = version;
        UploadDate = uploadDate;
        UserUpload = userUpload;
        this.username = username;
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
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
