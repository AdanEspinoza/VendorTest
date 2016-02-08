package com.mobile.apps.test.activitites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.apps.test.R;
/**
 * Created by snakelogan on 2/8/16.
 */
public class PalindromeActivity extends AppCompatActivity {

    private static final String TAG = PalindromeActivity.class.getSimpleName();
    EditText mEditText;
    Button mBtn;
    TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palindrome);
        mEditText = (EditText) findViewById(R.id.inputPalindrome);
        mBtn = (Button) findViewById(R.id.btnEval);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluatePalindrome();
            }
        });
        mResult = (TextView) findViewById(R.id.tv_result);
    }

    private void evaluatePalindrome(){
        String input = mEditText.getText().toString().trim();
        boolean isPalindrome =  false;
        if (input == null || input.length() < 3)
            return;

        for (int x=1; x<input.length()-1; x++) {
            for (int y=x-1,z=x+1; y>=0 && z<input.length(); y--,z++) {
                if (input.charAt(y) == input.charAt(z)) {
                    if (z-y+1 >= 3) {
                        Log.d(TAG, input.substring(y, z + 1) + " with index " + y + " and " + z);
                        mResult.setText("");
                        mResult.setText(input.substring(y, z+1));
                        isPalindrome=true;
                    }
                }
                else {
                    break;
                }
            }
        }
        if(!isPalindrome) mResult.setText("No palindrome found");
    }
}