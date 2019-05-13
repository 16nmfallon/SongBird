package edu.uiowa.besttunes;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    @Override
    protected Void doInBackground(Void... voids) {

        try {
            //https://itunes.apple.com/search?term=jack+johnson&entity=song&limit=20
            URL url = new URL("https://itunes.apple.com/search?term=jack+johnson&entity=song&limit=20");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            data = data.substring(31);
            JSONArray JA = new JSONArray(data);
            for(int i = 0; i < JA.length(); i++) {
                JSONObject JO = (JSONObject) JA.get(i);
                MainActivity.trackArray.add((String) JO.get("trackName"));

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("TEST", MainActivity.trackArray.toString());
    }
}
