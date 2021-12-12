package blog.droidsonroids.pl.blogpost;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;


public class MainActivity extends AppCompatActivity {

    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;
    private boolean mIsBackVisible1 = false;
    private boolean mIsBackVisible2 = false;
    private boolean mIsBackVisible3 = false;
    private boolean mIsBackVisible4 = false;
    private View mCardFrontLayout;
    private View mCardBackLayout;
    //findViews();

    Spinner spinner;
    Locale myLocale;
    String currentLanguage = "en", currentLang;

   long size=0;
    TextView crunchifyEditText;
    TextView topText;
    HashMap<String,Integer> map = new HashMap<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentLanguage = getIntent().getStringExtra(currentLang);

        spinner =  findViewById(R.id.spinner);
List<String> list =new ArrayList<>();
       list.add("Select language");
        list.add("English");
        list.add("Hindi");
     ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        setLocale("en");
                        break;

                    case 2:
                        setLocale("hi");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
       // recreate();
        findViews();
        loadAnimations();
        changeCameraDistance();
        final DatabaseReference  mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        Button saveButton = findViewById(R.id.b1);
      final   CheckBox one1 = findViewById(R.id.checkBox);
        final CheckBox one2 = findViewById(R.id.checkBox2);
        final CheckBox one3 = findViewById(R.id.checkBox3);
        final CheckBox one22 = findViewById(R.id.checkBox22);
        final CheckBox one4 = findViewById(R.id.checkBox4);
        final CheckBox one5 = findViewById(R.id.checkBox5);
        final CheckBox one6 = findViewById(R.id.checkBox6);
        final CheckBox one7 = findViewById(R.id.checkBox7);
        final CheckBox one8 = findViewById(R.id.checkBox8);
        final CheckBox one9 = findViewById(R.id.checkBox9);
        final CheckBox one10 = findViewById(R.id.checkBox10);
        final CheckBox one11 = findViewById(R.id.checkBox11);
        final CheckBox one12 = findViewById(R.id.checkBox12);
        final CheckBox one13 = findViewById(R.id.checkBox13);
        final CheckBox one14 = findViewById(R.id.checkBox14);

        System.out.println("....................................");
        FirebaseApp.initializeApp(this);
      crunchifyEditText =  findViewById(R.id.txt1);
        topText=findViewById(R.id.txt2);
        System.out.println("Before adding listener, count="+size);
        mDatabase.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    size= (int)dataSnapshot.getChildrenCount();
                    System.out.println("In onDataChange, count="+size);
                   // String b=
                    crunchifyEditText.setText(null);
                    crunchifyEditText.setText("Last five recent orders are as follows ");
                    for(int i=0;i<5;i++)
                    crunchifyEditText.setText( crunchifyEditText.getText() + dataSnapshot.child((Long.toString(size-i))).getValue().toString()+", ");
                    String[] words=new String[1000];

                    for(int j=0;j<size;j++)
                    {if(dataSnapshot.child(Long.toString(j)).getValue()!=null)
                           words[j]=dataSnapshot.child(Long.toString(j)).getValue().toString();
                    }

                    List<String> allwords = new ArrayList<>();

                    for(int i = 0; i<words.length;i++)
                    {
                        Integer temp = map.get(words[i]);
                        if(temp!=null)
                        {
                            map.put(words[i],temp+1);
                        }
                        else{
                            map.put(words[i],1);
                            allwords.add(words[i]);
                        }

                    }
                    // sort the array based on map

