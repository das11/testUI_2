package kdas.i_nterface.uitest_2;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    boolean read_done = false;

    java.util.List<Contacts> contact = new ArrayList<Contacts>();
    ArrayList<String> contact_name = new ArrayList<>();
    ArrayList<String> contact_num = new ArrayList<>();

    Contact_adapter adapter;

    com.bhargavms.dotloader.DotLoader loader, loader2;

    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().hide();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        loader = (com.bhargavms.dotloader.DotLoader)findViewById(R.id.text_dot_loader_toolbar);
        loader2 = (com.bhargavms.dotloader.DotLoader)findViewById(R.id.text_dot_loader);
        loader.setVisibility(View.INVISIBLE);

        RecyclerView rv = (RecyclerView)findViewById(R.id.view_rv);
//        for (int i = 0; i < 10; ++i){
//            contact.add(new Contacts("Leo", "3213212332"));
//        }

        new read_async().execute("");

        adapter = new Contact_adapter(this, contact);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //adapter.notifyDataSetChanged();

    }

    private class read_async extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected String doInBackground(String... strings) {

            while (!read_done){
                readContacts();
            }

            return null;
        }

       // @Override
        protected void onPostExecute(String temp){

            loader2.setVisibility(View.GONE);
            loader.setVisibility(View.VISIBLE);

            Log.d("Hash :", contact_name.size() + "");
            for (int i = 0; i < contact_num.size(); ++i){
                if (!contact_name.get(i).equals("") || !contact_num.get(i).equals("")){
                    contact.add(new Contacts(contact_name.get(i), contact_num.get(i)));
                }
            }
            adapter.notifyDataSetChanged();
        }
    }


    public void readContacts(){
        String phnum = null;

        Uri content_uri = ContactsContract.Contacts.CONTENT_URI;
        String id = ContactsContract.Contacts._ID;
        String display_name = ContactsContract.Contacts.DISPLAY_NAME;
        String hasphnum = ContactsContract.Contacts.HAS_PHONE_NUMBER;
        Uri phone_content_uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String phone_contact_id = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String number = ContactsContract.CommonDataKinds.Phone.NUMBER;

        StringBuffer output, output2;
        String temp_n;
        String temp_num;
        ContentResolver contentResolver = getContentResolver();
        cursor = contentResolver.query(content_uri, null, null, null, null);

        if(cursor.getCount() > 0){
            int counter = 0;
            while(cursor.moveToNext()){
                output = new StringBuffer();
                output2 = new StringBuffer();
                String contact_id = cursor.getString(cursor.getColumnIndex(id));
                String name = cursor.getString(cursor.getColumnIndex(display_name));

                //Boolean hasnum = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(hasphnum)));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(hasphnum)));
                if(hasPhoneNumber > 0){
                    output.append(name);

                    temp_n = name;
                    Log.d("CON ",output.toString());

                    Cursor phcursor = contentResolver.query(phone_content_uri, null, phone_contact_id + " = ?", new String[] {contact_id}, null);
                    while (phcursor.moveToNext()){
                        phnum = phcursor.getString(phcursor.getColumnIndex(number));
                        output2.append("\nNumber " + phnum);
                        temp_num = phnum;
                        //contact_num.add(temp_num.toString());
                    }
                    phcursor.close();
                }


                contact_name.add(output.toString());
                contact_num.add(output2.toString());

                //contact.add(new Contacts(output.toString(), output2.toString()));
                //Log.d("on", "on");
            }
            Log.d("Map :: ", contact_name.toString());
            Log.d("Map :: ", contact_num.toString());
            Log.d("size :: ", contact_name.size() + "");
            //inf();
            //writef(contact);

            List.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Hello, from the thread ++ " + contact_name.size(), Toast.LENGTH_LONG).show();

                }
            });
            read_done = true;
            //thread_kill = true;


        }else {
        }
    }

    public void inf(){
        for (int i = 0; i < 10; ++i){
            contact.add(new Contacts(contact_name.get(i), contact_num.get(i)));
        }
    }







}
