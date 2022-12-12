package com.example.eventcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity {
    ArrayList<EventItems> eventItems;
    EventCalendar eventCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        checkOrder();
        eventCalendar = new EventCalendar(eventItems,this,findViewById(R.id.recyclerview));
        setLogicActivity1();
        setLogicActivity2();

    }

    /**
     * init the data -
     * new EventItems(int month, int day, right drawable (event image),
     * left drawable, String time, String event name,
     * String URL facebook button, String URL instagram button)
     */

    private void initData() {
        eventItems = new ArrayList<>();

        eventItems.add(new EventItems(10, 1, R.drawable.julfest, R.drawable.p2, "22:00", "Dömd", "https://www.facebook.com/datateknologsektionen","https://www.facebook.com/datateknologsektionen"));
        eventItems.add(new EventItems(3, 20, R.drawable.domd, R.drawable.p2, "22:00", "jul", "https://www.facebook.com/datateknologsektionen", "https://www.facebook.com/datateknologsektionen"));
        eventItems.add(new EventItems(1, 31, R.drawable.julfest, R.drawable.p2, "22:00", "kanelbulle", "https://www.facebook.com/datateknologsektionen", "https://www.google.com/"));
        eventItems.add(new EventItems(3, 15, R.drawable.domd, R.drawable.p2, "22:00", "party", "https://www.facebook.com/datateknologsektionen", "https://www.facebook.com/datateknologsektionen"));
        eventItems.add(new EventItems(2, 8, R.drawable.julfest, R.drawable.p2, "22:00", "midsommar", "https://www.facebook.com/datateknologsektionen", "https://www.facebook.com/datateknologsektionen"));
        eventItems.add(new EventItems(12, 20, R.drawable.domd, R.drawable.p2, "22:00", "Gille", "https://www.facebook.com/datateknologsektionen", "https://www.facebook.com/datateknologsektionen"));


    }

    /**
     * sort the order -  if the events are initiated in the wrong order, sort the dates.
     * see MyComparator class
     */
    public void checkOrder() {
        Collections.sort(eventItems,new MyComparator());
    }
    /**
     * set/add logic to the Events i main window
     * See Guide.txt
     */
    public void setLogicActivity1() {
        eventCalendar.initLogicHashSet("TEXT_WHITE");//-> component1
        eventCalendar.initLogicHashSet("ADD_ARROW_BTN");//-> component1
        eventCalendar.initLogicHashSet("ADD_LOGIC_EMOJI");//-> component2
        eventCalendar.initLogicHashSet("EMOJI_POSITION(5)");//-> component2
        eventCalendar.initLogicHashSet("EMOJI_POSITION(0)");//-> component2
        eventCalendar.initLogicHashSet("EMOJI_POSITION(3)");//-> component2
        eventCalendar.setActivity1Name("KANelBULLe");

        eventCalendar.initLogicHashSet("TEXT_BLACK");//-> component1
        eventCalendar.initLogicHashSet("ADD_ARROW_BTN");//-> component1
        eventCalendar.initLogicHashSet("LOCATION_ARROW_BTN");//-> component1
        eventCalendar.setCoordinates(-200,50);//-> component1
        eventCalendar.initLogicHashSet("ADD_LISTENER");//-> component1
        eventCalendar.setActivity1Name("party");

        eventCalendar.initLogicHashSet("ADD_ARROW_BTN");//-> component1
        eventCalendar.initLogicHashSet("ADD_LOGIC_EMOJI");//-> component2
        eventCalendar.initLogicHashSet("EMOJI_POSITION(1)");//-> component2
        eventCalendar.initLogicHashSet("EMOJI_POSITION(2)");//-> component2
        eventCalendar.initLogicHashSet("EMOJI_POSITION(3)");//-> component2
        eventCalendar.setActivity1Name("dömd");

        eventCalendar.initLogicHashSet("ADD_RANDOM_EMOJI");//-> component1
        eventCalendar.setActivity1Name("jul");
    }

    /**
     * If we want to change to segments instead of activity.
     * this method is for future development not to be use in this program
     *
     */
    public void setLogicActivity2() {


    }


}

/**
 * Class to check the order of Events. Used together with Collections sort (JAVA).
 *  if the events are initiated in the wrong order, sort the dates.
 */
class MyComparator implements Comparator<EventItems> {

    /**
     *
     * @param s1 - first object to compare
     * @param s2 - second object to compare with
     * @return
     */
    public int compare(EventItems s1, EventItems s2)
    {

        if (s1.getMonth() == s2.getMonth()) {
            if (s1.getDay() <= s2.getDay()) {
                return -1;
            } else {
                return 0;
            }
        } else if (s1.getMonth() > s2.getMonth()) {
            return 1;
        } else {
            return -1;
        }

    }
}