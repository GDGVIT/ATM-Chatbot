package gdg.mehakmeet.atmchatbot;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.irozon.sneaker.Sneaker;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingMenuLayout;
import com.simmorsal.recolor_project.ReColor;

import java.util.ArrayList;

import gdg.mehakmeet.atmchatbot.adapter.Adapter;
import gdg.mehakmeet.atmchatbot.model.chat_show;

public class MainActivity extends AppCompatActivity{

    ImageView drawer_btn;

    Context context=this;
    Activity activity=this;
    FlowingMenuLayout fml;
    FrameLayout fl_fd;
    public static FlowingDrawer mDrawer;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   setTitleColor(R.color.blue_shade);
        drawer_btn=findViewById(R.id.drawer_btn);
        fl_fd=findViewById(R.id.fd_container);
        fml=findViewById(R.id.menulayout);

       /* final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        *//*navigationView.setNavigationItemSelectedListener(this);
*/
        mDrawer = findViewById(R.id.drawerlayout);

        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                    Log.i("MainActivity", "Drawer STATE_CLOSED");
                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                Log.i("MainActivity", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
                if(openRatio==1){
                    flag=1;
                }
                else
                    flag=0;
            }
        });
        drawer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDrawer.openMenu();

            }
        });
        floating_main fm1=new floating_main();
        home fm=new home();
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_container,fm).replace(R.id.fd_container,fm1).commit();


    }

    @Override
    public void onBackPressed() {
        if (flag==1) {
    mDrawer.closeMenu(true);
        } else {
            super.onBackPressed();
        }
    }

}
