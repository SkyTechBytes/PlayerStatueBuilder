package com.skytechbytes.playerstatuebuilder;

import java.util.HashSet;
import java.util.List;

public class StatueArgs {
    private float hue = -1;
    private float saturation = -1;
    private float contrast = -1;
    private float brightness = -1;
    private float posterize = -1;
    private String direction;
    private int x;
    private int y;
    private int z;
    private String world;
    private HashSet<String> flags;
    private HashSet<String> assigned;
    public StatueArgs(List<String> tokens) throws Exception {
        /*
         * Type of skin flags
         */
        flags = new HashSet<>();
        assigned = new HashSet<>();
        /*
         * Tokenize flags with the format TAG:VALUE
         */
        for (String token : tokens) {
            String[] tokenized = token.split(":");
            assigned.add(tokenized[0]); // assigned no matter what
            if (tokenized.length == 2) {
                if (tokenized[0].equalsIgnoreCase("hue")) {
                    this.hue = Float.parseFloat(tokenized[1]);
                } else if (tokenized[0].equalsIgnoreCase("saturation")) {
                    this.saturation = Float.parseFloat(tokenized[1]);
                } else if (tokenized[0].equalsIgnoreCase("contrast")) {
                    this.contrast = Float.parseFloat(tokenized[1]);
                } else if (tokenized[0].equalsIgnoreCase("brightness")) {
                    this.brightness = Float.parseFloat(tokenized[1]);
                } else if (tokenized[0].equalsIgnoreCase("posterize")) {
                    this.posterize = Float.parseFloat(tokenized[1]);
                } else if (tokenized[0].equalsIgnoreCase("x")) {
                    this.x = Integer.parseInt(tokenized[1]);
                } else if (tokenized[0].equalsIgnoreCase("y")) {
                    this.y = Integer.parseInt(tokenized[1]);
                } else if (tokenized[0].equalsIgnoreCase("z")) {
                    this.z = Integer.parseInt(tokenized[1]);
                } else if (tokenized[0].equalsIgnoreCase("direction")) {
                    this.direction = tokenized[1];
                } else if (tokenized[0].equalsIgnoreCase("world")) {
                    this.world = tokenized[1];
                } else {
                    flags.add(tokenized[0]);
                }
            } else if (tokenized.length == 0){
                throw new Exception("Invalid ':' parameter");
            } else {
                flags.add(tokenized[0]);
            }
        }
    }

    public float getHue() {
        return hue;
    }

    public float getSaturation() {
        return saturation;
    }

    public float getContrast() {
        return contrast;
    }

    public float getBrightness() {
        return brightness;
    }

    public float getPosterize() {
        return posterize;
    }

    public String getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String getWorld() {
        return world;
    }

    public boolean hasFlag(String flag) {
        return this.flags.contains(flag);
    }
    // returns true if the value was ever included in the command input
    public boolean isSet(String value) {
        return this.assigned.contains(value);
    }
}
