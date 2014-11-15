package com.meer07.qiitamagazine;


import android.accounts.NetworkErrorException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

public class HttpRequest {
	public String Request(String path) throws NetworkErrorException {
		try {
			String line;
			StringBuilder builder = new StringBuilder();
			String urlString = path.replace(" ", "%20");
		
			HttpClient client = new DefaultHttpClient();
			HttpGet method = new HttpGet(URI.create(urlString));
			HttpResponse response = client.execute(method);
			InputStream is = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}

            if( response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
            {
                throw new NetworkErrorException(builder.toString());
            }
			
			return builder.toString();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
            throw new NetworkErrorException(e);
//			return null;
		}
		
	}
}
