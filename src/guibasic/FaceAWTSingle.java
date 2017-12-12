package guibasic;

import java.awt.*;
import java.awt.event.*;

public class FaceAWTSingle {

	public static void main(String[] args){
		new FaceAWTSingle();
	}
    FaceAWTSingle(){
		FaceFrame f = new FaceFrame();
		f.setSize(800,800);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);}});
		f.setVisible(true);
	}

	// インナークラスとしてのFaceFrameクラス
	class FaceFrame extends Frame{

		private int w;
		private int h;
		private int xStart;
		private int yStart;

		public void paint(Graphics g) {
			// この中には、g.drawLine というのは入ってこない
			// Graphicsクラス(型のようなもの---今は--)のgという変数はメソッドに渡す
			w = 200;
			h = 200;
			xStart=50;
			yStart=50;

			drawRim(g);
			drawBrow(g, 30);
			drawEye(g, 10);
			drawNose(g, 40);
			drawMouth(g, 100);
		}

		public void drawRim(Graphics g) {  // wが横幅、hが縦幅
			g.setColor(Color.ORANGE);
			g.fillRoundRect(55,55,w-10,h-10,20,20);
			//g.drawLine(50, 50, 50+w, 50);
			//g.drawLine(50, 50, 50, 50+h);
			//g.drawLine(50, 50+h, 50+w, 50+h);
			//g.drawLine(50+w, 50, 50+w, 50+h);
			g.setColor(Color.black);
		}

		public void drawBrow(Graphics g,int bx) { // xは目の幅 呼ばれる方(=定義する方)
			g.drawLine(50+40, 50+100, 50+30+30, 50+100);
		}

		public void drawNose(Graphics g, int nx) { // xは鼻の長さ

		}

		public void drawMouth(Graphics g, int mx) { // xは口の幅
			int xMiddle = 50 + w/2;
			int yMiddle = 50 + h - 20;
			g.drawLine(xMiddle - mx/2, yMiddle, xMiddle + mx/2, yMiddle);
		}

		public void drawEye(Graphics g, int r) { // rは目の半径

			g.fillOval(xStart+45,yStart+110,r,r);



		}

	}//FaceFrame class end

}//FaceAWTSingle class end
