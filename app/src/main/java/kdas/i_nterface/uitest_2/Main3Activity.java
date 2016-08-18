package kdas.i_nterface.uitest_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    java.util.List<events> mevents = new ArrayList<>();
    rv_event_adapter adapter;

    Boolean[] count = new Boolean[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().hide();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_event);
        //setSupportActionBar(toolbar);

        RecyclerView events_rv = (RecyclerView)findViewById(R.id.rv_schedule);

        for (int i = 0; i < 3; ++i){
            count[i] = true;
        }

        for (int i = 0; i < 10000; ++i){
            mevents.add(new events(count));
        }
        adapter = new rv_event_adapter(this, mevents);
        events_rv.setAdapter(adapter);
        events_rv.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();


    }
}
