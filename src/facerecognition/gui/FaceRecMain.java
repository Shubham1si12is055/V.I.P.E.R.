package facerecognition.gui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import facerecognition.utils.Utils;
import facerecognition.javafaces.FaceRec;
import facerecognition.javafaces.FaceRecError;
import facerecognition.javafaces.MatchResult;

public class FaceRecMain{
	public static void main(String [] args){
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	FaceRecView view=new FaceRecView("FACE RECOGNITION");
            	FaceRec model=new FaceRec();
            	new SimpleController(view,model);
            }
        });
	}
	public static void debug(String msg){
		System.out.println(msg);
	}
}

