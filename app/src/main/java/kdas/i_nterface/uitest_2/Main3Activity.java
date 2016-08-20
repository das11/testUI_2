package kdas.i_nterface.uitest_2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main3Activity extends AppCompatActivity {

    java.util.List<events> mevents = new ArrayList<>();
    rv_event_adapter adapter;

    int test_rv_inflate = 10000;

    Boolean[] count = new Boolean[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().hide();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_event);
        //setSupportActionBar(toolbar);

        RecyclerView events_rv = (RecyclerView)findViewById(R.id.rv_schedule);
        final FloatingActionButton month_fab = (FloatingActionButton)findViewById(R.id.fab_month);
        final com.github.sundeepk.compactcalendarview.CompactCalendarView month_view = (com.github.sundeepk.compactcalendarview.CompactCalendarView)findViewById(R.id.compactcalendar_view);

        for (int i = 0; i < 3; ++i){
            count[i] = true;
        }

        for (int i = 0; i < test_rv_inflate; ++i){
            mevents.add(new events(count));
        }
        adapter = new rv_event_adapter(this, mevents);
        events_rv.setAdapter(adapter);
        events_rv.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();

        Toast.makeText(getApplicationContext(), test_rv_inflate + " RV rows drawn, dayummm!", Toast.LENGTH_SHORT).show();

        if (month_fab != null){
            month_fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (month_view != null){
                        if (month_view.getVisibility() == View.INVISIBLE){
                            month_view.setVisibility(View.VISIBLE);
                        }
                        else
                            month_view.setVisibility(View.INVISIBLE);

                    }
                }
            });
        }
        //#########
        String d = "08-02-2016";
        SimpleDateFormat parser = new SimpleDateFormat("MM-dd-yyyy");
        Date test = new Date();
        try {
            test = parser.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Event test_ev = new Event(R.color.some_teal2, test.getTime(), "This is a test event, which will later track the av. activity fetched from Firebase !");
        if (month_view != null)
            month_view.addEvent(test_ev);

        //#########
        if (month_view != null){
            month_view.setListener(new CompactCalendarView.CompactCalendarViewListener() {
                @Override
                public void onDayClick(Date dateClicked) {
//                    Calendar cal = Calendar.getInstance();
//                    int month = cal.get(Calendar.MONTH);
//                    int day = cal.get(Calendar.DAY_OF_MONTH);
//                    int hour = cal.get(Calendar.HOUR_OF_DAY);
//                    int min = cal.get(Calendar.MINUTE);
//                    int sec = cal.get(Calendar.SECOND);
//
//                    cal.set(month,day,hour,min,sec);
//                    Log.d("CAL ", cal + "");
                    //################################################### TODO
                    // current date object - dateClicked doesnt fetch time

                    Log.d("Date clicked", dateClicked + "");
                    Date date = dateClicked;

                    Event cev = new Event(R.color.some_teal2, date.getTime(), "" );
                    month_view.addEvent(cev, true);
                    java.util.List<Event> ev = month_view.getEvents(dateClicked);
                    Toast.makeText(getApplicationContext(), ev + "", Toast.LENGTH_LONG).show();
                    Log.d("cev :: ", date + "" + cev);
                    Log.d("prev_ev ::", ev + "");
                }

                @Override
                public void onMonthScroll(Date firstDayOfNewMonth) {

                }
            });
        }



    }
}
