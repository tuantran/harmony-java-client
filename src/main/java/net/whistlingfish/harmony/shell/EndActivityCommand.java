package net.whistlingfish.harmony.shell;

import net.whistlingfish.harmony.HarmonyClient;

import org.kohsuke.args4j.Argument;

public class EndActivityCommand extends ShellCommand {
    @Argument(required = true)
    private String activity;

    @Override
    public void execute(HarmonyClient harmonyClient) {
        try {
            harmonyClient.endActivity(Integer.parseInt(activity));
        } catch (NumberFormatException e) {
            harmonyClient.endActivityByName(activity);
        }
        println("Activity %s ended", activity);
    }
}
