package com.redpepper.fooble;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpConnection {

    String json;

    JSONObject jobj;

    public HttpConnection(){}

    public JSONObject makeHttpUrlRequest(URL url, HashMap<String,String> params,String requesttype){

        if(requesttype.equalsIgnoreCase("Post")){

            try{

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setConnectTimeout(15000);
                connection.setReadTimeout(10000);
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                OutputStream outstream = connection.getOutputStream();

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outstream, "UTF-8"));

                writer.write(getquery(params));

                writer.flush();

                writer.close();

                outstream.close();

                connection.connect();

                int status = connection.getResponseCode();

                if(status == 200 || status == 201){

                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

                    StringBuilder sb = new StringBuilder();

                    String line;

                    while( (line = br.readLine())!= null ){

                        sb.append(line+"\n");
                    }

                    br.close();

                    json = sb.toString();

                }else{

                    Log.d("http connection status", "" + status);

                }

            }catch (MalformedURLException ex){

            }catch (IOException e){

                Log.d("exception", "IOException"+e.toString());
            }


        }else if(requesttype.equalsIgnoreCase("Get")){

            try{
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setConnectTimeout(15000);
                connection.setReadTimeout(10000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);

                connection.connect();

                int status = connection.getResponseCode();

                if(status == 200 || status == 201){

                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

                    StringBuilder sb = new StringBuilder();

                    String line;

                    while( (line = br.readLine())!= null ){

                        sb.append(line+"\n");
                    }

                    br.close();

                    json = sb.toString();

                }else{

                    Log.d("http connection status", "" + status);

                }

            }
            catch (MalformedURLException ex){ }
            catch (IOException ex){ Log.d("exception", "IOException"+ex.toString()); }

        }

        try{

            jobj = new JSONObject(json);

        }catch (JSONException e){

            try {

                jobj = new JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1));

            }catch (Exception e0) {

                Log.e("JSON Parser0", "Error parsing data [" + e0.getMessage()+"] "+json);

                Log.e("JSON Parser0", "Error parsing data " + e0.toString());

                try {

                    jobj = new JSONObject(json.substring(1));

                }catch (Exception e1) {

                    Log.e("JSON Parser1", "Error parsing data [" + e1.getMessage()+"] "+json);

                    Log.e("JSON Parser1", "Error parsing data " + e1.toString());

                    try {

                        jobj = new JSONObject(json.substring(2));

                    }catch (Exception e2) {

                        Log.e("JSON Parser2", "Error parsing data [" + e2.getMessage()+"] "+json);

                        Log.e("JSON Parser2", "Error parsing data " + e2.toString());

                        try {

                            jobj = new JSONObject(json.substring(3));

                        }catch (Exception e3) {

                            Log.e("JSON Parser3", "Error parsing data [" + e3.getMessage()+"] "+json);

                            Log.e("JSON Parser3", "Error parsing data " + e3.toString());

                        }

                    }

                }

            }

        }

        return jobj;
    }

    private String getquery(HashMap<String,String> params) throws UnsupportedEncodingException {

        StringBuilder result = new StringBuilder();

        boolean first =  true ;

        for(Map.Entry<String,String> entry : params.entrySet()){

            if(first)

                first = false;

            else

                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(),"UTF-8" ));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(),"UTF-8"));


        }

        return result.toString();


        }

}
