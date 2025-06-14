package org.sam;
import org.powbot.api.Color;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.*;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;
import org.sam.Tasks.*;


@ScriptConfiguration.List({
        @ScriptConfiguration(
                name = "Inventory",
                description = "How would you like to mine shale?",
                optionType = OptionType.INVENTORY,
                allowedValues = {"3T Mining", "Mining", "AFK Mining"}
        ),
        @ScriptConfiguration(
                name = "Equipment",
                description = "Select the rocks you'd like to interact with",
                optionType = OptionType.EQUIPMENT
        )
})

@ScriptManifest(
        name = "Sam Green Dragons",
        description = "Green Dragon Slayer",
        author = "Sam",
        version = "1.0",
        category = ScriptCategory.Combat
)
public class samGreenDragons extends AbstractScript {
    public static void main(String[] args) {
        new ScriptUploader().uploadAndStart("Sam Green Dragons", "", "R52T90A6VCM", true, false);
    }

    Variables vars = new Variables();
    Constants constants = new Constants();

    @Override
    public void onStart() {

        constants.TASK_LIST.add(new Running(this));

        addPaint(
                PaintBuilder.newBuilder()
                        .minHeight(150)
                        .minWidth(450)
                        .backgroundColor(Color.argb(175, 0, 0, 0))
                        .withTextSize(14F)
                        .addString(() -> "Task: " + vars.currentTask)
                        .trackSkill(Skill.Combat)
                        .build()
        );
        vars.currentTask = "Idle";
    }

    @Override
    public void poll() {
        for (Task task : constants.TASK_LIST) {
            if (task.activate()) {
                vars.currentTask = task.name;
                task.execute();

                if (ScriptManager.INSTANCE.isStopping()) {
                    break;
                }
            }
        }
    }
}