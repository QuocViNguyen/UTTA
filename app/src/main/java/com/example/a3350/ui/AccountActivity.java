package com.example.a3350.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3350.R;
import com.example.a3350.logic.AccessInstitutions;
import com.example.a3350.logic.AccessUsers;
import com.example.a3350.logic.Filter;
import com.example.a3350.logic.LoginChecker;
import com.example.a3350.objects.Institution;
import com.example.a3350.objects.User;
import static com.example.a3350.ui.LoginActivity.currentUser;

import java.util.List;

public class AccountActivity extends Activity implements View.OnClickListener
{
    private AccessUsers accessUsers;
    private TextView usernameTextView, domainTextView, emailTextView, passwordTextView, confirmPassTextView, whichInstitution;
    private Institution selectedInstitution;
    boolean editAccount;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        Intent intent = getIntent();
        editAccount = intent.getBooleanExtra(getString(R.string.edit_account), false);

        AccessInstitutions accessInstitutions = new AccessInstitutions();
        accessUsers = new AccessUsers();
        List<Institution> institutions = accessInstitutions.getInstitutions();

        usernameTextView = findViewById(R.id.usernameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        passwordTextView = findViewById((R.id.passwordTextView));
        domainTextView = findViewById(R.id.domainTextView);
        whichInstitution = findViewById(R.id.whichInstitution);
        confirmPassTextView = findViewById(R.id.confirmPassTextView);
        Spinner institutionSpinner = findViewById(R.id.institutionSpinner);
        Button cancelButton = findViewById(R.id.cancelButton);
        Button accountButton = findViewById(R.id.accountButton);
        cancelButton.setOnClickListener(this);
        accountButton.setOnClickListener(this);


        final ArrayAdapter<Institution> institutionArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, institutions);
        institutionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        institutionSpinner.setAdapter(institutionArrayAdapter);
        institutionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedInstitution = (Institution)adapterView.getSelectedItem();
                String domain = selectedInstitution.getDomain();

                domainTextView.setText(domain);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        if(editAccount)
        {
            accountButton.setText(R.string.edit_account);
            usernameTextView.setText(currentUser.getName());
            int indexOfAtSign = currentUser.getEmail().indexOf("@");
            emailTextView.setText(currentUser.getEmail().substring(0,indexOfAtSign));
            String text = "Your institution";
            whichInstitution.setText(text);
            int indexOfInstitution = Filter.getIndexOfInstitution(institutions, currentUser.getInstitution());
            institutionSpinner.setSelection(indexOfInstitution);
            emailTextView.setFocusable(false);
            institutionSpinner.setEnabled(false);
            emailTextView.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.cancelButton:
                finish();
                break;
            case R.id.accountButton:
                String username = usernameTextView.getText().toString();
                String password = passwordTextView.getText().toString();
                String confirmPass = confirmPassTextView.getText().toString();
                String email = emailTextView.getText().toString() + domainTextView.getText().toString();
                String toast;
                if(editAccount)
                {
                    toast = LoginChecker.editAccountCheck(username, currentUser.getEmail(), email, password, confirmPass);
                    if(toast.equals(getString(R.string.allGood)))
                    {
                        accessUsers.updateUser(currentUser.getEmail(), username, email, password, selectedInstitution);
                        toast = "Account information updated successfully. Please log out and log back in to see changes!!";
                        finish();
                    }
                }
                else
                {
                    toast = LoginChecker.registerCheck(username, email, password, confirmPass);
                    if(toast.equals(getString(R.string.allGood)))
                    {
                        User user = new User(username, selectedInstitution, email, password);
                        accessUsers.addUser(user);
                        Intent intent = new Intent(getApplicationContext(), HomepageActivity.class);
                        currentUser = user;
                        startActivity(intent);
                    }
                }
                Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
                break;
            case R.id.emailTextView:
                Toast.makeText(this, "You can not change your email id.", Toast.LENGTH_SHORT).show();
        }
    }
}
