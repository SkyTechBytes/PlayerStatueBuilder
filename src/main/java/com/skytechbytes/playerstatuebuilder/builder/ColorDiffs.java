package com.skytechbytes.playerstatuebuilder.builder;

import java.awt.*;

class RGBDiff implements ColorDiffable {
    @Override
    public double getDelta(Color a, Color b, float w1, float w2, float w3) {
        return (int) (Math.pow(a.getRed()-b.getRed(),2)*w1 + Math.pow(a.getGreen()-b.getGreen(),2)*w2 + Math.pow(a.getBlue()-b.getBlue(),2)*w3);
    }
}

class AbsRGBDiff implements ColorDiffable {
    @Override
    public double getDelta(Color a, Color b, float w1, float w2, float w3) {
        return (int) (Math.abs(a.getRed()-b.getRed())*w1 + Math.abs(a.getGreen()-b.getGreen())*w2 + Math.abs(a.getBlue()-b.getBlue())*w3);
    }
}

class HSBDiff implements ColorDiffable {
    @Override
    public double getDelta(Color a, Color b, float w1, float w2, float w3) {
        float[] hsba = new float[3];
        float[] hsbb = new float[3];
        Color.RGBtoHSB(a.getRed(), a.getGreen(), a.getBlue(), hsba);
        Color.RGBtoHSB(b.getRed(), b.getGreen(), b.getBlue(), hsbb);

        return (int) (Math.pow((hsba[0]-hsbb[0])*1000,2)*w1 + Math.pow((hsba[1]-hsbb[1])*1000,2)*w2 + Math.pow((hsba[2]-hsbb[2])*1000,2)*w3);
    }
}

class HSLDiff implements ColorDiffable {
    @Override
    public double getDelta(Color a, Color b, float w1, float w2, float w3) {
        float[] hsla = rgbToHsl(a.getRed(), a.getGreen(), a.getBlue());
        float[] hslb = rgbToHsl(b.getRed(), b.getGreen(), b.getBlue());

        return (int) (Math.pow((hsla[0]-hslb[0])*1000,2)*w1 + Math.pow((hsla[1]-hslb[1])*1000,2)*w2 + Math.pow((hsla[2]-hslb[2])*1000,2)*w3);
    }

    public static float[] rgbToHsl(int r, int g, int b) {
        // Normalize RGB values to [0, 1]
        float rf = r / 255f;
        float gf = g / 255f;
        float bf = b / 255f;

        // Find min and max RGB values
        float max = Math.max(rf, Math.max(gf, bf));
        float min = Math.min(rf, Math.min(gf, bf));
        float delta = max - min;

        // Compute Lightness (L)
        float l = (max + min) / 2;

        // Compute Saturation (S)
        float s = 0;
        if (delta != 0) {
            s = (l > 0.5) ? delta / (2f - max - min) : delta / (max + min);
        }

        // Compute Hue (H)
        float h = 0;
        if (delta != 0) {
            if (max == rf) {
                h = (gf - bf) / delta + (gf < bf ? 6 : 0);
            } else if (max == gf) {
                h = (bf - rf) / delta + 2;
            } else if (max == bf) {
                h = (rf - gf) / delta + 4;
            }
            h /= 6;
        }

        // Return HSL values as an array
        return new float[]{h, s, l};
    }
}

class LABDiff implements ColorDiffable {
    // Constants for the reference white point (D65)
    private static final double Xn = 95.047;
    private static final double Yn = 100.000;
    private static final double Zn = 108.883;

    @Override
    public double getDelta(Color a, Color b, float w1, float w2, float w3) {
        double[] lab1 = rgbToLab(a.getRed(), a.getGreen(), a.getBlue());
        double[] lab2 = rgbToLab(b.getRed(), b.getGreen(), b.getBlue());

        return (Math.pow((lab1[0] - lab2[0]) * 1000, 2) * w1 + Math.pow((lab1[1] - lab2[1]) * 1000, 2) * w2 + Math.pow((lab1[2] - lab2[2]) * 1000, 2) * w3);
    }

    public double[] rgbToLab(int r, int g, int b) {
        double[] xyz = rgbToXyz(r, g, b);
        return xyzToLab(xyz[0], xyz[1], xyz[2]);
    }

    public double[] rgbToXyz(int r, int g, int b) {
        // Normalize RGB values to [0, 1]
        double R = r / 255.0;
        double G = g / 255.0;
        double B = b / 255.0;

        float[] ret = new float[3];
        double X = R * 0.4124564 + G * 0.3575761 + B * 0.1804375;
        double Y = R * 0.2126729 + G * 0.7151522 + B * 0.0721750;
        double Z = R * 0.0193339 + G * 0.1191920 + B * 0.9503041;

        return new double[]{X*100, Y*100, Z*100};
    }

    public double[] xyzToLab(double X, double Y, double Z) {
        // Normalize by the reference white point
        X = X / Xn;
        Y = Y / Yn;
        Z = Z / Zn;

        // Apply the f(t) function
        double fx = f(X);
        double fy = f(Y);
        double fz = f(Z);

        // Calculate L, a, and b
        double L = 116 * fy - 16;
        double a = 500 * (fx - fy);
        double b = 200 * (fy - fz);

        return new double[]{L, a, b};
    }

    public double f(double t) {
        if (t > Math.pow((6.0f/29.0f), 3)) {
            return (Math.pow(t, 1.0f/3.0f));
        } else {
            return (1.0f/3.0f * Math.pow((29.0f/6.0f), 2) * t + 4.0f/29.0f);
        }
    }
}