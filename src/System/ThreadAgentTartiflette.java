package System;

public class ThreadAgentTartiflette extends Thread {

	public ThreadAgentTartiflette() {

	}

	public ThreadAgentTartiflette(String name) {

		super(name);

	}

	public ThreadAgentTartiflette(ThreadGroup group, Runnable target) {

		super(group, target);

	}

	public ThreadAgentTartiflette(ThreadGroup group, String name) {

		super(group, name);

	}

	public ThreadAgentTartiflette(Runnable target, String name) {

		super(target, name);

	}

	public ThreadAgentTartiflette(ThreadGroup group, Runnable target, String name) {

		super(group, target, name);

	}

	public ThreadAgentTartiflette(ThreadGroup group, Runnable target, String name, long stackSize) {

		super(group, target, name, stackSize);

	}







	@Override

	public void run() {

		super.run();

		while (true) {

			try {

				//Interface.agentTartiflette.Boucle();

				Thread.sleep(1000);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		}

	}

}