package map.google.newattendance;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import map.google.show;
public class MainActivity extends AppCompatActivity {
    int v;
    db db1;
    Button btn1;
    Button btn2,btn3,btn4;
    ListView l1;
    final Context context = this;
    //AutoCompleteTextView at1;
    ArrayList<String> str_at = new ArrayList<String>();
    final CharSequence[] st = {"Present","Absent","Cancelled"};
    final CharSequence[] ch={"DeleteAll","Specify Delete"};//items in the alertdialog that displays checkboxes
    //String[] ListElements;
    //is stored in this ArrayList variable
    //ArrayList<String> arrayList = new ArrayList<String>();
    /** Declaring an ArrayAdapter to set items to ListView */
     ArrayAdapter<String> arrayAdapter;
    AlertDialog levelDialog,lev1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"Click on ADD to add subjects and SHOW to see the percentage of attendance",Toast.LENGTH_LONG).show();
        btn1=(Button)findViewById(R.id.bn1);
        btn2=(Button)findViewById(R.id.bn2);
        btn3=(Button)findViewById(R.id.bn3);
        btn4=(Button)findViewById(R.id.bn4);
        l1 = (ListView) findViewById(R.id.lv1);
        db1=new db(this);
        String[] sub = new String[] {"SUBJECTS"};
        // Create a List from String Array elements
        //db1.clearData();
        final List<String> subj=new ArrayList<String>(Arrays.asList(sub));
       Cursor c=db1.getInsertedData();
        if(c!=null) {
            if(c.moveToFirst()) {
                do {
                    String s1;
                    s1 = c.getString(0);
                    if (s1 == null) break;
                    subj.add(s1);
                    } while (c.moveToNext());
            }}
            // Create an ArrayAdapter from List
            arrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, subj);
            l1.setAdapter(arrayAdapter);
        //final List<String> ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));


        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                //(MainActivity.this, android.R.layout.simple_list_item_1, ListElementsArrayList);
        //listview.setAdapter(adapter);
        btn4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Select type of deletion...\n");
                builder.setSingleChoiceItems(ch, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item)
                        {
                            case 0:
                                //db1.up1(val,p);
                                android.support.v7.app.AlertDialog.Builder at=new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                                at.setTitle("Message" +
                                        "\n");
                                at.setMessage("Are you sure You want to delete all??");
                                at.setCancelable(false);
                                at.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        db1.clearData();
                                        subj.clear();
                                        String str="SUBJECTS:";
                                        subj.add(str);
// refresh the View
                                        arrayAdapter.notifyDataSetChanged();
                                    }
                                });
                                at.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                android.support.v7.app.AlertDialog ad=at.create();
                                ad.show();

                                break;
                            case 1:
                                // Your code when 2nd  option seletced

                                //Toast.makeText(MainActivity.this,"Second",Toast.LENGTH_LONG).show();
                                LayoutInflater li = LayoutInflater.from(context);
                                View promptsView = li.inflate(R.layout.dialog3, null);

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                        context);
                                // set prompts.xml to alertdialog builder
                                alertDialogBuilder.setView(promptsView);

                                final AutoCompleteTextView userInput = (AutoCompleteTextView) promptsView
                                                                                 .findViewById(R.id.a1);
                                Cursor c=db1.getInsertedData();
                                int i=0;
                                ArrayList<String> s1 = new ArrayList<String>();

                                if(c.moveToFirst())
                                {
                                    do
                                    {
                                        //stringArrayList.add(json_data.getString("xCoord")); //add to arraylist
                                        s1.add(c.getString(0));
                                    }while(c.moveToNext());
                                }
                                String [] stringArray = s1.toArray(new String[s1.size()]);
                               // userInput=(AutoCompleteTextView) findViewById(R.id.a1);
                                ArrayAdapter<String> ad1=new ArrayAdapter<String>(MainActivity.this,android.R.layout.select_dialog_item,stringArray);

                                //at1.setThreshold);
                                userInput.setThreshold(1);
                                userInput.setAdapter(ad1);

                                // set dialog message
                                alertDialogBuilder
                                        .setCancelable(false)
                                        .setPositiveButton("OK",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog,int id) {
                                                        // get user input and set it to result
                                                        // edit text
                                                        //result.setText(userInput.getText());
                                                        String s1;
                                                        s1=userInput.getText().toString();
                                                        int in;
                                                        in=subj.indexOf(s1);
                                                        if(in>=0&&in<subj.size())
                                                        {
                                                        subj.remove(subj.indexOf(s1));
                                                        arrayAdapter.notifyDataSetChanged();
                                                        db1.del1(s1);}
                                                        else
                                                            Toast.makeText(MainActivity.this,"Subject not present!!",Toast.LENGTH_LONG).show();

                                                    }
                                                })
                                        .setNegativeButton("Cancel",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog,int id) {
                                                        dialog.cancel();
                                                    }
                                                });

                                // create alert dialog
                                AlertDialog alertDialog = alertDialogBuilder.create();

                                // show it
                                alertDialog.show();
                                break;

                        }
                        lev1.dismiss();
                    }
                });
                lev1 = builder.create();
                lev1.show();}

        });

        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i=new Intent
                        (MainActivity.this,show.class);
                //i.putExtra("name",ed1.getText().toString());
                //i.putExtra("email",ed2.getText().toString());
                startActivity(i);
