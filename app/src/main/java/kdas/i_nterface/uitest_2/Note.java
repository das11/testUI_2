package kdas.i_nterface.uitest_2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Note extends AppCompatActivity {

    String note, user_number, furl, furl2, furlpoint;
    int ipoints;
    Firebase gist_note, note2, points;

    boolean done1 = false;
    boolean done2 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Firebase.setAndroidContext(this);

        getSupportActionBar().hide();

        SharedPreferences pref = getSharedPreferences("prefs", MODE_PRIVATE);
        user_number = pref.getString("Number","");

        final EditText note_ed = (EditText)findViewById(R.id.note_ed);

        FloatingActionButton note_done = (FloatingActionButton)findViewById(R.id.note_done);
        note_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note = note_ed.getText().toString();
                furl = "https://wifiap-1361.firebaseio.com/" + user_number + "/data/" + 244 + "/gist_note";
                furlpoint = "https://wifiap-1361.firebaseio.com/" + user_number + "/data/" + 244 + "/points";

                gist_note = new Firebase(furl);
                points = new Firebase(furlpoint);
                gist_note.setValue(note);
                done2 = true;

                points.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ipoints = dataSnapshot.getValue(int.class);
                        furl2 = "https://wifiap-1361.firebaseio.com/" + user_number + "/data/" + 244 + "/points_data/" + ipoints +"/notes";
                        Log.d("furl2", furl2);
                        note2 = new Firebase(furl2);
                        note2.setValue(note);
                        done1 = true;

                        finish();

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
        });




    }
}
