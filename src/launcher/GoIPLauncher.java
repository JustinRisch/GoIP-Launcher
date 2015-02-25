package launcher;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import goip.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GoIPLauncher {

	public static void main(String[] args) {
		JFrame jd = new JFrame();
		jd.setResizable(false);
		jd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			int version = getVersion();
			int currentVersion = getCurrentVersion();
			jd.setTitle("GoIP version " + version + " of " + currentVersion);
		} catch (Exception e) {
			jd.setTitle("GoIP");
		}

		jd.setBounds(100, 100, 300, 100);
		jd.setLocationRelativeTo(null);
		jd.add(new JLabel("Choose your role:"), BorderLayout.NORTH);
		JButton DMButton = new JButton("Dungeon Master");
		DMButton.addActionListener(e -> {
			try {
				GoIPDM.main(args);
				jd.dispose();
			} catch (Exception d) {
				jd.removeAll();
				jd.add(new JLabel(d.getMessage()));
				jd.revalidate();
				jd.repaint();
			}
		});
		jd.add(DMButton, BorderLayout.CENTER);

		JButton PlayerButton = new JButton("Player");
		PlayerButton.addActionListener(e -> {
			try {
				GoIPPlayer.main(args);
				jd.dispose();
			} catch (Exception d) {
				jd.removeAll();
				jd.add(new JLabel(d.getMessage()));
				jd.revalidate();
				jd.repaint();
			}
		});
		jd.add(PlayerButton, BorderLayout.SOUTH);
		jd.setVisible(true);
	}

	private static int getCurrentVersion() {
	
		int version = -1;
		try {
			URL oracle = new URL("https://dl.dropboxusercontent.com/u/11902673/version.txt");
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(oracle.openStream()));

	        String inputLine;
	        while ((inputLine = in.readLine()) != null)
	            System.out.println((version = Integer.parseInt(inputLine)));
	        in.close();
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return version;
	}

	private static int getVersion() throws IOException {
		File file;
		int version = -1;
		try {
			file = new File("/Users/justinrisch/documents/workspace/GoIP/bin/version.txt");
			Scanner in = new Scanner(file);
			if (in.hasNextInt()) {
				version = in.nextInt();
			}
			in.close();
		} catch (Exception e) {
			version = GoIPLauncher.class.getResourceAsStream("/bin/version.txt").read();
		}

		return version;
	}

}
