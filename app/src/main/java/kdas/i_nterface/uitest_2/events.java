package kdas.i_nterface.uitest_2;

/**
 * Created by Interface on 18/08/16.
 */
public class events {

    Boolean[] events = new Boolean[3];

    events(Boolean[] events){
        for(int i = 0; i < 3; ++i){
            this.events[i] = events[i];
        }

    }
}
