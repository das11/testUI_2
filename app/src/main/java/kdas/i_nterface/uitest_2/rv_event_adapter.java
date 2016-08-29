package kdas.i_nterface.uitest_2;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

/**
 * Created by Interface on 18/08/16.
 */
public class rv_event_adapter extends RecyclerView.Adapter<rv_event_adapter.ViewHolder>{

    int high_pos;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public com.github.pavlospt.CircleView pre1, pre2, pre3;
    public com.mikhaellopez.circularimageview.CircularImageView pro;
    public TextView day_month, day_week, runtv, lifetv, notetv;
    public View line;


    private Context context;

    public ViewHolder(Context context, View itemView) {
        super(itemView);

        this.pro = (com.mikhaellopez.circularimageview.CircularImageView)itemView.findViewById(R.id.pro);
        this.pre1 = (com.github.pavlospt.CircleView)itemView.findViewById(R.id.cir2);
        this.pre2 = (com.github.pavlospt.CircleView)itemView.findViewById(R.id.cir3);
        this.pre3 = (com.github.pavlospt.CircleView)itemView.findViewById(R.id.cir4);
        this.day_month = (TextView)itemView.findViewById(R.id.day_month);
        this.day_week = (TextView)itemView .findViewById(R.id.day_week);
        this.line = (View)itemView.findViewById(R.id.line);
        this.runtv = (TextView)itemView.findViewById(R.id.run_tv);
        this.lifetv = (TextView)itemView.findViewById(R.id.life_tv);
        this.notetv = (TextView)itemView.findViewById(R.id.note_tv);
        this.context = context;

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int pos = getLayoutPosition();
        Toast.makeText(context, pos + "", Toast.LENGTH_SHORT).show();

//        if (pos == 0) {
//            Intent i = new Intent(context, Main2Activity.class);
//            context.startActivity(i);
//        }

    }
}

    private java.util.List<events> mevents;
    private Context mcontext;

    public rv_event_adapter(Context context, java.util.List<events> m_events){
        mevents = m_events;
        mcontext = context;
    }

    private Context getContext(){
        return mcontext;
    }


    @Override
    public rv_event_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = getContext();

        LayoutInflater inflator = LayoutInflater.from(context);
        View row = inflator.inflate(R.layout.event_row, parent, false);
        ViewHolder viewholder = new ViewHolder(getContext(), row);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(rv_event_adapter.ViewHolder holder, int position) {

        events events_data = mevents.get(position);
        //Calendar date = getdate_from_pos(position);
        Date date = getdate_from_pos(position);

        String day, month, day_week;

        day = (String) android.text.format.DateFormat.format("dd", date);
        month = (String) android.text.format.DateFormat.format("MMM", date);
        day_week = (String) android.text.format.DateFormat.format("EEEE", date);

        holder.day_month.setText(day);
        holder.day_week.setText(day_week);


        high_pos = return_present_pos();
        for (int i = 0; i < 1; ++i){

            if (position == high_pos){

                holder.day_month.setTextColor(ContextCompat.getColor(mcontext, R.color.some_accent));
                holder.day_week.setTextColor(ContextCompat.getColor(mcontext, R.color.some_accent));
                holder.line.setBackgroundColor(ContextCompat.getColor(mcontext, R.color.some_accent));
                Log.d("COLOR", position + "");
            }

        }

        java.util.List<String> events_count = events_data.m_events;
//        for (int i = 0; i < events_count.size(); ++i){
//            if (events_count.get(i) == "true"){
//                switch (i){
//                    case 0 : {
//                        holder.pre1.setVisibility(View.VISIBLE);
//                        break;
//                    }
//
//                    case 1 : {
//                        holder.pre2.setVisibility(View.VISIBLE);
//                        break;
//                    }
//
//                    case 2 : {
//                        holder.pre3.setVisibility(View.VISIBLE);
//                        break;
//                    }
//
//                    default: //
//                }
//            }
//            else {
//                switch (i){
//                    case 0 : {
//                        holder.pre1.setVisibility(View.INVISIBLE);
//                        break;
//                    }
//
//                    case 1 : {
//                        holder.pre2.setVisibility(View.INVISIBLE);
//                        break;
//                    }
//
//                    case 2 : {
//                        holder.pre3.setVisibility(View.INVISIBLE);
//                        break;
//                    }
//
//                    default: //
//                }
//            }
//        }
    }

    @Override
    public int getItemCount() {
        return mevents.size();
    }

    public int return_present_pos(){
        Date current = new Date();
        Log.d("present pos date", current + "");
        int pos = getposition_from_time(current);
        Log.d("present pos", pos + "");

        return pos - 1; // POS FETCHING TRUE_VALUE + 1, SO NEEDED A QUICK FIX
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

    public int getposition_from_time(Date date){

        int pos = 0;
        for (long i = 1451586600000L; i < date.getTime(); i += 86400000){
            ++pos;
        }
        return pos;
    }
}
