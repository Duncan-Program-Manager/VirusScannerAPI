package com.Energy.BasicSpringAPI.logic;

import com.Energy.BasicSpringAPI.logic.scanneroptions.cloudmersive.CloudMersiveScanner;
import com.Energy.BasicSpringAPI.logic.scanneroptions.virustotalwrapper.VirusScanHook;

import java.io.File;

public class ScanOptionChooser {
    private FileSizeChecker sizeChecker;
    private CloudMersiveScanner cloudMersiveScanner;
    private VirusScanHook virusScanHook;

    public ScanOptionChooser()
    {
        sizeChecker = new FileSizeChecker();
        cloudMersiveScanner = new CloudMersiveScanner();
        virusScanHook = new VirusScanHook();
    }

    public boolean scanFile(File file)
    {
        boolean safe = false;
        if(sizeChecker.fileSmallEnoughForCloudmersive(file))
        {
            System.out.println("cloudmersive");
            safe = cloudMersiveScanner.mersiveScan(file);
        }
        else
        {
            System.out.println("virustotal");
            safe = virusScanHook.scanFile(file);
        }
        return safe;
    }
}
