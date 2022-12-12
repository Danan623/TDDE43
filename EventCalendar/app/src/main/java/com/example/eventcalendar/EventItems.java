package com.example.eventcalendar;

import java.util.Locale;

public class EventItems {
    private int parent_img;
    private int children_img;
    private int month;
    private int day;
    private String time;
    private final String eventName;
    private String fbUrl;
    private String instUrl;
    private String[] monthS = {"January", "February", "March", "April", "May", "June"
            , "July", "August", "September", "October", "November", "December"};

    public EventItems(int month, int day, int children_img, int parent_img, String time, String eventName, String fbUrl, String instUrl) {
        this.parent_img = parent_img;
        this.month = month;
        this.day = day;
        this.children_img = children_img;
        this.time = time;
        this.eventName = eventName.toUpperCase(Locale.ROOT);
        this.fbUrl = fbUrl;
        this.instUrl = instUrl;
    }

    /**
     *
     * @return left-background img
     */
    public int getParent_img() {
        return parent_img;
    }

    /**
     *
     * @return right-Event img
     */
    public int getChildren_img() {return children_img; }

    /**
     *
     * @return integer - month
     */
    public int getMonth() {
        return month;
    }

    /**
     *
     * @return Integer - day
     */
    public int getDay(){return day;}

    /**
     *
     * @return String - month name
     */
    public String getMonthS(){return monthS[month-1];}

    /**
     *
     * @return String - name of event
     */
    public String getEventName() {
        return eventName;
    }

    /**
     *
     * @return String - time of event
     */
    public String getTime() {
        return time;
    }

    /**
     *
     * @return String URL to facebook page
     */
    public String getFbUrl() {
        return fbUrl;
    }

    /**
     *
     * @return String URL to instagram page
     */
    public String getInstUrl() {
        return instUrl;
    }

    /**
     *
     * @param parent_img - set left background image (EventCalendar)
     */
    public void setParent_img(int parent_img) {
        this.parent_img = parent_img;
    }

    /**
     *
     * @param children_img - set Event image
     */
    public void setChildren_img(int children_img) {
        this.children_img = children_img;
    }

    /**
     *
     * @param month - set month of the event
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     *
     * @param day - set day of the event
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     *
     * @param time - set time of the event
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     *
     * @param fbUrl - set facebook url
     */
    public void setFbUrl(String fbUrl) {
        this.fbUrl = fbUrl;
    }

    /**
     *
     * @param instUrl - set instagram url
     */
    public void setInstUrl(String instUrl) {
        this.instUrl = instUrl;
    }
}
