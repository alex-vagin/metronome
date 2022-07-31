package org.alexvagin.metronome;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SystemSounds {
	private SystemSounds() {
	}

	public static List<String> getSounds() {
		return Arrays.stream(((String[]) Toolkit.getDefaultToolkit().getDesktopProperty("win.propNames"))).filter(prop -> prop.startsWith("win.sound.")).toList();
	}

	public static void playSoundFromResource(String soundFileName, Consumer<Clip> clipConsumer) {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		playSound(classloader.getResourceAsStream(soundFileName), clipConsumer);
	}

	public static void playSound(InputStream soundFileStream, Consumer<Clip> clipConsumer) {
		try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFileStream)) {
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clipConsumer.accept(clip);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			Logger.getLogger("playSound()").log(Level.SEVERE, null, ex);
		}
	}
}
