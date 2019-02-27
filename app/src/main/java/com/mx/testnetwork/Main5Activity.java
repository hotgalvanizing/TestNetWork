package com.mx.testnetwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mx.library.demo3.BindId;
import com.mx.library.demo3.BindIdApi;
import com.mx.library.demo3.BindOnClick;
import com.mx.library.demo3.OnClick;

@BindId(R.layout.activity_main5)
public class Main5Activity extends AppCompatActivity {

    @BindId(R.id.text_view)
    private TextView tv;

    @BindId(R.id.button)
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main5);
        BindIdApi.bindId(this);
        BindOnClick.bindOnCLick(this);
        tv.setText("haha");
    }

    @OnClick({R.id.button, R.id.text_view})
    private void click(View view) {
        switch (view.getId()) {
            case R.id.button:
                tv.setText("按了button");
                break;

            case R.id.text_view:
                btn.setText("按了TextView");
                break;

            default:
                break;
        }
    }


}
