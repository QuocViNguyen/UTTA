package com.example.a3350.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a3350.R;
import com.example.a3350.application.Main;
import com.example.a3350.logic.Filter;
import com.example.a3350.logic.LoginChecker;
import com.example.a3350.objects.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    //Constants
    EditText usernameEditText;
    EditText passwordEditText;
    String FailMessage;
    static User currentUser;
    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        FailMessage = "Login failed!!";

        copyDatabaseToDevice();
        Filter.setPostID();

        //temporary
        usernameEditText.setText("mbluth@myumanitoba.ca");
        passwordEditText.setText("123456");

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.loginButton:
                if(usernameEditText.getText()!=null && passwordEditText.getText()!=null) {
                    String email = usernameEditText.getText().toString();
                    String password = passwordEditText.getText().toString();
                    if (LoginChecker.check(email, password)) {
                        Toast.makeText(LoginActivity.this, "Welcome!! Login success", Toast.LENGTH_SHORT).show();
                        currentUser = Filter.getUserByEmail(email);
                        Intent intent = new Intent(getApplicationContext(), HomepageActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, FailMessage, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.registerButton:
                Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                intent.putExtra(getString(R.string.edit_account), false);
                startActivity(intent);
        }
    }
    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch (final IOException ioe) {
            Toast.makeText(this, "Unable to access data.", Toast.LENGTH_SHORT).show();
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }
                out.close();
                in.close();
            }
        }
    }
}
