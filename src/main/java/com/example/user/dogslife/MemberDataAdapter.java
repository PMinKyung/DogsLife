package com.example.user.dogslife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MemberDataAdapter extends BaseAdapter {

    ArrayList<DiaryList> datas;
    LayoutInflater inflater;

    // constructior. 여기서 datas에 우리가 DB에 저장된 정보를 arraylist로 받은걸 여기다 넣은것이고,
    // infalter는 이 객체를 리스트뷰에 띄우는데, 사용자 마음대로 띄우기 위한? 수단? 잘모름ㅋㅋ
    public MemberDataAdapter(LayoutInflater inflater, ArrayList<DiaryList> datas) {
        // TODO Auto-generated constructor stub

        this.datas= datas;
        this.inflater= inflater;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return datas.size(); //datas의 개수를 리턴
    }

    public void clear() {
        datas.clear() ;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return datas.get(position);//datas의 특정 인덱스 위치 객체 리턴.
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        if (convertView == null) { // 리스트뷰에 띄워지고 있는게 null값이라는 말은 현재 리스트뷰에 띄워진 정보가 없다는 뜻.
            convertView = inflater.inflate(R.layout.activity_listview, null);
        } // 사실 이 함수를 호출할 때 쓰여진 두번째 파라미터가 정확히 무엇을 의미하는지 모르겠음.
        // 이 조건문의 뜻은 리스트뷰에 뭔가 정보가 있다면 다시 띄우지 않아도 된다는 의미.

        TextView text_name = (TextView) convertView.findViewById(R.id.text_name);
        TextView text_nation = (TextView) convertView.findViewById(R.id.text_nation);
        TextView text_time = (TextView) convertView.findViewById(R.id.text_time) ;
        final ImageView img_flag = (ImageView) convertView.findViewById(R.id.img_flag);


        text_name.setText(datas.get(position).GetDiary());
        text_nation.setText(datas.get(position).GetDate());
        text_time.setText(datas.get(position).GetTime()) ;

        // 객체가 가지고 있는 정보에 대해서 카테고리에 따른 그림을 띄우는것.
        if (Integer.parseInt(datas.get(position).GetCategory()) == 1)
            img_flag.setImageResource(R.drawable.haircut);
        else if (Integer.parseInt(datas.get(position).GetCategory()) == 2)
            img_flag.setImageResource(R.drawable.vacine);
        else if (Integer.parseInt(datas.get(position).GetCategory()) == 3)
            img_flag.setImageResource(R.drawable.hospital);
        else
            img_flag.setImageResource(R.drawable.etc);


        return convertView; // 리스트뷰를 보여줄 객체를 리턴하는것.
    }

}
