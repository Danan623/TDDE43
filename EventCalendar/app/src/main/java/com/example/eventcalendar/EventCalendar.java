package com.example.eventcalendar;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class EventCalendar implements MyOnClickInterface {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<EventItems> eventItems;
    private Context context;

    private MainActivityInterface recyclerAdapter;

    private HashMap<String,HashSet<String>> addLogicMap;
    private HashSet<String> logicHashSet;
    private String activity2Name;

    public EventCalendar(ArrayList<EventItems> eventItems, Context context, RecyclerView recyclerView) {

        this.eventItems = eventItems;
        this.context = context;
        this.recyclerView = recyclerView;
        addLogicMap = new HashMap<>();
        logicHashSet = new HashSet<>();
        init();

    }
    //init layout
    private void init() {

        linearLayoutManager = new LinearLayoutManager(context);// helps positioning the items
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new MyRecyclerAdapter(this, eventItems,context);
        recyclerView.setAdapter((RecyclerView.Adapter) recyclerAdapter);

    }
    //set coordinates to arrow button
    protected void setCoordinates(int arrowButtonX, int arrowButtonY){
        recyclerAdapter.setCoordinates(arrowButtonX,arrowButtonY);
    }


    /**
     *
     * @param addLogic - String name of the logic to add or change (main window)
     */
    protected void initLogicHashSet(String addLogic){
        recyclerAdapter.initLogicHashSet(addLogic);
        //logicHashSet.add(addLogic);

    }

    /**
     *
     * @param activity1Name - String name of the event to add or change logic (main window)
     */
    public void setActivity1Name(String activity1Name) {// pass to rec.adapter -> to viewholder
        recyclerAdapter.setActivity1Name(activity1Name);
    }

    /**
     *
     * not used in the program.
     */
    public void setActivity2Name(String activity2Name){
        this.activity2Name = activity2Name;
    }

    /*
     * When onClick listener notifies a click (RecyclerView -> MyViewholder class)
     * the MyOnclickInterface will be en woken and the position will be sent to connect with
     * the correct content
     *
     */
    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(context, ShowContent.class);
        intent.putExtra("EVENTNAME",eventItems.get(position).getEventName());
        intent.putExtra("DATE",eventItems.get(position).getMonthS() + " " + eventItems.get(position).getDay());
        intent.putExtra("TIME",eventItems.get(position).getTime());
        intent.putExtra("EVENT_IMG", eventItems.get(position).getChildren_img());
        intent.putExtra("FB_URL",eventItems.get(position).getFbUrl());
        intent.putExtra("INST_URL",eventItems.get(position).getInstUrl());
        intent.putExtra("PASS_NAME", activity2Name);
        context.startActivity(intent);
    }


}
