package com.solomevich.android.notes;

/**
 * Created by 15 on 28.01.2018.
 */

import java.io.Serializable;

@SuppressWarnings("serial")
public class MyData implements Serializable{

    private long id;
    private long date;
    private String title;
    private int icon;

    public MyData (long id, long date, String title, int icon) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.icon = icon;
    }

    public long getID () {return id;}
    public long getDate () {return date;}
    public String getTitle () {return title;}
    public int getIcon () {return icon;}
}
