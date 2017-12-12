package guibasic;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FaceAWTSingleMasa {

	public static void main(String[] args){
		new FaceAWTSingleMasa();
	}
    FaceAWTSingleMasa(){
		FaceFrame f = new FaceFrame();
		f.setSize(800,800);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);}});
		f.setVisible(true);
	}

	// インナークラスとしてのFaceFrameクラス
	class FaceFrame extends Frame{



		public void paint(Graphics g) {
			// この中には、g.drawLine というのは入ってこない
			// Graphicsクラス(型のようなもの---今は--)のgという変数はメソッドに渡す

			//FaceObj fobj=new FaceObj();
			FaceObj[] fobjs= new FaceObj[9];


			//fobj.setPosition(200,100);
			//fobj.drawFace(g);

			for(int j=0;j<3;j++) {

				for (int i = 0; i < 3; i++) {

					fobjs[i+3*j]= new FaceObj();
					fobjs[i].setPosition(200*i+50,200*j+50);
					fobjs[i].setEmotionLevel(i,j);
					fobjs[i].drawFace(g);
				}
			}



		}



	}//FaceFrame class end

	class FaceObj{
		private int w;
		private int h;
		private int xStart;
		private int yStart;


		private int emo_a=0;
		private int emo_b=0;



		FaceObj(){
			w = 200;
			h = 200;
			xStart=150;
			yStart=150;
		}

		public void drawFace(Graphics g){
			drawRim(g);
			drawBrow(g, 30);
			drawEye(g, 10);
			drawNose(g, 40);
			drawMouth(g, 100);
		}

		public void drawRim(Graphics g) {  // wが横幅、hが縦幅
			g.setColor(Color.ORANGE);
			g.fillRoundRect(xStart+5,yStart+5,w-10,h-10,20,20);
			//g.drawLine(50, 50, 50+w, 50);
			//g.drawLine(50, 50, 50, 50+h);
			//g.drawLine(50, 50+h, 50+w, 50+h);
			//g.drawLine(50+w, 50, 50+w, 50+h);
			g.setColor(Color.black);
		}

		public void drawBrow(Graphics g,int bx) { // xは目の幅 呼ばれる方(=定義する方)

			g.drawLine(xStart+40, yStart+100+emo_a*3, xStart+40+30, yStart+100);
			g.drawLine(xStart+40+90, yStart+100, xStart+40+90+30, yStart+100+emo_a*3);
		}

		public void drawNose(Graphics g, int nx) { // xは鼻の長さ
			g.fillOval(xStart+100,yStart+130,5,2*10);

		}

		public void drawMouth(Graphics g, int mx) { // xは口の幅
			int xMiddle = xStart + w/2;
			int yMiddle = yStart + h - 20;
			g.drawLine(xMiddle - mx/2, yMiddle, xMiddle , yMiddle+emo_b*5);
			g.drawLine(xMiddle , yMiddle+emo_b*5, xMiddle + mx/2, yMiddle);

		}

		public void drawEye(Graphics g, int r) { // rは目の半径

			g.fillOval(xStart+45,yStart+110,r,r);
			g.fillOval(xStart+45+100,yStart+110,r,r);


		}

		public void setPosition(int px, int py) {
			this.xStart=px;
			this.yStart=py;

		}

		public void setEmotionLevel(int i, int j) {
			this.emo_a=i;
			this.emo_b=j;

		}
	}


}//FaceAWTSingle class end
