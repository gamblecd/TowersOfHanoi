package com.cgamble.games.mobiletowersofhanoi.util;

import interfaces.Tower;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import entities.Plate;

public interface Display {
	
	public void display(Tower tower, View view);
	
	public void display(Button button, View view);

	public void display(Plate plate, View view);

	public void display(TextView textView, View view);
}
