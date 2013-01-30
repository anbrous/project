package com.bweasel.game;

import com.bweasel.activities.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DistributionWait extends Activity {

	private Button monBouton;
	private ProgressDialog progressDialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.distribution_w);
		progressDialog = new ProgressDialog(this);
		monBouton = (Button) findViewById(R.id.MonBouton);
		monBouton.setOnClickListener(new OnClickListener() {
		
			public void onClick(View v) {
				traitementDesDonnees();
			}
		
			private void traitementDesDonnees() {
				// On ajoute un message à notre progress dialog
				progressDialog.setMessage("Distribution des cartes en cours");
				// On affiche notre message
				progressDialog.show();
				new Thread(new Runnable() {
					public void run() {
						// Boucle de 1 a 10
						for (int i = 0; i < 10; i++) {
							try {
								// Attends 500 millisecondes
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						handler.sendEmptyMessage(0);
						// A la fin du traitement, on fait disparaitre notre message
						progressDialog.dismiss();
					}
				}).start();
			}
			
			private Handler handler = new Handler() {
				public void handleMessage(android.os.Message msg) {
					if(msg.what == 0) {
					//monBouton.setText("C'est bon");
						Intent intent = new Intent(DistributionWait.this, DragActivity.class);
						startActivity(intent);
						finish();
					}
				};
		
			};
		});
	}
}
