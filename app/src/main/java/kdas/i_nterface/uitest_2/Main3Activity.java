package kdas.i_nterface.uitest_2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    java.util.List<events> mevents = new ArrayList<>();
    RecyclerView events_rv;
    rv_event_adapter adapter;

    int test_rv_inflate = 365;

    //Boolean[] count = new Boolean[3];
    List<String> count = new ArrayList<>();

    String furl, gist_note, user_num;
    Firebase note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Firebase.setAndroidContext(this);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_event);
        //setSupportActionBar(toolbar);

        SharedPreferences pref = getSharedPreferences("prefs", MODE_PRIVATE);
        user_num = pref.getString("Number","");

        events_rv = (RecyclerView)findViewById(R.id.rv_schedule);
        final FloatingActionButton month_fab = (FloatingActionButton)findViewById(R.id.fab_month);
        ImageView down = (ImageView)findViewById(R.id.down);
        final com.github.sundeepk.compactcalendarview.CompactCalendarView month_view = (com.github.sundeepk.compactcalendarview.CompactCalendarView)findViewById(R.id.compactcalendar_view);

        for (int i = 0; i < 3; ++i){
            //count[i] = true;
            count.add(i, "true");
        }

        Date da = new Date();
        int furl_x = getposition_from_time(da);
        Log.d("REAL DAU", furl_x + "");



        furl = "https://wifiap-1361.firebaseio.com/"+ user_num + "/data/" + furl_x + "/gist_note";
        Log.d("furl", furl);
        note = new Firebase(furl);

        note.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gist_note = dataSnapshot.getValue(String.class);
                Log.d("gist", gist_note);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        for (int i = 0; i < test_rv_inflate; ++i){
            mevents.add(new events(count, test_rv_inflate));
        }
        adapter = new rv_event_adapter(this, mevents);
        events_rv.setAdapter(adapter);
        events_rv.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();

        Toast.makeText(getApplicationContext(), test_rv_inflate + " RV rows drawn, dayummm!", Toast.LENGTH_SHORT).show();

        if (down != null){
            down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (month_view != null){
                        if (month_view.getVisibility() == View.INVISIBLE){
                            month_view.setVisibility(View.VISIBLE);
                        }
                        else
                            month_view.setVisibility(View.INVISIBLE);

                    }

                    //((LinearLayoutManager) events_rv.getLayoutManager()).scrollToPositionWithOffset(10, 0);

                }
            });
        }
        //#########
        String d = "01-01-2016";
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

                    Event cev = new Event(R.color.some_teal2, dateClicked.getTime(), "" );
                    month_view.addEvent(cev, true);
                    java.util.List<Event> ev = month_view.getEvents(dateClicked);
                    ((LinearLayoutManager) events_rv.getLayoutManager()).scrollToPositionWithOffset(getposition_from_time(dateClicked), 0);

                    Toast.makeText(getApplicationContext(), ev + "", Toast.LENGTH_LONG).show();
                    Log.d("cev :: ", dateClicked + " " + cev);
                    Log.d("prev_ev ::", ev + "");
                    Log.d("hash", dateClicked.hashCode() + "");
                    Log.d("POS_date", getposition_from_time(dateClicked) + "");
                    Log.d("Date_pos", getdate_from_pos(getposition_from_time(dateClicked)) + "");
                }

                @Override
                public void onMonthScroll(Date firstDayOfNewMonth) {

                }
            });
        }


        //##################### initial offset
        ((LinearLayoutManager) events_rv.getLayoutManager()).scrollToPositionWithOffset(adapter.return_present_pos(), 0);

    }

    public int getposition_from_time(Date date){

        int pos = 0;
        for (long i = 1451586600000L; i < date.getTime(); i += 86400000){
            ++pos;
        }
        return pos;
    }

    public Date getdate_from_pos(int pos){

        Date start = new Date(1451586600000L);
        int count = 0;
        for (int i = 0; i < pos; ++i){
            ++count;
        }
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(start);
        cal.add(Calendar.DATE, count);
        //Date date = cal.getTime();

        return cal.getTime();
    }
}
