package com.hanynemr.webservicedemo3;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.StringReader;

public class MainActivity extends AppCompatActivity /*implements Response.ErrorListener, Response.Listener<String>*/ {

    public static TextView bakhtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bakhtText=findViewById(R.id.bakhtText);

        Intent in=new Intent(this,MyIntentService.class);
        PendingIntent pendingIntent=PendingIntent.getService(this,0,in,0);

        AlarmManager manager= (AlarmManager) getSystemService(ALARM_SERVICE);
        manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME
                , SystemClock.elapsedRealtime()+30*60*1000
        ,AlarmManager.INTERVAL_DAY,pendingIntent);

    }

    public void bakhty(View view) {
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url="http://widgets.fabulously40.com/horoscope.xml?sign=cancer";
//        StringRequest request=new StringRequest(url,this,this);
//        queue.add(request);

        Intent in=new Intent(this,MyIntentService.class);
        startService(in);

    }

//    @Override
//    public void onErrorResponse(VolleyError error) {
//
//    }
//
//    @Override
//    public void onResponse(String response) {
//        SAXBuilder saxBuilder=new SAXBuilder();
//        StringReader reader=new StringReader(response);
//        try {
//            Document document = saxBuilder.build(reader);
//
//            String horoscope = document.getRootElement().getAttributeValue("horoscope");
//            bakhtText.setText(horoscope);
//
//
//        } catch (JDOMException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
