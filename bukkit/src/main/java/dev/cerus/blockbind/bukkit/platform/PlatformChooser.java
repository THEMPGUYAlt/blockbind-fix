package dev.cerus.blockbind.bukkit.platform;

import dev.cerus.blockbind.api.platform.PlatformAdapter;
import dev.cerus.blockbind.platform.PlatformAdapter16R3;
import dev.cerus.blockbind.platform.PlatformAdapter18R1;
import org.bukkit.Bukkit;

/**
 * Simple utility for choosing the right adapter
 */
public class PlatformChooser {

    private PlatformChooser() {
    }

    /**
     * Attempts to find a matching platform adapter
     *
     * @return A matching adapter or null
     */
    public static PlatformAdapter choose() {
        String version = Bukkit.getVersion();
        version = version.substring(version.indexOf("MC: ") + 4, version.lastIndexOf(')'));

        String[] parts = version.split("\\.");
        if (parts.length < 2) {
            return null;
        }

        try {
            int major = Integer.parseInt(parts[0]);
            int minor = Integer.parseInt(parts[1]);

            if (major == 1) {
                if (minor >= 18) {
                    return new PlatformAdapter18R1();
                } else if (minor >= 16 && minor <= 17) {
                    return new PlatformAdapter16R3();
                }
            }
        } catch (NumberFormatException e) {
            return null;
        }

        return null;
    }

}
