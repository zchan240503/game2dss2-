package main;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	public Clip clip;
	
	URL soundURL[] = new URL[30];
	public Sound() {
		soundURL[0] =  getClass().getResource("/sound/Loonboon.wav");
	}
	public void setfile(int i) throws UnsupportedAudioFileException, LineUnavailableException {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch (IOException e) {

		}
	}
	public void play() {
		clip.start();
	}
	public void loop() {
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
}
