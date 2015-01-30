package shopping.with.friends;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Ryan Brooks on 1/24/15.
 */
public class MainActivity extends Activity {

    /**
     * See loginActivity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
