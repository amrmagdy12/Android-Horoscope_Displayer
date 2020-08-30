package com.hanynemr.webservicedemo3;

import android.content.Intent;
import android.net.IpSecManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String>, AdapterView.OnItemClickListener {

    ListView list;

    List<Element> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        list=findViewById(R.id.list);

        RequestQueue queue= Volley.newRequestQueue(this);
        String url="http://560057.youcanlearnit.net/services/xml/itemsfeed.php";
//        String url="https://www.findyourfate.com/rss/horoscope-astrology-feed.php?mode=view";
        StringRequest request=new StringRequest(url,this,this);

        queue.add(request);
        
        list.setOnItemClickListener(this);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response) {

        SAXBuilder builder=new SAXBuilder();
        StringReader reader=new StringReader(response);
        try {
            Document document = builder.build(reader);


            elements = document.getRootElement().getChildren("item");

            ArrayList<String> names=new ArrayList<>();
            for (Element element : elements) {
               names.add(element.getChild("itemName").getText());

            }
            ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,names);
            list.setAdapter(adapter);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent in=new Intent(this,FoodDetailActivity.class);
        in.putExtra("desc",elements.get(position).getChild("description").getText());
        in.putExtra("img","http://560057.youcanlearnit.net/services/images/"+elements.get(position).getChild("image").getText());

        startActivity(in);


    }
}