//                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v7.app.AlertDialog.Builder at=new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                at.setTitle("Message" +
                        "\n");
                at.setMessage("Are you sure You want to exit??");
                at.setCancelable(false);
                at.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       /* Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);*/
                       finish();
                    }
                });
                at.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                android.support.v7.app.AlertDialog ad=at.create();
                ad.show();
            }
        });

        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.dialog, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        //result.setText(userInput.getText());
                                        String s1;
                                        s1=userInput.getText().toString();
                                        if(s1.isEmpty())
                                        {
                                            Toast.makeText(context,"Empty  "+"Subject  ",Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            subj.add(userInput.getText().toString());

                                            // ListElementsArrayList.add(userInput.getText().toString());
                                            // next thing you have to do is check if your adapter has changed
                                            //adapter.notifyDataSetChanged();
                                            arrayAdapter.notifyDataSetChanged();
                                            db1.insert(userInput.getText().toString());}


                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 final String val;
                val = (String) l1.getItemAtPosition(i).toString();
                //AlertDialog levelDialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Select ...");
                builder.setSingleChoiceItems(st, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {


                        switch(item)
                        {
                            case 0:

                                //Toast.makeText(MainActivity.this,"first",Toast.LENGTH_LONG).show();
                                Cursor c=db1.getInsertedData();
                                if(c.moveToFirst())
                                {

                                    do{
                                        String s1;
                                        s1=c.getString(0);
                                        if(s1.equals(val)==true)
                                        {
                                            int p;
                                            p=c.getInt(1);
                                            db1.up1(val,p);
                                            break;
                                        }
                                    }while(c.moveToNext());
                                }
                                //db1.up1(val,p);
                                break;
                            case 1:
                                // Your code when 2nd  option seletced
                                Cursor c1=db1.getInsertedData();
                                //int b=c1.getCount();
                                //Toast.makeText(MainActivity.this,""+b,Toast.LENGTH_LONG).show();
                                if(c1.moveToFirst())
                                {

                                    do{
                                        String s1;
                                        s1=c1.getString(0);
                                        if(s1.equals(val)==true)
                                        {
                                            int p;
                                            p=c1.getInt(2);
                                            db1.up2(val,p);
                                            break;
                                        }
                                    }while(c1.moveToNext());
                                }
                                //Toast.makeText(MainActivity.this,"Second",Toast.LENGTH_LONG).show();

                                break;
                            case 2:
                                //Toast.makeText(MainActivity.this,"Third",Toast.LENGTH_LONG).show();
                                // Your code when 3rd option seletced
                                break;

                        }
                        levelDialog.dismiss();
                    }
                });
                levelDialog = builder.create();
                levelDialog.show();}



        });

    }
}
