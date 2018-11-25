package kr.ac.gachon.www;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.activity_main);

        Button what_is_race=(Button)findViewById(R.id.what_is_race);
        Button foreign=(Button)findViewById(R.id.foriegn);
        Button what_is_racism=(Button)findViewById(R.id.what_is_racism);
        Button history=(Button)findViewById(R.id.history);
        Button why=(Button)findViewById(R.id.why);
        Button how=(Button)findViewById(R.id.how);

        what_is_race.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explain(1);
            }
        });
        foreign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explain(2);
            }
        });
        what_is_racism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explain(3);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explain(4);
            }
        });
        why.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explain(5);
            }
        });
        how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explain(6);
            }
        });

        Button racismout=(Button)findViewById(R.id.racismout);
        racismout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move_web("인종차별 철폐의 날 행사","http://asiansori.org/280");
            }
        });
        racismout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(Main.this, "인종차별 철폐의 날 행사에 관한 정보를 확인할 수 있습니다", Toast.LENGTH_LONG).show();
                return false;
            }
        });
        Button cyber_lecture=(Button)findViewById(R.id.cyber_lecture);
        cyber_lecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move_web("인권교육센터", "http://edu.humanrights.go.kr/academy/course/onDetail.do");
            }
        });
        cyber_lecture.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(Main.this, "인권 교육센터에서 사이버 교육을 신청할 수 있습니다", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    private void explain(int cases) {
        Resources res=getResources();
        String[] all=null;
        switch (cases) {
            case 1:
                all=res.getStringArray(R.array.what_is_race);
                break;
            case 2:
                all=res.getStringArray(R.array.foriegn);
                break;
            case 3:
                all=res.getStringArray(R.array.what_is_racism);
                break;
            case 4:
                all=res.getStringArray(R.array.history);
                break;
            case 5:
                all=res.getStringArray(R.array.why);
                break;
            case 6:
                all=res.getStringArray(R.array.how);
                break;
        }

        LayoutInflater inflater=getLayoutInflater();
        //다이얼로그 빌더 생성
        View layout=inflater.inflate(R.layout.dialog_explain, null);
        //내용 설정
        TextView title=(TextView)layout.findViewById(R.id.title);
        TextView descrption=(TextView)layout.findViewById(R.id.description);
        title.setText(all[0]);
        descrption.setText(all[1]);

        //다이얼로그 출력
        AlertDialog.Builder builder=new AlertDialog.Builder(Main.this);
        builder.setView(layout);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    private void move_web(String title, String url){
        Intent intent=new Intent(Main.this, Web.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
