package com.example.projectone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String ERROR_MESSAGE = "Invalid E-Mail/Password. Please try again.";
    private Button login;
    private TextView errorMessage;
    private EditText emailInput, passwordInput;
    private String emailInfo, passwordInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.button);
        errorMessage = (TextView)findViewById(R.id.errorView); // used to connect to log-in error message
        emailInput = (EditText)findViewById(R.id.editText);
        passwordInput = (EditText)findViewById(R.id.editText2);
        login.setOnClickListener(sendUser);
    }

    private final View.OnClickListener sendUser =
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent mainMenu = new Intent(getApplicationContext(), MainMenu.class);
                    emailInfo = emailInput.getText().toString();
                    passwordInfo = passwordInput.getText().toString();
                    boolean hasAt = false; // false by default. used to check if the entered email has an @ symbol, to further error check
                    boolean noSpaces = true; // true by default. used to check if there are spaces in the email, which would make it invalid
                    for (int i = 0; i < emailInfo.length(); i++){
                        if (emailInfo.charAt(i) == '@')
                            hasAt = true; // if @ detected in email, set flag true
                        if (emailInfo.charAt(i) == ' ')
                            noSpaces = false; // if space detected in email, set flag false.
                    }
                    if(emailInfo.length() == 0 || passwordInfo.length() == 0) { // Error checking! See if email/password fields are empty.
                        errorMessage.setText(ERROR_MESSAGE);
                    }
                    else if (noSpaces == false || hasAt == false){
                        errorMessage.setText(ERROR_MESSAGE);
                    }
                    else {
                        startActivity(mainMenu);
                    }
                }
            };

}
