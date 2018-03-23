import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.swing.JFrame;

public class Music extends JFrame{
	File f;
	URL url;
	URI uri;
	AudioClip ac;
	Music(){
		try {
			f = new File("BackgroundMusic.wav");
			uri = f.toURI();
			url = uri.toURL();
			ac = Applet.newAudioClip(url);
			ac.loop();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void stop() {
		ac.stop();
	}
	public void start() {
		ac.play();
	}
}
