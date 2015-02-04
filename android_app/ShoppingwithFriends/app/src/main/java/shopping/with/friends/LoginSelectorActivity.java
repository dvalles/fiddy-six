package shopping.with.friends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import shopping.with.friends.Login.LoginActivity;
import shopping.with.friends.Login.RegisterActivity;

/**
 * Created by ryanbrooks on 2/2/15.
 */
public class LoginSelectorActivity extends Activity {

    private Button loginButton, registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        loginButton = (Button) findViewById(R.id.as_login_button);
        registerButton = (Button) findViewById(R.id.as_register_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginSelectorActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginSelectorActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

    }
}
