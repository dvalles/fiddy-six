package shopping.with.friends.Adapters;

/**
 * Created by Ryan Brooks on 2/19/15.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import shopping.with.friends.Objects.Profile;
import shopping.with.friends.R;

public class UserListviewAdapter extends BaseAdapter {

    private ArrayList<Profile> users;
    private Context context;
    private long id1;
    private double percentageDouble, dayCtInt;

    public UserListviewAdapter(Context context, ArrayList<Profile> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Profile getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int pos, View child, ViewGroup parent) {
        final Holder mHolder;
        LayoutInflater layoutInflater;
        Profile profile = users.get(pos);
        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.listview_item_users, null);
            mHolder = new Holder();
            mHolder.name = (TextView) child.findViewById(R.id.lvu_name_textview);
            mHolder.username = (TextView) child.findViewById(R.id.lvu_username_textview);
            mHolder.followButton = (Button) child.findViewById(R.id.lvu_follow_button);
            child.setTag(mHolder);
        } else {
            mHolder = (Holder) child.getTag();
        }

        mHolder.name.setText(profile.getName());
        mHolder.username.setText(profile.getUsername());

        mHolder.followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHolder.followButton.setEnabled(false);

            }
        });

        return child;
    }

    public class Holder {
        TextView name;
        TextView username;
        Button followButton;
    }
}
