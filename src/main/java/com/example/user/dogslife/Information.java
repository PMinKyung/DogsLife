package com.example.user.dogslife;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Information extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        // fab.setOnClickListener(new View.OnClickListener() {
        // public void onClick(View view) {
        //   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //         .setAction("Action", null).show();
        // }
        // });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_information, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_information, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
            Fragment fragment = null;
            Bundle args = null;
            switch (position) {
                case 0:
                    fragment = new SectionsFragment1();
                    args = new Bundle();
                    break;
                case 1:
                    fragment = new SectionsFragment2();
                    args = new Bundle();
                    break;
                case 2:
                    fragment = new SectionsFragment3();
                    args = new Bundle();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Vaccination";
                case 1:
                    return "Food";
                case 2:
                    return "Language";
            }
            return null;
        }
    }

    public static class SectionsFragment1 extends Fragment {


        public SectionsFragment1() {

        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_page1, container, false);

            ArrayList<String> vacine = new ArrayList<String>();
            vacine.add("종합7종백신");
            vacine.add("코로나장염");
            vacine.add("켄넬코프");
            vacine.add("구충제");
            vacine.add("심장사상충예방");

            ArrayAdapter<String> Adapter;
            Adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, vacine);

            final ListView listView = (ListView) rootView.findViewById(R.id.vacinelist);
            listView.setAdapter(Adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setIcon(R.drawable.vacine1);
                    if (listView.getItemIdAtPosition(position) == 0) {
                        builder.setTitle("종합7종백신");
                        builder.setMessage("아데노바이러스성 기관지염, 파보바이러스성 장염, 전염성 간염, 개홍역, 파라인플루엔자성 기관지염 및 렙토스피라증 등의 질병을 예방해주는 가장 중요한 예방접종이다.\n" +
                                "6~8주 부터 시작해서 2~3주 간격으로 5회이상 접종한다.");
                    } else if (listView.getItemIdAtPosition(position) == 1) {
                        builder.setTitle("코로나장염");
                        builder.setMessage("코로나바이러스성 장염은 피가 섞인 설사를 하고, 구토를 하며, 열이 나고, 식욕이 없어지는 증세가 특징으로, 개들에게는 매우 치명적인 전염병이다.\n" +
                                "6~8주부터 시작해서 2~3주 간격으로 3회 접종한다.");
                    } else if (listView.getItemIdAtPosition(position) == 2) {
                        builder.setTitle("켄넬코프");
                        builder.setMessage("주로 강아지들이 대량으로 있는 곳에서 공기를 통해 쉽게 감염되며 심한 마른기침을 일으키며 폐렴으로 진행되기도 한다.\n" +
                                "6~8주부터 시작해서 2~3주 간격으로 3회 접종한다.");
                    } else if (listView.getItemIdAtPosition(position) == 3) {
                        builder.setTitle("구충제");
                        builder.setMessage("매 2~3개월마다 구충제를 먹인다.");
                    } else if (listView.getItemIdAtPosition(position) == 4) {
                        builder.setTitle("심장사상충예방");
                        builder.setMessage("모기가활동하는 봄부터 가을까지 매달 1회 투여한다.");
                    }

                    builder.setPositiveButton("OK!", null);
                    builder.show();

                }
            });

            return rootView;
        }

    }


    public static class SectionsFragment2 extends Fragment {

        public SectionsFragment2() {

        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_page2, container, false);

            ArrayList<String> food = new ArrayList<String>();
            food.add("북어");
            food.add("계란노른자");
            food.add("닭가슴살");
            food.add("당근");
            food.add("고구마와 단호박");
            food.add("딸기");
            food.add("바나나");

            ArrayAdapter<String> Adapter;
            Adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, food);

            final ListView listView = (ListView) rootView.findViewById(R.id.foodlist);
            listView.setAdapter(Adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    if (listView.getItemIdAtPosition(position) == 0) {
                        builder.setIcon(R.drawable.food1);
                        builder.setTitle("북어");
                        builder.setMessage("높은영양가가있고, 피를맑게해준다. 임신, 출산견에게 좋다.");
                    } else if (listView.getItemIdAtPosition(position) == 1) {
                        builder.setTitle("계란노른자");
                        builder.setIcon(R.drawable.food2);
                        builder.setMessage("모질개선과 감기와 구토를 완화시킨다.");
                    } else if (listView.getItemIdAtPosition(position) == 2) {
                        builder.setTitle("닭가슴살");
                        builder.setIcon(R.drawable.food3);
                        builder.setMessage("비만견에게 좋다");
                    } else if (listView.getItemIdAtPosition(position) == 3) {
                        builder.setTitle("당근");
                        builder.setIcon(R.drawable.food4);
                        builder.setMessage("모질개선과 색소유지에 좋다.");
                    } else if (listView.getItemIdAtPosition(position) == 4) {
                        builder.setTitle("고구마와 단호박");
                        builder.setIcon(R.drawable.food5);
                        builder.setMessage("눈과 장건강, 피부에 좋다.");
                    } else if (listView.getItemIdAtPosition(position) == 5) {
                        builder.setTitle("딸기");
                        builder.setIcon(R.drawable.food7);
                        builder.setMessage("치아미백에 좋다");
                    } else if (listView.getItemIdAtPosition(position) == 6) {
                        builder.setTitle("바나나");
                        builder.setIcon(R.drawable.food8);
                        builder.setMessage("소화기능을 개선한다.");
                    }

                    builder.setPositiveButton("OK!", null);
                    builder.show();

                }
            });

            return rootView;
        }
    }

    public static class SectionsFragment3 extends Fragment {

        public SectionsFragment3() {

        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_page3, container, false);

            ArrayList<String> language = new ArrayList<String>();
            language.add("뚫어지게 쳐다본다");
            language.add("숨을 헐떡인다");
            language.add("하품을 한다");
            language.add("귀를 쫑긋 세우고 앞으로 향한다");
            language.add("꼬리를 아래로 늘어뜨린다");
            language.add("배를 보고 눕는다");

            ArrayAdapter<String> Adapter;
            Adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1,language);

            final ListView listView = (ListView) rootView.findViewById(R.id.languagelist);
            listView.setAdapter(Adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setIcon(R.drawable.language1);
                    if (listView.getItemIdAtPosition(position) == 0) {
                        builder.setTitle("뚫어지게 쳐다본다");
                        builder.setMessage("당신을 위협하거나 도전의식등의 자신감을 표출하는것이다. 먹을것을 들고있다면 음식을 바라는 걸지도 모른다.\n" +
                                "눈을 바라보고 있다면 낯선 사람에겐 강한 경계이지만 친한 사람에겐 정말 좋다는 의미이다.\n");
                    } else if (listView.getItemIdAtPosition(position) == 1) {
                        builder.setTitle("숨을 헐떡인다");
                        builder.setMessage("일반적으로 더울 때 숨을 헐떡이지만, 덥지않을때 헐떡인다면 불안 혹은 흥분상태를 말한다.\n" +
                                "얼굴을 핥는 행동은 밥달라는 행동에서 버릇된 행동이다. 애정을 더 달라는 강아지만의 애교이다.");
                    } else if (listView.getItemIdAtPosition(position) == 2) {
                        builder.setTitle("하품을한다");
                        builder.setMessage("피곤할 때 일수 있지만 대체적으로 불안하거 스트레스가 쌓인상태를 표시한다.\n");
                    } else if (listView.getItemIdAtPosition(position) == 3) {
                        builder.setTitle("귀를 쫑긋 세우고 앞으로 향한다 ");
                        builder.setMessage("당신에게 관심이 있거나 경계하는중이다.");
                    } else if (listView.getItemIdAtPosition(position) == 4) {
                        builder.setTitle("꼬리를 아래로 늘어뜨린다");
                        builder.setMessage("불안하거나 두려운상태를 의미하고 주인에게는 복종을 의미한다.\n");
                    } else if (listView.getItemIdAtPosition(position) == 5) {
                        builder.setTitle("배를 보고 눕는다");
                        builder.setMessage("복종하겠으니 같이 놀아달라는 뜻으로, 휴식을 취하는 행동이다.\n" +
                                "침대나 소파에 몸을 비비며 뒹군다면 주인의 향을 맡는중이다.");
                    }

                    builder.setPositiveButton("OK!", null);
                    builder.show();

                }
            });

            return rootView;
        }

    }
}

