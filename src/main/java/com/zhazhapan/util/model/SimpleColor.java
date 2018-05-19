package com.zhazhapan.util.model;

/**
 * @author pantao
 */
public class SimpleColor {

    public int r = 0;

    public int g = 0;

    public int b = 0;

    public double opacity = 1;

    public SimpleColor(int r, int g, int b, double opacity) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.opacity = opacity;
    }

    /**
     * 转换为十六进制
     *
     * @return {@link String}
     */
    public String toHexString() {
        return Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
    }

    @Override
    public String toString() {
        return r + "," + g + "," + b + "," + opacity;
    }
}
