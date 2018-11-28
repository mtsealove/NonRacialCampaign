package kr.ac.gachon.www;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main extends AppCompatActivity {
    Button[] explain_btn=new Button[100];
    Button[] link_btn=new Button[100];
    LinearLayout explain_layout;
    LinearLayout link_layout;
    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.activity_main);

        //데이터베이스 연결
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        //버튼 레이아웃 설정
        LinearLayout.LayoutParams param=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.topMargin=50;

        //설명 추가
        explain_layout=(LinearLayout)findViewById(R.id.explains);

        for(int i=0; i<explain_btn.length; i++) {
            explain_btn[i]=new Button(Main.this);
            explain_btn[i].setLayoutParams(param);
            explain_btn[i].setBackgroundResource(R.drawable.button_background);
            explain_btn[i].setTextColor(Color.BLACK);
            explain_btn[i].setTextSize(16);
        }

        //교육자료 데이터 연결
        DatabaseReference reference=database.getReference("educate");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                   final String titles=snapshot.child("title").getValue().toString();
                   final String contents=snapshot.child("content").getValue().toString();
                   explain_btn[i].setText(titles);
                   explain_btn[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            explain(titles, contents);
                        }
                    });
                   explain_layout.addView(explain_btn[i]);
                   i++;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //링크 추가
        link_layout=(LinearLayout)findViewById(R.id.links);

        for(int i=0; i<link_btn.length; i++) {
            link_btn[i]=new Button(Main.this);
            link_btn[i].setLayoutParams(param);
            link_btn[i].setBackgroundResource(R.drawable.button_background);
            link_btn[i].setTextColor(Color.BLACK);
            link_btn[i].setTextSize(16);
        }

        //관련 링크 연결
        DatabaseReference linksRef=database.getReference("link");
        linksRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    final String title=snapshot.child("title").getValue().toString();
                    final String url=snapshot.child("url").getValue().toString();
                    final String des=snapshot.child("des").getValue().toString();
                    link_btn[i].setText(title);
                    link_btn[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            move_web(title, url);
                        }
                    });
                    link_btn[i].setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            Toast.makeText(Main.this, des, Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    });
                    link_layout.addView(link_btn[i]);
                    i++;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //교육자료 설명 다이얼로그 출력
    private void explain(String title_label, String content_label) {
        LayoutInflater inflater=getLayoutInflater();
        //다이얼로그 빌더 생성
        View layout=inflater.inflate(R.layout.dialog_explain, null);
        //내용 설정
        TextView title=(TextView)layout.findViewById(R.id.title);
        TextView descrption=(TextView)layout.findViewById(R.id.description);
        title.setText(title_label);
        descrption.setText(content_label);

        //다이얼로그 출력
        AlertDialog.Builder builder=new AlertDialog.Builder(Main.this);
        builder.setView(layout);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    //웹 페이지로 이동
    private void move_web(String title, String url){
        Intent intent=new Intent(Main.this, Web.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    //만든 사람들
    public void creater(View v) {
        AlertDialog.Builder builder=new AlertDialog.Builder(Main.this);
        LayoutInflater inflater=getLayoutInflater();
        View layout=inflater.inflate(R.layout.dialog_creater, null);
        builder.setView(layout);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    //메뉴 열기
    public void open_drawer(View v) {
        DrawerLayout layout=(DrawerLayout)findViewById(R.id.main_layout);
        layout.openDrawer(Gravity.LEFT);
    }
}
