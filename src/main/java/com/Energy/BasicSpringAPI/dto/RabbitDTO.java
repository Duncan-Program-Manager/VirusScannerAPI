package com.Energy.BasicSpringAPI.dto;

import org.springframework.web.multipart.MultipartFile;

public class RabbitDTO {
    private String Name;
    private String Description;
    private String Version;
    private boolean UserUpload;
    private String Username;
    private byte[] file;

    public RabbitDTO(String name, String description, String version, boolean userUpload, String username, byte[] file) {
        Name = name;
        Description = description;
        Version = version;
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

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
