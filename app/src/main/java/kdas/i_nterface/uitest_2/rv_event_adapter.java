package kdas.i_nterface.uitest_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Interface on 18/08/16.
 */
public class rv_event_adapter extends RecyclerView.Adapter<rv_event_adapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public com.github.pavlospt.CircleView pre1, pre2, pre3;
    public com.mikhaellopez.circularimageview.CircularImageView pro;

    private Context context;

    public ViewHolder(Context context, View itemView) {
        super(itemView);

        this.pro = (com.mikhaellopez.circularimageview.CircularImageView)itemView.findViewById(R.id.pro);
        this.pre1 = (com.github.pavlospt.CircleView)itemView.findViewById(R.id.cir2);
        this.pre2 = (com.github.pavlospt.CircleView)itemView.findViewById(R.id.cir3);
        this.pre3 = (com.github.pavlospt.CircleView)itemView.findViewById(R.id.cir4);
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

        Boolean events_count[] = events_data.events;
        for (int i = 0; i < 3; ++i){
            if (events_count[i] == true){
                switch (i){
                    case 0 : {
                        holder.pre1.setVisibility(View.VISIBLE);
                        break;
                    }

                    case 1 : {
                        holder.pre2.setVisibility(View.VISIBLE);
                        break;
                    }

                    case 2 : {
                        holder.pre3.setVisibility(View.VISIBLE);
                        break;
                    }

                    default: //
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mevents.size();
    }
}
