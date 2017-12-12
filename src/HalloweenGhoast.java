import java.io.Serializable;


public class HalloweenGhoast implements Serializable{
	
	String message="boo";
	String present="中身はないよー";
	String from="Client氏";
	String to="サバ君";

	int ghoasttype=0;
	public void changeGhoastype(){
		ghoasttype=(ghoasttype++)%5;

	}

	public void printGhoastinfo(){
		System.out.print("getGhoast:"+this.getGhoast());
	}
	public void setGhoastype(int nofg){
		this.ghoasttype=nofg;

	}
	public String getGhoast() {
		if(ghoasttype==0) {
			return "Jack Skellington";
		}else if(ghoasttype==1) {
			return "Zero";
		}else if(ghoasttype==2) {
			return "Sally";
		}else
			return message;


	}
	public void setGhoast(String message) {
		this.message = message;
	}
	public String getPresent() {
		return present;
	}
	public void setPresent(String present) {
		this.present = present;
	}

	public void swapFromTo() {
	}

}
