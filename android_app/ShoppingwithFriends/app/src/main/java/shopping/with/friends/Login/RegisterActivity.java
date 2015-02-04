package shopping.with.friends.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import shopping.with.friends.MainActivity;
import shopping.with.friends.R;

/**
 * Created by Ryan Brooks on 1/24/15.
 */
public class RegisterActivity extends Activity {

    /**
     * Declarations
     */
    private EditText emailET, usernameET, passwordET, passwordConfirmET;
    private Button signUpButton;
    private boolean registrationSuccessful;

    /**
     * See loginActivity for explanation of onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // TODO: Remove the actionbar
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        emailET = (EditText) findViewById(R.id.ar_email_et);
        usernameET = (EditText) findViewById(R.id.ar_username_et);
        passwordET = (EditText) findViewById(R.id.ar_password_et);
        passwordConfirmET = (EditText) findViewById(R.id.ar_password_reenter_et);
        signUpButton = (Button) findViewById(R.id.ar_register_button);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailET.getText().toString().trim().equals("")) {
                    Toast.makeText(RegisterActivity.this, "You didn't enter an email!", Toast.LENGTH_SHORT).show();
                } else if (usernameET.getText().toString().trim().equals("")) {
                    Toast.makeText(RegisterActivity.this, "You didn't enter a username!", Toast.LENGTH_SHORT).show();
                } else if (passwordET.getText().toString().trim().equals("")) {
                    Toast.makeText(RegisterActivity.this, "You didn't enter a password!", Toast.LENGTH_SHORT).show();
                } else if (!passwordET.getText().toString().trim().equals(passwordConfirmET.getText().toString().trim())) {
                    Toast.makeText(RegisterActivity.this, "Your passwords don't match!", Toast.LENGTH_SHORT).show();
                } else {
                    new HttpAsyncTask().execute("http://128.61.66.79:5000/api/users");
                }
            }
        });
    }

    /**
     * See loginActivity for explanation of POST
     */
    public static String POST(String url, String email, String username, String password){
        InputStream inputStream;
        String result = "";
        try {
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            // build jsonObject
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
            nameValuePairs.add(new BasicNameValuePair("email", email));
            nameValuePairs.add(new BasicNameValuePair("username", username));
            nameValuePairs.add(new BasicNameValuePair("password", password));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null) {
                result = convertInputStreamToString(inputStream);
            } else {
                result = "Did not work!";
            }

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

    /**
     * See loginActivity for explanation of ASync and HTTP POST
     */
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {

            String emailToPost = emailET.getText().toString().trim();
            String userNameToPost = usernameET.getText().toString().trim();

            //TODO: Encrypt Password
            String passwordToPost = passwordET.getText().toString().trim();

            Log.d("username", userNameToPost);
            Log.d("password", passwordToPost);


            return POST(urls[0], emailToPost, userNameToPost, passwordToPost);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            try {
                readJSONResponse(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * See loginActivity
     */
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line;
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }

    /**
     * See loginActivity for explanation of read response method
     */
    private void readJSONResponse(String result) throws JSONException {
        Log.d("JSON", result);
        JSONObject mainObject = new JSONObject(result);
        registrationSuccessful = mainObject.getBoolean("success");

        if (registrationSuccessful) {
            Intent mainActivity = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(mainActivity);
            Toast.makeText(getBaseContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Sorry there was an error, please try again!", Toast.LENGTH_SHORT).show();
            signUpButton.setEnabled(true);
            emailET.setEnabled(true);
            usernameET.setEnabled(true);
            passwordET.setEnabled(true);
            passwordConfirmET.setEnabled(true);
        }
    }
}
