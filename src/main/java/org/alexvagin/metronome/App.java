package org.alexvagin.metronome;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class App {
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public static void main(String[] args) {
		SystemSounds.playSoundFromResource("tick.wav", clip -> {
			scheduler.scheduleAtFixedRate(() -> {
				clip.setFramePosition(0);
				clip.start();
			}, 0, 1, SECONDS);
			try {
				scheduler.awaitTermination(Long.MAX_VALUE, SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
}
