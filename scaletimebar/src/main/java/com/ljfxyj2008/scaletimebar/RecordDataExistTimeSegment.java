/*
 * This source code is licensed under the MIT-style license found in the
 * LICENSE file in the root directory of this source tree.
 */
package com.ljfxyj2008.scaletimebar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * Created by ljfxyj2008 on 16/10/28.
 * Email: ljfxyj2008@gmail.com
 * GitHub: <a href="https://github.com/ljfxyj2008">https://github.com/ljfxyj2008</a>
 * HomePage: <a href="http://www.carrotsight.com">http://www.carrotsight.com</a>
 * </pre>
 * <p>
 * Data model to store time clips.
 * <p>
 * One time segment indicates there is record data during the start time and end time of this segment,
 * which causes the segment corresponding to the time duration drawn colored on recordbar.
 */
public class RecordDataExistTimeSegment {
    /**
     * In general, there will be more than one time segments to show on recordbar.<br>
     * Among all the time segments, use this variable to record 0 O'clock of earliest time segment date in millisecond.
     */
    private static long mostLeftDayZeroTime = Long.MAX_VALUE;

    /**
     * Similar to mostLeftDayZeroTime, this variable is used to record the 0 O'clock of latest time segment date in millisecond.
     */
    private static long mostRightDayZeroTime = -1;

    /**
     * Start time of this time segment
     */
    private long startTimeInMillisecond;

    /**
     * End time of this time segment
     */
    private long endTimeInMillisecond;

    /**
     * All time points corresponding to 00:00 for all dates covered by startTimeInMillisecond to endTimeInMillisecond.
     *
     * <pre>
     * For example , the time segment instance is
     * [*RecordDataExistTimeSegment*
     * startTimeInMillisecond:  2015-11-26 10:10:30
     * endTimeInMillisecond:    2015-11-29 19:12:55]
     *
     * Then coverDateZeroOClockList will keep 4 items as below:
     * {“2015-11-26 00:00:00”、“2015-11-27 00:00:00”、“2015-11-28 00:00:00”、“2015-11-29 00:00:00”}
     * </pre>
     */
    private List<Long> coverDateZeroOClockList = new ArrayList<>();

    public RecordDataExistTimeSegment(long startTimeInMillisecond, long endTimeInMillisecond) {
        this.startTimeInMillisecond = startTimeInMillisecond;
        this.endTimeInMillisecond = endTimeInMillisecond;

        if (startTimeInMillisecond < mostLeftDayZeroTime) {
            this.mostLeftDayZeroTime = startTimeInMillisecond;
        }

        if (endTimeInMillisecond > mostRightDayZeroTime) {
            this.mostRightDayZeroTime = endTimeInMillisecond;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat zeroTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String startTimeDateString = dateFormat.format(startTimeInMillisecond);
        String startTimeZeroTimeString = startTimeDateString + " 00:00:00";

        String endTimeDateString = dateFormat.format(endTimeInMillisecond);
        String endTimeZeroTimeString = endTimeDateString + " 00:00:00";

        try {
            Date startTimeZeroDate = zeroTimeFormat.parse(startTimeZeroTimeString);
            Date endTimeZeroDate = zeroTimeFormat.parse(endTimeZeroTimeString);

            long loopZeroDateInMilliseconds = startTimeZeroDate.getTime();
            while (loopZeroDateInMilliseconds <= endTimeZeroDate.getTime()) {
                coverDateZeroOClockList.add(loopZeroDateInMilliseconds);
                loopZeroDateInMilliseconds = loopZeroDateInMilliseconds + ScalableTimebarView.SECONDS_PER_DAY * 1000;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public long getStartTimeInMillisecond() {
        return startTimeInMillisecond;
    }

    public long getEndTimeInMillisecond() {
        return endTimeInMillisecond;
    }

    public List<Long> getCoverDateZeroOClockList() {
        return coverDateZeroOClockList;
    }
}