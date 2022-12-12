package com.example.eventcalendar;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class - creates a popup window with clickable emoji buttons
 */
public class MyPopUp {
    private ImageView like_img;
    private ImageView arrowButton;
    private Context context;
    private ArrayList<ImageButton> emoji_btns;
    private Dialog myDialog;
    private HashSet<String> emojiLogic;
    private Boolean activity1Toggle;
    private Boolean activity2Toggle;
   private ArrayList<Integer> emojiDrawables;
   private final String[] POSITIONS = {"0","1","2","3","4","5"};
   private int nullPosition;

    /**
     *
     * @param context - from activity1
     * @param arrowButton - the reference of XML from the main activity layout
     *
     */
    public MyPopUp(Context context, ImageView arrowButton, HashSet<String> emojiLogic) {
        this.arrowButton = arrowButton;
        this.emojiLogic = emojiLogic;
        this.context = context;
        activity1Toggle = true;// enable add logic
        activity2Toggle = false;
        nullPosition = -1;
        init();


    }

    /**
     *
     * @param context - from activity2
     * @param imageView - the reference of XML from the showcontent layout
     */
    public MyPopUp(Context context, ImageView imageView) {
        nullPosition = -1;
        this.context = context;
        this.like_img = imageView;
        activity2Toggle = true;
        activity1Toggle = false; // disable add logic
        init();

    }

    private void init() {
        emoji_btns = new ArrayList<>();
        emojiDrawables = new ArrayList<>();
        myDialog = new Dialog(context);
        if(activity2Toggle){
        like_img.setOnClickListener(v -> show_popup());}
        else{
            arrowButton.setOnClickListener(v -> show_popup());
        }
    }

    private void show_popup() {
        //sets the screen content from the layout resource.
        // Inflates the resource
        myDialog.setContentView(R.layout.emoji_popup);

        ImageButton like_btn = myDialog.findViewById(R.id.like_btn);
        ImageButton heart_btn = myDialog.findViewById(R.id.heart_btn);
        ImageButton amused_btn = myDialog.findViewById(R.id.amused_btn);
        ImageButton angry_btn = myDialog.findViewById(R.id.angry_btn);
        ImageButton sad_btn = myDialog.findViewById(R.id.sad_btn);
        ImageButton surprised_btn = myDialog.findViewById(R.id.surprised_btn);

        emoji_btns.add(like_btn);
        emoji_btns.add(heart_btn);
        emoji_btns.add(surprised_btn);
        emoji_btns.add(sad_btn);
        emoji_btns.add(amused_btn);
        emoji_btns.add(angry_btn);


        emojiDrawables.add(R.drawable.like_emoticon);
        emojiDrawables.add(R.drawable.heart_emoticon);
        emojiDrawables.add(R.drawable.suprised_emoticon);
        emojiDrawables.add(R.drawable.sad_emoticon);
        emojiDrawables.add(R.drawable.amused_emoticon);
        emojiDrawables.add(R.drawable.angry_emoticon);




        if (emojiLogic != null) {
            // Apply logic to the button at a specific position
            for(String index : POSITIONS){
                if (emojiLogic.contains("EMOJI_POSITION("+ index +")")) {
                    hideButton(emoji_btns.get(Integer.parseInt(index)),index);

                }
            }

        }
        // when respective button is clicked -> set like_button the a drawable
        if(activity2Toggle){
            for(int i = 0; i < emoji_btns.size();i++ ) {
                handleButtonClick(emoji_btns.get(i), like_img, emojiDrawables.get(i),i);
            }
        }else{
            for(int i = 0; i < emoji_btns.size();i++ ) {
                handleButtonClick(emoji_btns.get(i), arrowButton, emojiDrawables.get(i),i);
            }

        }

        // retrieves the current window and set the background to transparent. A nice touch!
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();// starts the dialog

    }

    private void hideButton(ImageButton imageButton, String index) {
        imageButton.setVisibility(View.INVISIBLE);
        nullPosition = Integer.parseInt(index);
    }

    public void handleButtonClick(ImageButton button, ImageView imageView, int drawable, int pos) {
        button.setOnClickListener(v -> imageView.setImageResource(drawable));

    }


}
