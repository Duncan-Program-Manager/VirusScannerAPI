package com.Energy.BasicSpringAPI.logic;

import java.io.File;

public class FileSizeChecker {

    public boolean fileSmallEnoughForCloudmersive(File file)
    {
        long fileSizeInBytes = file.length();
        // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
        long fileSizeInKB = fileSizeInBytes / 1024;
        // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
        long fileSizeInMB = fileSizeInKB / 1024;
        if(fileSizeInMB > 2)
        {
            return false;
        }
        return true;
    }
}
