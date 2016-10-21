package net.whistlingfish.harmony.shell;

import net.whistlingfish.harmony.HarmonyClient;

import org.kohsuke.args4j.Argument;

public class PressButtonCommand extends ShellCommand {
    @Argument(required = true, index = 0)
    private String deviceId;

    @Argument(required = true, index = 1)
    private String button;

    @Argument(required = true, index= 2)
    private String pressTime;

    @Override
    public void execute(HarmonyClient harmonyClient) {
        try {
            if (pressTime!=null) {
                harmonyClient.pressButton(Integer.parseInt(deviceId),button, Integer.parseInt(pressTime));
            }
            else {
                harmonyClient.pressButton(Integer.parseInt(deviceId), button);
            }

        } catch (NumberFormatException e) {
            harmonyClient.pressButton(deviceId, button);
        }
    }
}