                    Collections.sort(allwords,new Comparator<String>(){
                        public int compare(String o1, String o2) {
                            if(map.get(o2).compareTo(map.get(o1))!=0)
                                return map.get(o2).compareTo(map.get(o1));
                            else
                                return o1.compareTo(o2);

                        }
                    });
                    if(allwords.size()!=0) {
                        topText.setText(null);
                        topText.setText("Top 5 ordered dishes are the following ");
                    }
                    if(allwords.size()!=0)
                    for (int p = 1; p < 5; p++)
                    topText.setText(topText.getText()+allwords.get(p)+" ,");
                 }
            }

            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
        System.out.println("After adding listener, count="+size);
        System.out.println("After adding listener");

        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                System.out.println(size+"     ;;;;;;;;;;;;;;;;;;;;;; ");
                if(one1.isChecked()) {
                    System.out.println("cofffeee");
size+=1;
                  mDatabase.child(String.valueOf(size)).setValue("Coffee");
                }
                else {

                }
                if(one2.isChecked()) {
                    System.out.println("pizzaaa");
                    size+=1;
                    mDatabase.child(String.valueOf(size)).setValue("Pizza");
                }
                else {

                }
                if(one22.isChecked()) {
                    System.out.println("aloooooo");

                    size+=1;

                    mDatabase.child(String.valueOf(size)).setValue("Dum Aloo");
                }
                else {

                }
                if(one3.isChecked()) {
                    size+=1;
                    mDatabase.child(String.valueOf(size)).setValue("Noodles");
                }
                if(one4.isChecked()) {
                    size+=1;
                    mDatabase.child(String.valueOf(size)).setValue("Pasta");
                }
                if(one5.isChecked()) {
                    size+=1;
                    mDatabase.child(String.valueOf(size)).setValue("momo");

                }
                if(one6.isChecked()) {
                    size+=1;
                    mDatabase.child(String.valueOf(size)).setValue("Chicken leg");
                }
                if(one7.isChecked()) {
                    size+=1;
                    mDatabase.child(String.valueOf(size)).setValue("Prawns");
                }
                if(one8.isChecked()) {
                    size+=1;
                    mDatabase.child(String.valueOf(size)).setValue("Salmon");
                }
                if(one9.isChecked()) {
                    size+=1;
                    mDatabase.child(String.valueOf(size)).setValue("Dosa");
                }
                if(one10.isChecked()) {
                    size+=1;
                    mDatabase.child(String.valueOf(size)).setValue("Idli");
                }
                if(one11.isChecked()) {

                    size+=1;
                    mDatabase.child(String.valueOf(size)).setValue("Vada Pav");
                }
                if(one12.isChecked()) {
                    size+=1;
                    mDatabase.child(String.valueOf(size)).setValue("Spaghetti");
                }
                if(one13.isChecked()) {
                    size+=1;
                    mDatabase.child(String.valueOf(size)).setValue("Lasagna");
                }
                if(one14.isChecked()) {
                    size+=1;
                    mDatabase.child(String.valueOf(size)).setValue("Ravioli");
                }
                Toast.makeText(getApplicationContext(),"Order successful", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), MainActivityTwo.class);
                i.putExtra("Value1", "onebanc");
                i.putExtra("Value2", "neobank");
                // Set the request code to any code you like, you can identify the
                // callback via this code
                startActivity(i);

            }

        });

    }
    public void setLocale(String localeName) {
        if (!localeName.equals(currentLanguage)) {
            myLocale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);
        } else {
            Toast.makeText(MainActivity.this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }


    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mCardFrontLayout.setCameraDistance(scale);
        mCardBackLayout.setCameraDistance(scale);
    }

    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.in_animation);
    }
    public void findViews1(View view1) {
        mCardBackLayout = findViewById(R.id.card_back1);
        mCardFrontLayout = findViewById(R.id.card_front1);
    }

    private void findViews() {
        mCardBackLayout = findViewById(R.id.card_back);
        mCardFrontLayout = findViewById(R.id.card_front);

    }

    public void flipCard(View view) {
        mCardBackLayout = findViewById(R.id.card_back);
        mCardFrontLayout = findViewById(R.id.card_front);

        if (!mIsBackVisible) {
            mSetRightOut.setTarget(mCardFrontLayout);
            mSetLeftIn.setTarget(mCardBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = true;
        } else {
            mSetRightOut.setTarget(mCardBackLayout);
            mSetLeftIn.setTarget(mCardFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
        }
    }
    public void flipCard1(View view) {

        mCardBackLayout = findViewById(R.id.card_back1);
        mCardFrontLayout = findViewById(R.id.card_front1);
        if (!mIsBackVisible1) {
            mSetRightOut.setTarget(mCardFrontLayout);
            mSetLeftIn.setTarget(mCardBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible1 = true;
        } else {
            mSetRightOut.setTarget(mCardBackLayout);
            mSetLeftIn.setTarget(mCardFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible1 = false;
        }
    }
    public void flipCard2(View view) {

        mCardBackLayout = findViewById(R.id.card_back2);
        mCardFrontLayout = findViewById(R.id.card_front2);
        if (!mIsBackVisible2) {
            mSetRightOut.setTarget(mCardFrontLayout);
            mSetLeftIn.setTarget(mCardBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible2 = true;
        } else {
            mSetRightOut.setTarget(mCardBackLayout);
            mSetLeftIn
                    .setTarget(mCardFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible2 = false;
        }
    }
    public void flipCard3(View view) {

        mCardBackLayout = findViewById(R.id.card_back3);
        mCardFrontLayout = findViewById(R.id.card_front3);
        if (!mIsBackVisible3) {
            mSetRightOut.setTarget(mCardFrontLayout);
            mSetLeftIn.setTarget(mCardBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible3 = true;
        } else {
            mSetRightOut.setTarget(mCardBackLayout);
            mSetLeftIn.setTarget(mCardFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible3 = false;
        }
    }
    public void flipCard4(View view) {

        mCardBackLayout = findViewById(R.id.card_back4);
        mCardFrontLayout = findViewById(R.id.card_front4);
        if (!mIsBackVisible4) {
            mSetRightOut.setTarget(mCardFrontLayout);
            mSetLeftIn.setTarget(mCardBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible4 = true;
        } else {
            mSetRightOut.setTarget(mCardBackLayout);
            mSetLeftIn.setTarget(mCardFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible4 = false;
        }
    }

}
