package com.example.eventcalendar;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

/**
 * MyRecyclerAdapter is an adapter class that uses a RecyclerView.
 *  It takes in a list of EventItems, which represent events and its that will be displayed in the RecyclerView.
 *  The MyRecyclerAdapter class inflates a layout for each item in the RecyclerView, and populates
 *  the layout with data from the corresponding EventItems object.
 *  The MyRecyclerAdapter class also handles user interactions with the RecyclerView items.
 * The Viewholder class will cache the data for each row item to be accessed (each view will be accessed)
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> implements MainActivityInterface{
    private MyOnClickInterface onClickInterface;
    private MainActivityInterface mainActivity;
    private ArrayList<EventItems> eventItems;
    private Context context;
    private int arrowButtonX;
    private int arrowButtonY;
    private HashMap<String,HashSet<String>> addLogicMap;
    private HashSet<String> logicHashSet;


    public MyRecyclerAdapter(MyOnClickInterface onClickInterface, ArrayList<EventItems> eventItems, Context context) {
        this.onClickInterface = onClickInterface;
        this.eventItems = eventItems;
        this.context = context;
        logicHashSet = new HashSet<>();
        addLogicMap = new HashMap<>();


    }


    //Inflates the layout from XML resource and return the Custom holder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       //inflate the custom layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_items,parent,false);
        //return a new holder instance
        return new MyViewHolder(view, onClickInterface, mainActivity);
    }

    // Involves populating data into the item through holder
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //get the data based on the position
        int background_img = eventItems.get(position).getParent_img();
        int event_img = eventItems.get(position).getChildren_img();
        int month = eventItems.get(position).getMonth();
        String months = eventItems.get(position).getMonthS();
        int day = eventItems.get(position).getDay();
        String event_name = eventItems.get(position).getEventName();
        //init and cache the data
        holder.setData(month,months,day,event_img,background_img, event_name,
                context,addLogicMap,this.arrowButtonX,this.arrowButtonY);
        //sets the logic
        //holder.addLogic(event_name,this.arrowButtonX,this.arrowButtonY, addLogicMap);
        holder.addLogic(event_name);

    }

    /**
     *
     * @return total number of items held in the Dataset
     */
    public int getItemCount() {
        return eventItems.size();
    }

    @Override
    public void setCoordinates(int arrowButtonX, int arrowButtonY) {
        this.arrowButtonY = arrowButtonY;
        this.arrowButtonX = arrowButtonX;
    }

    @Override
    public void initLogicHashSet(String addLogic) {
        logicHashSet.add(addLogic);
    }

    @Override
    public void setActivity1Name(String activity1Name) {
        addLogicMap.put(activity1Name.toUpperCase(Locale.ROOT), logicHashSet);
        logicHashSet = new HashSet<>();
    }

    @Override
    public void setActivity2Name(String activity2Name) {

    }


    /**
     * Provide a direct reference to each of the views within a data item
     * Used to cache the views within the item layout for fast access
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView backgroundView;
        private ImageView eventView;
        private TextView textView;
        private Context context;
        private String month;
       // private ImageButton next_btn;
        private ImageView next_btn;
        private HashMap<String, HashSet<String>> addLogicMap;
        private int arrowButtonX;
        private int arrowButtonY;
        private MainActivityInterface mainActivity;

        // A constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public MyViewHolder(@NonNull View itemView, MyOnClickInterface onClickInterface,MainActivityInterface mainActivity) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            this.mainActivity = mainActivity;
            backgroundView = itemView.findViewById(R.id.parent_image);
            eventView = itemView.findViewById(R.id.children_image);
            textView = itemView.findViewById(R.id.date_txtBlue);
            next_btn = itemView.findViewById(R.id.next_btn);
            //when clicked on an item -> pass the position to interface
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View itemView) {
                    if(onClickInterface != null){
                        int position = getAbsoluteAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            onClickInterface.onItemClick(position);

                            Toast.makeText(context,"clicked on position " + position,Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }

        /**
         *
         * @param month - the month: integer value
         * @param months - the month: String name
         * @param day - the day: integer value
         * @param event_img - drawable
         * @param background_img - drawable
         * @param event_name - the name of the event: String name
         * @param context - the item we are working with at the moment
         */

        public void setData(int month, String months, int day, int event_img, int background_img,
                            String event_name,Context context,HashMap<String, HashSet<String>> addLogicMap,int arrowButtonX,int arrowButtonY) {
            this.context = context;
            this.month = months;
            this.addLogicMap = addLogicMap;
            this.arrowButtonX = arrowButtonX;
            this.arrowButtonY = arrowButtonY;
            backgroundView.setImageResource(background_img);
            eventView.setImageResource(event_img);
            textView.setText(event_name + "\n" + months + " " +day);

        }

        /**
         * Apply logic specified in the addLogicMap to the RecyclerView item with the given event name.
         * The logic includes changing the text color, showing/hiding the arrow button, adding a random
         * emoji, setting the location of the arrow button, and adding a listener to the arrow button.
         * @param event_name - the name of the event: String name
         */
        public void addLogic(String event_name) {
            next_btn.setVisibility(View.INVISIBLE);

            if (addLogicMap.containsKey(event_name)) {
                // Get the logic for the event with the given name
                HashSet<String> eventLogic = addLogicMap.get(event_name);

                if (eventLogic.contains("TEXT_WHITE")) {
                    textView.setTextColor(Color.WHITE);
                }
                if (eventLogic.contains("TEXT_BLACK")) {
                    textView.setTextColor(Color.BLACK);
                }
                if (eventLogic.contains("ADD_ARROW_BTN")) {
                    next_btn.setVisibility(View.VISIBLE);
                }
                if (eventLogic.contains("ADD_RANDOM_EMOJI")) {
                    new MyPopUp(context, itemView.findViewById(R.id.parent_image));
                }
                if (eventLogic.contains("ADD_LOGIC_EMOJI")){
                    new MyPopUp(context, itemView.findViewById(R.id.next_btn),eventLogic);

                }
                if (eventLogic.contains("LOCATION_ARROW_BTN")) {
                    next_btn.setX(arrowButtonX);
                    next_btn.setY(arrowButtonY);
                }
                // sets an OnClickListener
                // on next_btn that rotates the button by -180 degrees each time it is clicked.
                if (eventLogic.contains("ADD_LISTENER")) {
                    next_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            next_btn.setRotation(next_btn.getRotation() - 180);
                        }
                    });

                }
            }
        }

    }
}
