package alphanews.newsapp.server.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Zatsepin on 14.08.2014.
 */
public abstract class ServerCommand implements Command {

    public String execute() {
        String response = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            URL url = new URL(getUrl());
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "windows-1251"));
            response = readStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return response;
    }

    private String readStream(BufferedReader inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        String readString;
        while ((readString = inputStream.readLine()) != null) {
            sb.append(readString);
        }
        return sb.toString();
    }

    abstract String getUrl();
}
