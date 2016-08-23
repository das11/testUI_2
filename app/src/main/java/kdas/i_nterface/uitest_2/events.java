package kdas.i_nterface.uitest_2;

import java.util.*;

/**
 * Created by Interface on 18/08/16.
 */
public class events {

    //Boolean[] events = new Boolean[3];
    java.util.List<String> m_events = new ArrayList<>();

    events(java.util.List<String> events){
        for(int i = 0; i < events.size(); ++i){
            m_events.add(i, events.get(i));
        }

    }
}
