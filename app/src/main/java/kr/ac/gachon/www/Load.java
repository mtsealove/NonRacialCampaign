package kr.ac.gachon.www;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Load extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        Toast.makeText(getApplicationContext(), "우리 모두 인종차별을 지양하는 문화인이 됩시다^^", Toast.LENGTH_SHORT).show();
        Handler delay=new Handler();
        delay.postDelayed(new Runnable() {
            @Override
            public void run() {
                move_main();
            }
        }, 700);
    }

    //메인 페이지 이동 메서드
    private void move_main() {
        Intent intent=new Intent(Load.this, Main.class);
        startActivity(intent);
        finish();
    }
}
