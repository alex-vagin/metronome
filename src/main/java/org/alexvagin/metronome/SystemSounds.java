package org.alexvagin.metronome;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

public class SystemSounds {
	private SystemSounds() {
	}

	public static void playSound(String soundFileName, Consumer<Clip> clipConsumer) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();

		try (InputStream resourceAsStream = classloader.getResourceAsStream(soundFileName);
				 InputStream bufferedInputStream = new BufferedInputStream(resourceAsStream);
				 AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream)) {
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clipConsumer.accept(clip);
		}
	}
}
