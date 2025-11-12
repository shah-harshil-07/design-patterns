package CommandExample;

import java.util.Stack;

class Light {
    public void on() {
        System.out.println("Light turned ON");
    }

    public void off() {
        System.out.println("Light turned OFF");
    }
}

class AC {
    public void on() {
        System.out.println("AC turned ON");
    }

    public void off() {
        System.out.println("AC turned OFF");
    }
}

interface Command {
    public void undo();
    public void execute();
}

class LightOnCommand implements Command {
    private final Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.on();
    }

    @Override
    public void undo() {
        this.light.off();
    }
}

class LightOffCommand implements Command {
    private final Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.off();
    }

    @Override
    public void undo() {
        this.light.on();
    }
}

class ACOnCommand implements Command {
    private final AC ac;

    public ACOnCommand(AC ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        this.ac.on();
    }

    @Override
    public void undo() {
        this.ac.off();
    }
}

class ACOffCommand implements Command {
    private final AC ac;

    public ACOffCommand(AC ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        this.ac.off();
    }

    @Override
    public void undo() {
        this.ac.on();
    }
}

// Invoker - NavigateRemoteControl class to control devices
class NavigateRemoteControl {
    private final Command[] buttons;
    private final Stack<Command> commandHistory;

    public NavigateRemoteControl() {
        this.buttons = new Command[4];
        this.commandHistory = new Stack<>();
    }

    public void setCommand(int slot, Command command) {
        this.buttons[slot] = command;
    }

    public void pressButton(int slot) {
        if (slot < 4) {
            Command command = this.buttons[slot];

            command.execute();
            this.commandHistory.add(command);
        } else {
            System.out.println("No command assigned to the slot " + slot);
        }
    }

    public void pressUndo() {
        if (!this.commandHistory.isEmpty()) {
            this.commandHistory.pop().undo();
        } else {
            System.out.println("Command history is empty!");
        }
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        AC ac = new AC();
        Light light = new Light();
        Command acOnCommand = new ACOnCommand(ac);
        Command acOffCommand = new ACOffCommand(ac);
        Command lightOnCommand = new LightOnCommand(light);
        Command lightOffCommand = new LightOffCommand(light);

        NavigateRemoteControl remote = new NavigateRemoteControl();

        remote.setCommand(0, acOnCommand);
        remote.setCommand(1, acOffCommand);
        remote.setCommand(2, lightOnCommand);
        remote.setCommand(3, lightOffCommand);

        remote.pressButton(2);
        remote.pressButton(3);
        remote.pressButton(1);
        remote.pressUndo();
        remote.pressUndo();
    }
}

