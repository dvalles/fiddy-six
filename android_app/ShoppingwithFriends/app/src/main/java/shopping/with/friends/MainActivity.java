package shopping.with.friends;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import shopping.with.friends.Drawer.DrawerMenuAdapter;
import shopping.with.friends.Drawer.DrawerMenuItem;
import shopping.with.friends.Fragments.Followers;
import shopping.with.friends.Fragments.Following;
import shopping.with.friends.Fragments.MainFeed;
import shopping.with.friends.Fragments.Profile;
import shopping.with.friends.Fragments.Settings;
import shopping.with.friends.Fragments.WishList;

/**
 * Created by Ryan Brooks on 1/24/15.
 */
public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView drawerMenuListview;
    private DrawerMenuAdapter drawerMenuAdapter;

    /**
     * See loginActivity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.ma_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.ma_drawer_layout);
        drawerMenuListview = (ListView) findViewById(R.id.ma_drawer_menu_listview);

        List<DrawerMenuItem> menuItems = generateDrawerMenuItems();
        drawerMenuAdapter = new DrawerMenuAdapter(getApplicationContext(), menuItems);
        drawerMenuListview.setAdapter(drawerMenuAdapter);

        drawerMenuListview.setOnItemClickListener(this);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);

        if (savedInstanceState == null) {
            setFragment(0, Profile.class);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                setFragment(0, MainFeed.class);
                break;
            case 1:
                setFragment(1, Profile.class);
                break;
            case 2:
                setFragment(2, WishList.class);
                break;
            case 3:
                setFragment(3, Following.class);
                break;
            case 4:
                setFragment(4, Followers.class);
                break;
            case 5:
                setFragment(5, Settings.class);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(drawerMenuListview)) {
            drawerLayout.closeDrawer(drawerMenuListview);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public void setFragment(int position, Class<? extends Fragment> fragmentClass) {
        try {
            Fragment fragment = fragmentClass.newInstance();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.ma_drawer_frame_container, fragment, fragmentClass.getSimpleName());
            fragmentTransaction.commit();

            drawerMenuListview.setItemChecked(position, true);
            drawerLayout.closeDrawer(drawerMenuListview);
            drawerMenuListview.invalidateViews();
        }
        catch (Exception ex){
            Log.e("setFragment", ex.getMessage());
        }
    }

    private List<DrawerMenuItem> generateDrawerMenuItems() {
        String[] itemsText = getResources().getStringArray(R.array.ma_slide_drawer_items);
        List<DrawerMenuItem> result = new ArrayList<DrawerMenuItem>();
        for (int i = 0; i < itemsText.length; i++) {
            DrawerMenuItem item = new DrawerMenuItem();
            item.setItemText(itemsText[i]);
            result.add(item);
        }
        return result;
    }

    /**
     * Creates a menu in the top right corner of the ActionBar
     * We do not need to worry about anything in this method other
     * than the inflate(R.menu.main_menu). This is an xml file under the
     * res folder "menu" where we can customize the menu and what is in it
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * This is the handler for the menu. Gets the items and their id's in the
     * menu xml file and handles clicks to those items.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Logic behind determining which item is clicked:
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.ma_action_logout:
                Intent i = new Intent(this, LoginSelectorActivity.class);
                startActivity(i);
                finish();
        }

        return super.onOptionsItemSelected(item);
    }



}
