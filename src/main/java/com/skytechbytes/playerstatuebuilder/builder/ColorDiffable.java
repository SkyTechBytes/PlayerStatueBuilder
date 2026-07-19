package com.skytechbytes.playerstatuebuilder.builder;

import java.awt.*;

public interface ColorDiffable {
    double getDelta(Color a, Color b, float w1, float w2, float w3);
}
