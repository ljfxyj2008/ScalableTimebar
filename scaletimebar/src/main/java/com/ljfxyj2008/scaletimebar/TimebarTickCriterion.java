/*
 * This source code is licensed under the MIT-style license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.ljfxyj2008.scaletimebar;

/**
 * <pre>
 * Created by ljfxyj2008 on 16/10/28.
 * Email: ljfxyj2008@gmail.com
 * GitHub: <a href="https://github.com/ljfxyj2008">https://github.com/ljfxyj2008</a>
 * HomePage: <a href="http://www.carrotsight.com">http://www.carrotsight.com</a>
 * </pre>
 *
 * When user perform scale operation on ScalableTimebarView, ticks in this view shown on screen will change apperance according to different TimebarTickCriterions.
 */



public class TimebarTickCriterion {
    /**
     * Whole timebar length (Not including two extra empty part on left and right ends)
     */
    private int viewLength;

    /**
     * How many seconds can be seen on one screen
     */
    private int totalSecondsInOneScreen;

    /**
     * Time interval between two large ticks (Also known as key ticks)
     */
    private int keyTickInSecond;

    /**
     * Time interval between two small ticks
     */
    private int minTickInSecond;

    /**
     * How to format time string
     */
    private String dataPattern;

    public int getViewLength() {
        return viewLength;
    }

    public void setViewLength(int viewLength) {
        this.viewLength = viewLength;
    }

    public int getTotalSecondsInOneScreen() {
        return totalSecondsInOneScreen;
    }

    public void setTotalSecondsInOneScreen(int totalSecondsInOneScreen) {
        this.totalSecondsInOneScreen = totalSecondsInOneScreen;
    }

    public int getKeyTickInSecond() {
        return keyTickInSecond;
    }

    public void setKeyTickInSecond(int keyTickInSecond) {
        this.keyTickInSecond = keyTickInSecond;
    }

    public int getMinTickInSecond() {
        return minTickInSecond;
    }

    public void setMinTickInSecond(int minTickInSecond) {
        this.minTickInSecond = minTickInSecond;
    }

    public String getDataPattern() {
        return dataPattern;
    }

    public void setDataPattern(String dataPattern) {
        this.dataPattern = dataPattern;
    }
}
