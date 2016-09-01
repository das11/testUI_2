package kdas.i_nterface.uitest_2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class Init extends AppCompatActivity {

    String num;
    static String pref = "prefs";
    EditText number;
    FloatingActionButton done_done;
    CardView init_card;

    SharedPreferences sharedPreferences;

    Firebase user, root, pinged, location, notif, data, day, gist_note, points, points_data, points_data_node, check_in_count, check_ins, start_time, end_time, point_location, notes, check_ins_data;
    int fday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        Firebase.setAndroidContext(this);
        root = new Firebase("https://wifiap-1361.firebaseio.com");

        getSupportActionBar().hide();

        sharedPreferences = getSharedPreferences(pref,MODE_PRIVATE);

        number = (EditText)findViewById(R.id.editText);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab_init);
        done_done = (FloatingActionButton)findViewById(R.id.done_done);
        init_card = (CardView)findViewById(R.id.init_card);

        done_done.setVisibility(View.INVISIBLE);

        if (fab != null){
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    num = number.getText().toString();
                    Log.d("num", num);

                    user = root.child(num);
                    user.setValue("#");

                    pinged = user.child("pinged");
                    pinged.setValue("false");

                    location = user.child("location");
                    location.setValue("#");

                    notif = user.child("notif");
                    notif.setValue("false");

                    data = user.child("data");
                    for (int i = 245; i < 250; ++i){
                        day = data.child(i + "");

                        gist_note = day.child("gist_note");
                        gist_note.setValue("    Your notes lie within.");
                        points = day.child("points");
                        points.setValue(2);

                        points_data = day.child("points_data");
                        for (int j = 0; j <2; ++j){
                            points_data_node = points_data.child(j+"");

                            check_in_count = points_data_node.child("check_in_count");
                            start_time = points_data_node.child("start_time");
                            end_time = points_data_node.child("end_time");
                            notes = points_data_node.child("notes");
                            check_ins = points_data_node.child("check_ins");
                            point_location = points_data_node.child("location");

                            check_ins_data = check_ins.child(0 + "");
                            check_ins_data.setValue("loc");

                            check_in_count.setValue(1);
                            start_time.setValue("1234");
                            end_time.setValue("1534");
                            notes.setValue("No notes saved");
                            point_location.setValue("#");
                        }


                    }

                    Log.d("user", user.toString());

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Number", num);
                    editor.commit();

                    init_card.setVisibility(View.INVISIBLE);
                    done_done.setVisibility(View.VISIBLE);
                    finish();
                }
            });
        }


    }
}
