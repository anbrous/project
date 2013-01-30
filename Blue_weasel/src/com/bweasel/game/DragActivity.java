package com.bweasel.game;

import com.bweasel.activities.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.os.Bundle;
import android.view.Display;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;

public class DragActivity extends Activity {
  
/** Called when the activity is first created. */
	
	//Card list sent by the server for each player
	private static final String listCard0 ="J0;00;00;73;03;10;51;12;13"; // For player 1: this is the player tablet
	private static final String listCard1 ="J1;00;01;02;03;10;11;12;13"; // For player 2
	private static final String listCard2 ="J2;00;01;02;03;10;11;12;13"; // For player 3
	private static final String listCard3 ="J3;00;01;02;03;10;11;12;13"; // For player 4
	
	
	private static final String[] ListCardPlayers ={listCard0,listCard1,listCard2,listCard3};
	private static final String playerG="J2;00";
	private static final int DIALOG_ALERT = 10;

	ImageView image0, image1, image2, image3,  image4, image5, image6, image7,image8;
    ImageView image10, image11, image12, image13,  image14, image15, image16, image17;
    ImageView image20, image21, image22, image23,  image24, image25, image26, image27;
    ImageView image30, image31, image32, image33,  image34, image35, image36, image37;
    
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_drop);
   
    //Define all the images present in the activity_drop.xml
    image0=(ImageView)findViewById(R.id.ivImage00);
    image1=(ImageView)findViewById(R.id.ivImage01);
    image2=(ImageView)findViewById(R.id.ivImage02);
	image3=(ImageView)findViewById(R.id.ivImage03);
	image4=(ImageView)findViewById(R.id.ivImage04);
	image5=(ImageView)findViewById(R.id.ivImage05);
	image6=(ImageView)findViewById(R.id.ivImage06);
	image7=(ImageView)findViewById(R.id.ivImage07);
	
	image10=(ImageView)findViewById(R.id.ivImage10);
    image11=(ImageView)findViewById(R.id.ivImage11);
    image12=(ImageView)findViewById(R.id.ivImage12);
	image13=(ImageView)findViewById(R.id.ivImage13);
	image14=(ImageView)findViewById(R.id.ivImage14);
	image15=(ImageView)findViewById(R.id.ivImage15);
	image16=(ImageView)findViewById(R.id.ivImage16);
	image17=(ImageView)findViewById(R.id.ivImage17);
	
	image20=(ImageView)findViewById(R.id.ivImage20);
    image21=(ImageView)findViewById(R.id.ivImage21);
    image22=(ImageView)findViewById(R.id.ivImage22);
	image23=(ImageView)findViewById(R.id.ivImage23);
	image24=(ImageView)findViewById(R.id.ivImage24);
	image25=(ImageView)findViewById(R.id.ivImage25);
	image26=(ImageView)findViewById(R.id.ivImage26);
	image27=(ImageView)findViewById(R.id.ivImage27);
	
	image30=(ImageView)findViewById(R.id.ivImage30);
    image31=(ImageView)findViewById(R.id.ivImage31);
    image32=(ImageView)findViewById(R.id.ivImage32);
	image33=(ImageView)findViewById(R.id.ivImage33);
	image34=(ImageView)findViewById(R.id.ivImage34);
	image35=(ImageView)findViewById(R.id.ivImage35);
	image36=(ImageView)findViewById(R.id.ivImage36);
	image37=(ImageView)findViewById(R.id.ivImage37);
	

	//Function to display the cards each player according to the list provided by the server
	for(int j =0; j < ListCardPlayers.length ; j++){
		String t = ListCardPlayers[j];
		  String delimiter = ";";
		  String[] temp;
		  temp = t.split(delimiter);
		  String Pos = temp[0];
		  if(Pos.equals("J0")){
			  ImageView[] listI={image0,image1,image2,image3,image4,image5,image6,image7};
				 for(int i =1; i < temp.length ; i++){
						int res =AssociationCard(temp[i]);
						
						if (i==1){
							image0.setImageResource(res);
						}else if (i==2){
							image1.setImageResource(res);
						}else if (i==3){
							image2.setImageResource(res);
						}else if (i==4){
							image3.setImageResource(res);
						}else if (i==5){
							image4.setImageResource(res);
						}else if (i==6){
							image5.setImageResource(res);
						}else if (i==7){
							image6.setImageResource(res);
						}else if (i==8){
							image7.setImageResource(res);
						}
				 } 
			 }else if (Pos.equals("J1")){
				 for(int i =0; i < temp.length ; i++){
					 
					 if (temp[i].equals("00")){
						 image10.setVisibility(View.VISIBLE);
						 image10.setBackgroundResource(R.drawable.b1pt);
					  }else if(temp[i].equals("01")){
						  image11.setVisibility(View.VISIBLE);
						 image11.setBackgroundResource(R.drawable.b1pt); 
					  }else if(temp[i].equals("02")){
						  image12.setVisibility(View.VISIBLE);
						  image12.setBackgroundResource(R.drawable.b1pt); 
					  }
					  else if(temp[i].equals("03")){
						  image13.setVisibility(View.VISIBLE);
						  image13.setBackgroundResource(R.drawable.b1pt); 
					  }
					  else if(temp[i].equals("10")){
						  image14.setVisibility(View.VISIBLE);
						  image14.setBackgroundResource(R.drawable.b1pt); 
					  }
					  else if(temp[i].equals("11")){
						  image15.setVisibility(View.VISIBLE);
						  image15.setBackgroundResource(R.drawable.b1pt); 
					  }
					  else if(temp[i].equals("12")){
						  image16.setVisibility(View.VISIBLE);
						  image16.setBackgroundResource(R.drawable.b1pt); 
					  }
					  else if(temp[i].equals("13")){
						  image17.setVisibility(View.VISIBLE);
						  image17.setBackgroundResource(R.drawable.b1fh); 
					  }
				 
				 }
			 }else if (Pos.equals("J2")){
				 for(int i =0; i < temp.length ; i++){
					  if (temp[i].equals("00")){
						  image20.setVisibility(View.VISIBLE);
						 image20.setBackgroundResource(R.drawable.b3pt);
					  }else if(temp[i].equals("01")){
						  image21.setVisibility(View.VISIBLE);
						 image21.setBackgroundResource(R.drawable.b3pt); 
					  }else if(temp[i].equals("02")){
						  image22.setVisibility(View.VISIBLE);
						  image22.setBackgroundResource(R.drawable.b3pt); 
					  }
					  else if(temp[i].equals("03")){
						  image23.setVisibility(View.VISIBLE);
						  image23.setBackgroundResource(R.drawable.b3pt); 
					  }
					  else if(temp[i].equals("10")){
						  image24.setVisibility(View.VISIBLE);
						  image24.setBackgroundResource(R.drawable.b3pt); 
					  }
					  else if(temp[i].equals("11")){
						  image25.setVisibility(View.VISIBLE);
						  image25.setBackgroundResource(R.drawable.b3pt); 
					  }
					  else if(temp[i].equals("12")){
						  image26.setVisibility(View.VISIBLE);
						  image26.setBackgroundResource(R.drawable.b3pt); 
					  }
					  else if(temp[i].equals("13")){
						  image27.setVisibility(View.VISIBLE);
						  image27.setBackgroundResource(R.drawable.b1fv); 
					  }
				 
				 } 
			 }else if (Pos.equals("J3")){
				 for(int i =0; i < temp.length ; i++){
					  if (temp[i].equals("00")){
						  image30.setVisibility(View.VISIBLE);
						 image30.setBackgroundResource(R.drawable.b1fh);
					  }else if(temp[i].equals("01")){
						  image31.setVisibility(View.VISIBLE);
						 image31.setBackgroundResource(R.drawable.b2pt); 
					  }else if(temp[i].equals("02")){
						  image32.setVisibility(View.VISIBLE);
						  image32.setBackgroundResource(R.drawable.b2pt); 
					  }
					  else if(temp[i].equals("03")){
						  image33.setVisibility(View.VISIBLE);
						  image33.setBackgroundResource(R.drawable.b2pt); 
					  }
					  else if(temp[i].equals("10")){
						  image34.setVisibility(View.VISIBLE);
						  image34.setBackgroundResource(R.drawable.b2pt); 
					  }
					  else if(temp[i].equals("11")){
						  image35.setVisibility(View.VISIBLE);
						  image35.setBackgroundResource(R.drawable.b2pt); 
					  }
					  else if(temp[i].equals("12")){
						  image36.setVisibility(View.VISIBLE);
						  image36.setBackgroundResource(R.drawable.b2pt); 
					  }
					  else if(temp[i].equals("13")){
						  image37.setVisibility(View.VISIBLE);
						  image37.setBackgroundResource(R.drawable.b2pt); 
					  }
				 
				 } 
			 }
	}
   
	//Put an event onTouch on the images and linearlayout
	findViewById(R.id.ivImage00).setOnTouchListener(new MyTouchListener());
    findViewById(R.id.ivImage01).setOnTouchListener(new MyTouchListener());
    findViewById(R.id.ivImage02).setOnTouchListener(new MyTouchListener());
    findViewById(R.id.ivImage03).setOnTouchListener(new MyTouchListener());
    findViewById(R.id.ivImage04).setOnTouchListener(new MyTouchListener());
    findViewById(R.id.ivImage05).setOnTouchListener(new MyTouchListener());
    findViewById(R.id.ivImage06).setOnTouchListener(new MyTouchListener());
    findViewById(R.id.ivImage07).setOnTouchListener(new MyTouchListener());
    
    findViewById(R.id.bottomcenter).setOnDragListener(new MyDragListener());
    findViewById(R.id.LinearLayout03).setOnDragListener(new MyDragListener());
    findViewById(R.id.LinearLayout02).setOnDragListener(new MyDragListener());
    findViewById(R.id.gridLayout1).setOnDragListener(new MyDragListener());
    findViewById(R.id.gridLayout).setOnDragListener(new MyDragListener());
    
	 
    //Distribution Automatic card given by the server
    PlayedCard(playerG);
  }
  
  private final class MyTouchListener implements OnTouchListener {
	  
	public boolean onTouch(View view, MotionEvent motionEvent) {
		  
      if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
        ClipData data = ClipData.newPlainText("", "");
        DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(data, shadowBuilder, view, 0);
        view.setVisibility(View.INVISIBLE);
        return true;
      } else {
        return false;
      }
    }
  }

  class MyDragListener implements OnDragListener {
   
    public boolean onDrag(View v, DragEvent event) {
      int action = event.getAction();
      View dragView = (View) event.getLocalState();
      
      LinearLayout r2 = (LinearLayout) findViewById(R.id.bottomcenter);
	  int nb2 =r2.getChildCount();
	  if (nb2 >= 1){
		 Toast.makeText(getApplicationContext(),"You played", Toast.LENGTH_LONG).show();
		 LinearLayout myLayout = (LinearLayout) findViewById(R.id.LinearLayout02);
		 LinearLayout myLayout2 = (LinearLayout) findViewById(R.id.bottomcenter);
		  for ( int i = 0; i < myLayout.getChildCount();  i++ ){
		      View view = myLayout.getChildAt(i);
		      view.setEnabled(false); // Or whatever you want to do with the view.
		  }
		  for ( int j = 0; j < myLayout2.getChildCount();  j++ ){
		      View view = myLayout2.getChildAt(j);
		      view.setEnabled(false); // Or whatever you want to do with the view.
		  }
		 
	  }else {
		 
		  switch (event.getAction()) {
	      case DragEvent.ACTION_DRAG_STARTED:
	    	// Do nothing 	 
	    	  
	        break;
	      case DragEvent.ACTION_DRAG_ENTERED:
	       break;
	      case DragEvent.ACTION_DRAG_EXITED:
	    	 break;
	      case DragEvent.ACTION_DROP:
	    	  	Display display = getWindowManager().getDefaultDisplay(); 
			    
			    
	    	  if (v.getId () == R.id.bottomcenter){
	    		  LinearLayout rl = (LinearLayout) findViewById(R.id.bottomcenter);
		    	  int nb =rl.getChildCount();
		    	  if (nb < 1){
		    		    View view = (View) event.getLocalState();
		    			ViewGroup owner = (ViewGroup) view.getParent();
		    			owner.removeView(view);
		    			LinearLayout container = (LinearLayout) v;
		    			container.addView(view);
		    			view.setVisibility(View.VISIBLE);
		    			
		    	  }else{
		    		  dragView.setVisibility(v.VISIBLE);
		    	  }
	    	  	
		    }else if (v.getId () != R.id.bottomcenter  ){
	    		  dragView.setVisibility(v.VISIBLE);
	    	  }else if (v.getId () ==R.id.gridLayout  ){
	    		  dragView.setVisibility(v.VISIBLE);
	    	  }
	         
	        break;
	      case DragEvent.ACTION_DRAG_ENDED:
	        	    	  
	      default:
	        break;
	      }
	  }
      return true;
    }
  }
  
