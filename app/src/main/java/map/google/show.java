package map.google;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import map.google.newattendance.MainActivity;
import map.google.newattendance.R;
import map.google.newattendance.db;

public class show extends AppCompatActivity {
    Button btn1,btn2;
    db db1,db2;
   // t2,t5,t7
    TextView t1,t2,t3;
    AutoCompleteTextView at1;
    ArrayList<String> s1 = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Toast.makeText(this,"First Enter the subject name and click on SHOW button",Toast.LENGTH_LONG).show();
        btn1=(Button)findViewById(R.id.bn1);
        t1=(TextView) findViewById(R.id.t2);
        t2=(TextView) findViewById(R.id.textView5);
        t3=(TextView) findViewById(R.id.textView7);
        btn2=(Button) findViewById(R.id.bn2);
        db1=new db(this);
        Cursor c=db1.getInsertedData();
        int i=0;
        if(c.moveToFirst())
        {
            do
            {
                //stringArrayList.add(json_data.getString("xCoord")); //add to arraylist
                s1.add(c.getString(0));
            }while(c.moveToNext());
        }
        String [] stringArray = s1.toArray(new String[s1.size()]);
        at1=(AutoCompleteTextView) findViewById(R.id.a1);
        ArrayAdapter<String> ad=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,stringArray);

        //at1.setThreshold);
        at1.setThreshold(1);
        at1.setAdapter(ad);

        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                db2=new db(show.this);
                Cursor c1=db2.getInsertedData();
                //int i=0;
                String str;
                int fl=0;
                str=at1.getText().toString();
                if(c1.moveToFirst())
                {
                    do
                    {
                        //stringArrayList.add(json_data.getString("xCoord")); //add to arraylist
                        if(c1.getString(0).equals(str))
                        {
                            fl=1;
                            int pre,ab;
                            double per;
                            pre=c1.getInt(1);
                            ab=c1.getInt(2);
                            if(pre==0&&ab==0)
                                per=100;
                                        else
                            per=((double)pre/(double)(pre+ab))*100;
                            per=per*100;
                            per=(int)per;
                            per=per/100;
                            t1.setText(""+pre);
                            t2.setText(""+ab);
                            t3.setText(""+per);
                            if(per<75)
                                Toast.makeText(show.this,"likely to get detained!!!",Toast.LENGTH_LONG).show();
                            break;
                        }
                    }while(c1.moveToNext());

                }
                if(fl==0)
                    Toast.makeText(show.this,"Subject is not added",Toast.LENGTH_LONG).show();
            }
        });

        //init();
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
//                Intent i=new Intent(show.this,MainActivity.class);
                //i.putExtra("name",ed1.getText().toString());
                //i.putExtra("email",ed2.getText().toString());
//               startActivity(i);
               finish();
            }
        });

    }
    }
