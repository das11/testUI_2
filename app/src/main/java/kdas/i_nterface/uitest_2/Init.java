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

    Firebase user, root, pinged, location;

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