//Distribution Automatic card given by the server
  public void PlayedCard (String liste){
	  
	  String delimiter = ";";
	  String[] temp;
	  temp = liste.split(delimiter);
	  String Player = temp[0];
	  String Card = temp[1];
	  int res =AssociationCard(Card);
	  
	  if(Player.equals("J0")){
		  ImageView iv = new ImageView(this);
		  iv.setImageResource(res);
		  LinearLayout rl = (LinearLayout) findViewById(R.id.bottomcenter);
		 
		  rl.addView(iv);
	  }else 
		  if(Player.equals("J1")){
			  ImageView iv = new ImageView(this);
			  iv.setImageResource(res);
			  LinearLayout rl = (LinearLayout) findViewById(R.id.centerright);
			  
			  rl.addView(iv);
		  }else 
			  if(Player.equals("J2")){
				  ImageView iv = new ImageView(this);
				  iv.setImageResource(res);
				  LinearLayout rl = (LinearLayout) findViewById(R.id.topcenter);
				  image20.setVisibility(View.GONE);
				  rl.addView(iv);
			  }
			  else 
				  if(Player.equals("J3")){
					  ImageView iv = new ImageView(this);
					  iv.setImageResource(res);
					  LinearLayout rl = (LinearLayout) findViewById(R.id.centerleft);
					  
					  rl.addView(iv);
				  }
  }

  
  
  //Associate a card with an image
  public int AssociationCard (String card){
	  
	  int newC = 0;
	  
	  if (card.equals("00")){
			newC =R.drawable.ass;
		  }else if(card.equals("01")){
			  newC =R.drawable.ash; 
		  }else if(card.equals("02")){
			 newC=R.drawable.asc; 
		  }
		  else if(card.equals("03")){
			  newC=R.drawable.asd; 
		  }
		  else if(card.equals("10")){
			  newC=R.drawable.kis; 
		  }
		  else if(card.equals("11")){
			  newC=R.drawable.kih; 
		  }
		  else if(card.equals("12")){
			  newC=R.drawable.kic; 
		  }
		  else if(card.equals("13")){
			  newC=R.drawable.kid; 
		  }
		  else if(card.equals("20")){
			  newC=R.drawable.qus; 
		  }
		  else if(card.equals("21")){
			  newC=R.drawable.quh; 
		  }
		  else if(card.equals("22")){
			  newC=R.drawable.quc; 
		  }
		  else if(card.equals("23")){
			  newC=R.drawable.qud; 
		  }
		  else if(card.equals("30")){
			  newC=R.drawable.jas; 
		  }
		  else if(card.equals("31")){
			  newC=R.drawable.jah; 
		  }
		  else if(card.equals("32")){
			  newC=R.drawable.jac; 
		  }
		  else if(card.equals("33")){
			  newC=R.drawable.jad; 
		  }
		  else if(card.equals("40")){
			  newC=R.drawable.tes; 
		  }
		  else if(card.equals("41")){
			  newC=R.drawable.teh; 
		  }
		  else if(card.equals("42")){
			  newC=R.drawable.tec; 
		  }
		  else if(card.equals("43")){
			  newC=R.drawable.ted; 
		  }
		  else if(card.equals("50")){
			  newC=R.drawable.nis; 
		  }
		  else if(card.equals("51")){
			  newC=R.drawable.nih; 
		  }
		  else if(card.equals("52")){
			  newC=R.drawable.nic; 
		  }
		  else if(card.equals("53")){
			  newC=R.drawable.nid; 
		  }
		  else if(card.equals("60")){
			  newC=R.drawable.eis; 
		  }
		  else if(card.equals("61")){
			  newC=R.drawable.eih; 
		  }
		  else if(card.equals("62")){
			  newC=R.drawable.eic; 
		  }
		  else if(card.equals("63")){
			  newC=R.drawable.eid; 
		  }
		  else if(card.equals("70")){
			  newC=R.drawable.ses; 
		  }
		  else if(card.equals("71")){
			  newC=R.drawable.seh; 
		  }
		  else if(card.equals("72")){
			  newC=R.drawable.sec; 
		  }
		  else if(card.equals("73")){
			  newC=R.drawable.sed; 
		  }
	return newC;
	  
  }

  //Return button function
  public void onClick(View view) {
	    showDialog(DIALOG_ALERT);
	  }

	  @Override
	  protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case DIALOG_ALERT:
	      // Create out AlterDialog
	      Builder builder = new AlertDialog.Builder(this);
	      builder.setMessage("You will be disconnected from the game.");
	      builder.setCancelable(true);
	      builder.setPositiveButton("I agree", new OkOnClickListener());
	      builder.setNegativeButton("No, no", new CancelOnClickListener());
	      AlertDialog dialog = builder.create();
	      dialog.show();
	    }
	    return super.onCreateDialog(id);
	  }

	  private final class CancelOnClickListener implements
	      DialogInterface.OnClickListener {
	    public void onClick(DialogInterface dialog, int which) {
	      Toast.makeText(getApplicationContext(), "Activity will continue",
	          Toast.LENGTH_LONG).show();
	    }
	  }

	  private final class OkOnClickListener implements DialogInterface.OnClickListener {
	    public void onClick(DialogInterface dialog, int which) {
	      DragActivity.this.finish();
	    }
	  }
	 
	  //Disable the tablet button
	  @Override
      public void onBackPressed() {
		  showDialog(DIALOG_ALERT);
         return;
      }

	} 