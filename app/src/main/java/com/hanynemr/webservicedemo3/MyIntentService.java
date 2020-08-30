package com.hanynemr.webservicedemo3;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService implements Response.ErrorListener, Response.Listener<JSONObject> {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected){
            RequestQueue queue = Volley.newRequestQueue(this);
//        fromText.getText().toString().replaceAll(" ","%20");
            String url="http://api.mymemory.translated.net/get?q=hello&langpair=en|it";
            JsonObjectRequest request=new JsonObjectRequest(url,null,this,this);
            queue.add(request);

        }

    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        String tran = null;
        try {
            tran = response.getJSONObject("responseData").getString("translatedText");
            MainActivity.bakhtText.setText(tran);

            //send notification
            Intent in=new Intent(this,MainActivity.class);
//          in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent=PendingIntent.getActivity(this,0,in,0);
            NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"main");
            builder.setTicker("new horoscope").setContentTitle("new horoscope")
                    .setContentText(tran)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            Notification notification = builder.build();

            NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            manager.notify(1,notification);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
