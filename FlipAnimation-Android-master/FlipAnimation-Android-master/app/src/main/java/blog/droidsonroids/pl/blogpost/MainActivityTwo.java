package blog.droidsonroids.pl.blogpost;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.security.SecureRandom;


public class MainActivityTwo extends AppCompatActivity{
    TextView orderid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitytwo_main);
        Bundle extras = getIntent().getExtras();
        String value1 = extras.getString("Value1");
        String value2 = extras.getString("Value2");

    orderid=findViewById(R.id.bbb);
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);
        String formatted = String.format("%05d", num);
      //  System.out.println(formatted);
//int r1= random.nextInt(100000);
        orderid.setText(null);
        orderid.setText("Your order id is ");
        orderid.setText(orderid.getText()+formatted);
    }
    public void callFirstActivity(View view){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

}
