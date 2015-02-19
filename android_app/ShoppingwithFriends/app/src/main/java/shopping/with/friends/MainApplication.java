package shopping.with.friends;

import android.app.Application;
import android.content.Context;

import shopping.with.friends.Objects.Profile;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Ryan Brooks on 2/17/15.
 */
public class MainApplication extends Application {

    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/DroidSans-Bold.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/DroidSans.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/fontAwesome.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }
}
