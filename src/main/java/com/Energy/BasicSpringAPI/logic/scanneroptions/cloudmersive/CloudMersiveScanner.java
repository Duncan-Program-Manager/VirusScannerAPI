package com.Energy.BasicSpringAPI.logic.scanneroptions.cloudmersive;

import com.cloudmersive.client.*;
import com.cloudmersive.client.model.*;
import com.cloudmersive.client.invoker.*;
import com.cloudmersive.client.invoker.auth.*;
import java.io.*;

public class CloudMersiveScanner {

    public boolean mersiveScan(File file)
    {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.cloudmersive.com");
        ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
        Apikey.setApiKey("110b6c3d-fe73-4931-8bf3-e3e65a154563");
        ScanApi apiInstance = new ScanApi(defaultClient);
        try {
            VirusScanResult result = apiInstance.scanFile(file);
            System.out.println(result);
            return result.isCleanResult();
        } catch (ApiException e) {
            System.err.println("Exception when calling ScanApi#scanFile");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
        /*ApiClient client = new ApiClient();
        client.addDefaultHeader("Apikey", "110b6c3d-fe73-4931-8bf3-e3e65a154563");

        client.setReadTimeout(300000);
        client.setWriteTimeout(300000);
        client.setConnectTimeout(300000);

        ApiClient defaultClient = Configuration.getDefaultApiClient();

        // Configure API key authorization: Apikey
        ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
        Apikey.setApiKey("f0c513bc-8c00-4491-830e-3e83b015feb6");

        ConvertDocumentApi apiInstance = new ConvertDocumentApi();
        try {
            Object result = apiInstance.convertDocumentDocxToPdf(file);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ConvertDocumentApi#convertDocumentDocxToPdf");
            e.printStackTrace();
        }*/
        return false;
    }
}
