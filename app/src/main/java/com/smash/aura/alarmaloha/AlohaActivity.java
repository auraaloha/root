package com.smash.aura.alarmaloha;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class AlohaActivity extends Activity implements View.OnClickListener {

    private int mWidth;
    private int mHeight;

    //GUI components
    TextView titleTextView;
    Button menuButton;
    Button plusButton;
    PopupMenu popupMenu;

    Boolean isMenuOpened = false;

    private float actionBarHeightRate = 0.125f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActionbar();
    }

    public void initActionbar() {

        //get Device display
        Display display = getWindowManager().getDefaultDisplay();
        mWidth = display.getWidth();
        mHeight = display.getHeight();

        /** Attach Actionbar **/
        //get RootView of current Activity
        ViewGroup rootLayout = (ViewGroup) getWindow().getDecorView().getRootView();
        //Make Relativelayout for actionbar
        RelativeLayout actionbarLayout = new RelativeLayout(this);
        //actionbar Default attribute
        actionbarLayout.setBackgroundColor(Color.BLACK);
        //actionbar Default Layout Params
        RelativeLayout.LayoutParams layoutParams;
        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(int)(mHeight*actionBarHeightRate));
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        rootLayout.addView(actionbarLayout,layoutParams);

        /** add title TextView **/
        titleTextView = new TextView(this);
        titleTextView.setTextColor(Color.WHITE);
        titleTextView.setTextSize(mHeight * actionBarHeightRate * 0.25f);
        titleTextView.setGravity(Gravity.CENTER_VERTICAL);
        titleTextView.setText("ALOHA");
        //set layout parameters for title textview
        RelativeLayout.LayoutParams titleTextviewParams;
        titleTextviewParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        titleTextviewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        titleTextviewParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        titleTextviewParams.setMargins(0, (int) (mHeight * actionBarHeightRate * 0.25f), 0, 0); //center alignment
        actionbarLayout.addView(titleTextView, titleTextviewParams);

        /** add menu button **/
        menuButton = new Button(this);
        menuButton.setTextSize(mHeight * actionBarHeightRate * 0.25f);
        menuButton.setText("=");
        menuButton.setOnClickListener(this);
        //set layout parameters for menu button
        RelativeLayout.LayoutParams menuButtonParams;
        menuButtonParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        menuButtonParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        actionbarLayout.addView(menuButton,menuButtonParams);

        /** add plus button **/
        plusButton = new Button(this);

        /** add popup menu **/
        popupMenu = new PopupMenu(this,menuButton);
        popupMenu.inflate(R.menu.menu_main);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Log.v("view ID", v.getId() + "");
        if(isMenuOpened){
            popupMenu.dismiss();
            isMenuOpened = false;
        }else{
            popupMenu.show();
            isMenuOpened = true;
        }

    }

    @Override
    public void openOptionsMenu() {
        super.openOptionsMenu();
    }
}
