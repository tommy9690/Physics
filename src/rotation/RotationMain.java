package rotation;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class RotationMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				JFrame jrame = new JFrame("Physics");
				//jrame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
				RotationPanel rp = new RotationPanel();
				jrame.setContentPane(rp);
				jrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//jrame.setLocationRelativeTo(null);
				jrame.pack();
				jrame.setVisible(true);
			}
		});
	}

}
