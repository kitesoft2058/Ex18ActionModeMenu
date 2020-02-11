package com.kitesoft.ex18actionmodemenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 액션뷰   : 액션바의 메뉴 모양을 커스텀
        // 2. 액션모드 메뉴 : 기존 액션바를 덮으면서 새로운 액션바메뉴가 보여지는 모드

    }

    //1. 액션뷰가 추가된 옵션메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        menuItem= menu.findItem(R.id.menu_action); // app:showAsAction="ifRoom|collapseActionView"속성값이 지정된 옵션메뉴

        //커스텀 액션메뉴 제어
        View v= menuItem.getActionView(); //커스텀 액션뷰객체 참조
        actionViewMenuEditText= v.findViewById(R.id.actionview_et); // 위 액션뷰안에 있는 EditText객체 참조


        //EditText의 글씨를 입력하고 소프트키패드의 완료버튼을 눌렀을 때 리스너 처리  //////////////////
        actionViewMenuEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //원하는 동작 코드 작성 .....

                    return true;
                }
                return false;
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////


        return super.onCreateOptionsMenu(menu);
    }

    MenuItem menuItem;
    EditText actionViewMenuEditText;

    //버튼 클릭시에 액션뷰에 입력한 글씨 토스트로 보여주기
    public void clickBtn2(View view) {
        menuItem.collapseActionView();//열려있는 액션바를 다시 아이콘모양으로 닫기

        Toast.makeText(this, actionViewMenuEditText.getText().toString(), Toast.LENGTH_SHORT).show();
    }



    //기존 액션바를 덮어버리는 액션모드...
    public void clickBtn(View view) {
        //액션모드 시작하기 : 액션바위치에 메뉴가 보여짐
        startActionMode(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                Toast.makeText(MainActivity.this, "Create ActionMode", Toast.LENGTH_SHORT).show();

                //res/menu폴더안에 있는 actionmode.xml의 메뉴들로 액션모드 메뉴 생성
                mode.getMenuInflater().inflate(R.menu.actionmode, menu);

                //액션모드에 추가로 줄수 있는 설정들
                mode.setTitle("actionmode");
                mode.setSubtitle("this is actionmode");

                //mode.setCustomView(new Button(MainActivity.this));
                //색상변경은 스타일작업을 통해 해야함... Color값을 넣을 때 반드시 colors.xml 리소스를 이용해야 함.

                return true; //true가 아니면 액션모드가 발동되지 않음..
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                Toast.makeText(MainActivity.this, "Prepare ActionMode", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu_aa:
                        Toast.makeText(MainActivity.this, "aaa", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_bb:
                        Toast.makeText(MainActivity.this, "bbb", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_cc:
                        Toast.makeText(MainActivity.this, "ccc", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                Toast.makeText(MainActivity.this, "Destroy ActionMode", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
