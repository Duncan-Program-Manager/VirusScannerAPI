package com.Energy.BasicSpringAPI.logic.scanneroptions.virustotalwrapper;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

public class VirusScanHook {

    public boolean scanFile(File file)
    {
        System.out.println("[*] Scanning file: " + file);

        byte[] data = new byte[0];
        try {
            data = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String sha256Hex = DigestUtils.sha256Hex(data);

        //The VirusTotal class allows you to search for results using various hashes
        //and details as supported on the VirusTotal search functionality.
        Set<Report> reports = null;
        try {
            reports = VirusScanner.scan(sha256Hex);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("total reports:"+ reports.size());
        //Output the details of each scan result from a vendor
        for (Report report : reports) {
            System.out.println(report.getVendor() + " - " + report.getMalwarename());
        }

        if(reports.size() > 10)
        {
            return false;
        }
        return true;
        //https://github.com/tushroy/virus-total
    }

}
