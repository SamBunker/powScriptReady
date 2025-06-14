package org.sam.Tasks;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Movement;
import org.sam.Task;
import org.sam.samScriptName;

public class Running extends Task {
    samScriptName main;

    public Running(samScriptName main) {
        super();
        super.name = "Setting Run";
        this.main = main;
    }

    @Override
    public boolean activate() {
        return Movement.energyLevel() > Random.nextInt(14, 16) && !Movement.running();
    }

    @Override
    public void execute() {
        Movement.running(true);
        Condition.sleep(Random.nextInt(22, 50));
    }
}
