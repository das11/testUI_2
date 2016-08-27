package kdas.i_nterface.uitest_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class Ping extends AppCompatActivity {

    Firebase peers;

    java.util.List<String> con_num = new ArrayList<>();
    java.util.List<Contacts> con = new ArrayList<>();

    Contact_adapter_ping adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);

        getSupportActionBar().hide();
        RecyclerView rv = (RecyclerView)findViewById(R.id.view_rv_ping);

        adapter = new Contact_adapter_ping(this, con);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        final com.bhargavms.dotloader.DotLoader loader = (com.bhargavms.dotloader.DotLoader)findViewById(R.id.text_dot_loader_ping);
        final com.bhargavms.dotloader.DotLoader loader2 = (com.bhargavms.dotloader.DotLoader)findViewById(R.id.text_dot_loader_toolbar_ping_2);

        loader2.setVisibility(View.INVISIBLE);

        Firebase.setAndroidContext(this);
        peers = new Firebase("https://wifiap-1361.firebaseio.com/peers");

//        for (int i = 0; i < 6; ++i){
//            con_num.add("8876721208");
//        }
//        peers.setValue(con_num);

        peers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    GenericTypeIndicator<java.util.List<String>> t = new GenericTypeIndicator<java.util.List<String>>() {};
                    con_num = dataSnapshot.getValue(t);

                    loader.setVisibility(View.INVISIBLE);
                    loader2.setVisibility(View.VISIBLE);

                    for (int i = 0; i < con_num.size(); ++i){
                        con.add(new Contacts("NAME ", con_num.get(i)));
                    }
                    adapter.notifyDataSetChanged();

                    Log.d(":: ", con_num.toString());

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });




    }
}
