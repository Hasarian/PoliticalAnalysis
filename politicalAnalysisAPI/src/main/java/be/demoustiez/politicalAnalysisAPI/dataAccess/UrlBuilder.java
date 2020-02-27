package be.demoustiez.politicalAnalysisAPI.dataAccess;

import be.demoustiez.politicalAnalysisAPI.configuration.ConfigurationLoader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class UrlBuilder<T> {
    private String code;
    private ConfigurationLoader resourceLoader;
    private String jsonResponseValue="json";
    private String jsonResponseTag="f";
    private Type type;

    public UrlBuilder(ConfigurationLoader resources,String code,Type type){
        this.type=type;
        this.code=code;
        this.resourceLoader=resources;
    }

    public T sendRequest(HashMap<String,String>args){
        args.put(jsonResponseTag,jsonResponseValue);
        HttpURLConnection con=null;
        T responseContent=null;
        String urlString=this.resourceLoader.getURL(code);
        try {
            urlString+="?"+getParamsString(args);
            URL url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("Sending get request : "+ url);
            System.out.println("Response code : "+ responseCode);
            if(responseCode==302){
                System.out.println("redirection : "+con.getHeaderField("Location"));
                urlString=con.getHeaderField("Location");
                url = new URL(urlString);
                con = (HttpURLConnection) url.openConnection();
                responseCode = con.getResponseCode();
                System.out.println("Sending get request to redirected url: "+ url);
                System.out.println("Response code : "+ responseCode);

            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
            Gson gson = new Gson();
            responseContent= gson.fromJson(response.toString(),type);

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(con!=null){
                con.disconnect();
            }
            return responseContent;
        }
    }
    private static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }

}
