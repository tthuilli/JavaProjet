package DotsAndBoxes;


public class Game  {

	   public static void main(String args[]) {

		   
		       java.awt.EventQueue.invokeLater(new Runnable() {
		           public void run() {
		               new FenetreOption().setVisible(true);
		           }
		       });
	   }
}
