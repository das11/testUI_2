package kdas.i_nterface.uitest_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.view);
        //setSupportActionBar(toolbar);

        if (toolbar != null)
            toolbar.setTitle("hello");
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_main_1);
        if (fab != null){
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Main2Activity.this, List.class);
                    startActivity(i);
                }
            });
        }

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab_main_2);
        if (fab2 != null){
            fab2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Main2Activity.this, Main3Activity.class);
                    startActivity(i);
                }
            });
        }

        FloatingActionButton fab3 = (FloatingActionButton)findViewById(R.id.fab_main_3);
        if(fab3 != null){
            fab3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Main2Activity.this, MapsActivity.class);
                    startActivity(i);
                }
            });
        }

        FloatingActionButton fab4 = (FloatingActionButton)findViewById(R.id.fab_main_4);
        if(fab != null){
            fab4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Main2Activity.this, Ping.class);
                    startActivity(i);
                }
            });
        }

        FloatingActionButton fab5 = (FloatingActionButton)findViewById(R.id.fab_main_5);
        if (fab5 != null){
            fab5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Main2Activity.this, Init.class);
                    startActivity(i);
                }
            });
        }

        FloatingActionButton fab6 = (FloatingActionButton)findViewById(R.id.fab_main_6);
        if (fab6 != null){
            fab6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Main2Activity.this, share_location.class);
                    startActivity(i);
                }
            });
        }

        FloatingActionButton fab_red = (FloatingActionButton)findViewById(R.id.fab_main_7);
        if (fab_red != null){
            fab_red.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Main2Activity.this, EventTimeline.class);
                    startActivity(i);
                }
            });
        }
    }

}
